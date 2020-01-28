package me.ferdz.placeableitems.blockentities.renderers;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.IdentityHashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.platform.GlStateManager;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.block.component.impl.FluidHolderBlockComponent;
import me.ferdz.placeableitems.blockentities.FluidHolderBlockEntity;
import me.ferdz.placeableitems.client.AllVertexBoundingBox;
import me.ferdz.placeableitems.client.model.FluidModel;
import me.ferdz.placeableitems.utils.FluidStack;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.Vec3d;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import org.lwjgl.opengl.GL11;

/**
 * A fast tile entity renderer implementation for {@link FluidHolderBlockEntity} instances.
 * Fluid holders should bind {@link FluidModel} implementations to each registered renderer
 * instance using {@link #bind(PlaceableItemsBlock, FluidModel)}. If a
 * {@link FluidHolderBlockComponent#shouldRenderFluid()} returns true but has no registered FluidModel,
 * an exception will be thrown and the client will crash.
 *
 * @author Parker Hawke - Choco
 */
public class FluidHolderBlockEntityRenderer extends PlaceableItemBlockEntityRenderer<FluidHolderBlockEntity> {

    private static final FloatBuffer COLOR_BUFFER = createDirectFloatBuffer(4);
    private static final Vector3f LIGHT0_POS = createNormalizedVector(0.2F, 1.0F, -0.7F);
    private static final Vector3f LIGHT1_POS = createNormalizedVector(-0.2F, 1.0F, 0.7F);

    private final Map<PlaceableItemsBlock, FluidModel> fluidModels = new IdentityHashMap<>();

    // Technically these should be reloaded on resource reload, but quite honestly, I don't really care.
    private final Map<FluidState, WeakReference<Sprite>> fluidSprites = new IdentityHashMap<>();

    @Override
    public void render(FluidHolderBlockEntity tile, double x, double y, double z, float partialTicks, int destroyStage) {
        super.render(tile, x, y, z, partialTicks, destroyStage);

        // Tesselation to batch render all fluids in one draw call
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBufferBuilder();
        this.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.shadeModel(MinecraftClient.isAmbientOcclusionEnabled() ? GL11.GL_SMOOTH : GL11.GL_FLAT);
        
        buffer.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR_UV_LMAP);
        this.bufferRender(tile, x, y, z, buffer);
        buffer.setOffset(0, 0, 0);
        tessellator.draw();

        this.enableStandardItemLighting();
    }

    public void bufferRender(FluidHolderBlockEntity tile, double x, double y, double z, BufferBuilder buffer) {
        FluidStack fluidStack = tile.getFluid();
        if (fluidStack.isEmpty()) {
            return;
        }

        BlockState state = tile.getCachedState();
        Block block = state.getBlock();
        if (!(block instanceof PlaceableItemsBlock)) {
            return;
        }

        FluidHolderBlockComponent fluidComponent = getFluidHolderBlockComponent((PlaceableItemsBlock) block);
        Preconditions.checkState(fluidComponent != null, "FluidHolderTileEntity does not have a FluidHolderBlockComponent. This is impossible.");
        if (!fluidComponent.shouldRenderFluid()) {
            return;
        }

        FluidModel model = this.fluidModels.get(block);
        Preconditions.checkState(model != null, "Missing model binding for FluidHolderTileEntity (" + tile.getClass().getName() + ")");

        Vec3d rotationPoint = model.getOrigin(state);
        buffer.setOffset(x + (rotationPoint.x / 16F), y + (rotationPoint.y / 16F), z + (rotationPoint.z / 16F));

        // Render the fluid
        Fluid fluid = fluidStack.getFluid();
        FluidRenderHandler fluidRenderHandler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);

        Sprite still = fluidSprites.computeIfAbsent(fluid.getDefaultState(), s -> new WeakReference<>(fluidRenderHandler.getFluidSprites(getWorld(), tile.getPos(), s)[0])).get();
        float u1 = still.getMinU(), u2 = still.getMaxU();
        float v1 = still.getMinV(), v2 = still.getMaxV();

        int color = fluidRenderHandler.getFluidColor(getWorld(), tile.getPos(), fluid.getDefaultState());
        float alpha = 1.0f; // Fabric's rendering hooks do not allow for custom alpha tints. A full alpha value will do for now. Most all fluids will be full alpha anyways
        float red = (color >> 16 & 0xFF) / 255F;
        float green = (color >> 8 & 0xFF) / 255F;
        float blue = (color & 0xFF) / 255F;

        BlockPos pos = tile.getPos();
        AllVertexBoundingBox bounds = model.getRenderBounds(state);

        if (model.shouldRender(state, Direction.DOWN)) {
            int downCombined = getWorld().getLightmapIndex(pos.down(), 0);
            int downLMa = downCombined >> 16 & 65535;
            int downLMb = downCombined & 65535;

            // Two calls to texture(), different overloads. Fabric mappings here are strange. The second call represents the light map
            bufferVertex(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).texture(u1, v2).texture(downLMa, downLMb).next();
            bufferVertex(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).texture(u1, v1).texture(downLMa, downLMb).next();
            bufferVertex(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).texture(u2, v1).texture(downLMa, downLMb).next();
            bufferVertex(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).texture(u2, v2).texture(downLMa, downLMb).next();
        }

        if (model.shouldRender(state, Direction.UP)) {
            int upCombined = getWorld().getLightmapIndex(pos.up(), 0);
            int upLMa = upCombined >> 16 & 65535;
            int upLMb = upCombined & 65535;

            bufferVertex(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).texture(u1, v2).texture(upLMa, upLMb).next();
            bufferVertex(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).texture(u1, v1).texture(upLMa, upLMb).next();
            bufferVertex(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).texture(u2, v1).texture(upLMa, upLMb).next();
            bufferVertex(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).texture(u2, v2).texture(upLMa, upLMb).next();
        }

        if (model.shouldRender(state, Direction.NORTH)) {
            int northCombined = getWorld().getLightmapIndex(pos.north(), 0);
            int northLMa = northCombined >> 16 & 65535;
            int northLMb = northCombined & 65535;

            bufferVertex(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).texture(u1, v1).texture(northLMa, northLMb).next();
            bufferVertex(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).texture(u1, v2).texture(northLMa, northLMb).next();
            bufferVertex(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).texture(u2, v2).texture(northLMa, northLMb).next();
            bufferVertex(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).texture(u2, v1).texture(northLMa, northLMb).next();
        }

        if (model.shouldRender(state, Direction.SOUTH)) {
            int southCombined = getWorld().getLightmapIndex(pos.south(), 0);
            int southLMa = southCombined >> 16 & 65535;
            int southLMb = southCombined & 65535;

            bufferVertex(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).texture(u2, v1).texture(southLMa, southLMb).next();
            bufferVertex(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).texture(u2, v2).texture(southLMa, southLMb).next();
            bufferVertex(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).texture(u1, v2).texture(southLMa, southLMb).next();
            bufferVertex(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).texture(u1, v1).texture(southLMa, southLMb).next();
        }

        if (model.shouldRender(state, Direction.WEST)) {
            int westCombined = getWorld().getLightmapIndex(pos.west(), 0);
            int westLMa = westCombined >> 16 & 65535;
            int westLMb = westCombined & 65535;

            bufferVertex(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).texture(u1, v2).texture(westLMa, westLMb).next();
            bufferVertex(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).texture(u2, v2).texture(westLMa, westLMb).next();
            bufferVertex(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).texture(u2, v1).texture(westLMa, westLMb).next();
            bufferVertex(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).texture(u1, v1).texture(westLMa, westLMb).next();
        }

        if (model.shouldRender(state, Direction.EAST)) {
            int eastCombined = getWorld().getLightmapIndex(pos.east(), 0);
            int eastLMa = eastCombined >> 16 & 65535;
            int eastLMb = eastCombined & 65535;

            bufferVertex(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).texture(u1, v1).texture(eastLMa, eastLMb).next();
            bufferVertex(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).texture(u2, v1).texture(eastLMa, eastLMb).next();
            bufferVertex(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).texture(u2, v2).texture(eastLMa, eastLMb).next();
            bufferVertex(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).texture(u1, v2).texture(eastLMa, eastLMb).next();
        }
    }

    /**
     * Bind a {@link PlaceableItemsBlock} to a {@link FluidModel} implementation.
     *
     * @param block the block for which to bind the model
     * @param model the model to bind
     *
     * @return this instance. Allows for chained method calls
     */
    public FluidHolderBlockEntityRenderer bind(PlaceableItemsBlock block, FluidModel model) {
        this.fluidModels.put(block, model);
        return this;
    }

    private FluidHolderBlockComponent getFluidHolderBlockComponent(PlaceableItemsBlock block) {
        for (IBlockComponent component : block.getComponents()) {
            if (component instanceof FluidHolderBlockComponent) {
                return (FluidHolderBlockComponent) component;
            }
        }

        return null;
    }

    // Convenience method to buffer a Vec3d. It would be much uglier otherwise
    private BufferBuilder bufferVertex(BufferBuilder buffer, Vec3d pos) {
        return buffer.vertex(pos.x, pos.y, pos.z);
    }
    
    private void disableStandardItemLighting() {
        GlStateManager.disableLighting();
        GlStateManager.disableLight(0);
        GlStateManager.disableLight(1);
        GlStateManager.disableColorMaterial();
    }

    private void enableStandardItemLighting() {
        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);

        GlStateManager.light(GL11.GL_COLOR_BUFFER_BIT, GL11.GL_POSITION, setColorBuffer(LIGHT0_POS.getX(), LIGHT0_POS.getY(), LIGHT0_POS.getZ(), 0.0F));
        GlStateManager.light(GL11.GL_COLOR_BUFFER_BIT, GL11.GL_DIFFUSE, setColorBuffer(0.6F, 0.6F, 0.6F, 1.0F));
        GlStateManager.light(GL11.GL_COLOR_BUFFER_BIT, GL11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.light(GL11.GL_COLOR_BUFFER_BIT, GL11.GL_SPECULAR, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.light(GL11.GL_LIGHT1, GL11.GL_POSITION, setColorBuffer(LIGHT1_POS.getX(), LIGHT1_POS.getY(), LIGHT1_POS.getZ(), 0.0F));
        GlStateManager.light(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, setColorBuffer(0.6F, 0.6F, 0.6F, 1.0F));
        GlStateManager.light(GL11.GL_LIGHT1, GL11.GL_AMBIENT, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
        GlStateManager.light(GL11.GL_LIGHT1, GL11.GL_SPECULAR, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.lightModel(GL11.GL_LIGHT_MODEL_AMBIENT, setColorBuffer(0.4F, 0.4F, 0.4F, 1.0F));
    }

    private FloatBuffer setColorBuffer(float red, float green, float blue, float alpha) {
        COLOR_BUFFER.clear();
        COLOR_BUFFER.put(red).put(green).put(blue).put(alpha);
        COLOR_BUFFER.flip();
        return COLOR_BUFFER;
    }

    private static synchronized FloatBuffer createDirectFloatBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    private static Vector3f createNormalizedVector(float x, float y, float z) {
        Vector3f vector = new Vector3f(x, y, z);
        vector.reciprocal();
        return vector;
    }

}
