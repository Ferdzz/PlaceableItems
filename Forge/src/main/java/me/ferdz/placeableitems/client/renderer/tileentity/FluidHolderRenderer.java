package me.ferdz.placeableitems.client.renderer.tileentity;

import java.util.IdentityHashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.block.component.impl.FluidHolderBlockComponent;
import me.ferdz.placeableitems.client.AllVertexBoundingBox;
import me.ferdz.placeableitems.client.model.FluidModel;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.client.model.animation.TileEntityRendererFast;
import net.minecraftforge.fluids.FluidStack;

/**
 * A fast tile entity renderer implementation for {@link FluidHolderTileEntity} instances.
 * Fluid holders should bind {@link FluidModel} implementations to each registered renderer
 * instance using {@link #bind(PlaceableItemsBlock, FluidModel)}. If a
 * {@link FluidHolderBlockComponent#shouldRenderFluid()} returns true but has no registeredFluidModel,
 * an exception will be thrown and the client will crash.
 *
 * @author Parker Hawke - Choco
 */
public class FluidHolderRenderer extends TileEntityRendererFast<FluidHolderTileEntity> {

    private final Map<PlaceableItemsBlock, FluidModel> fluidModels = new IdentityHashMap<>();

    @Override
    public void renderTileEntityFast(FluidHolderTileEntity tile, double x, double y, double z, float partialTicks, int destroyStage, BufferBuilder buffer) {
        FluidStack fluidStack = tile.getFluid();
        if (fluidStack.isEmpty()) {
            return;
        }

        BlockState state = tile.getBlockState();
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

        Vector3d rotationPoint = model.getOrigin(state);
        buffer.setTranslation(x + (rotationPoint.x / 16F), y + (rotationPoint.y / 16F), z + (rotationPoint.z / 16F));

        // Render the fluid
        Fluid fluid = fluidStack.getFluid();
        TextureAtlasSprite still = Minecraft.getInstance().getTextureMap().getAtlasSprite(fluid.getAttributes().getStill(fluidStack).toString());
        float u1 = still.getMinU(), u2 = still.getMaxU();
        float v1 = still.getMinV(), v2 = still.getMaxV();

        int color = fluid.getAttributes().getColor(fluidStack);
        float alpha = (color >> 24 & 0xFF) / 255F;
        float red = (color >> 16 & 0xFF) / 255F;
        float green = (color >> 8 & 0xFF) / 255F;
        float blue = (color & 0xFF) / 255F;

        BlockPos pos = tile.getPos();
        AllVertexBoundingBox bounds = model.getRenderBounds(state);

        if (model.shouldRender(state, Direction.DOWN)) {
            int downCombined = getWorld().getCombinedLight(pos.down(), 0);
            int downLMa = downCombined >> 16 & 65535;
            int downLMb = downCombined & 65535;

            bufferPos(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).tex(u1, v2).lightmap(downLMa, downLMb).endVertex();
            bufferPos(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).tex(u1, v1).lightmap(downLMa, downLMb).endVertex();
            bufferPos(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).tex(u2, v1).lightmap(downLMa, downLMb).endVertex();
            bufferPos(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).tex(u2, v2).lightmap(downLMa, downLMb).endVertex();
        }

        if (model.shouldRender(state, Direction.UP)) {
            int upCombined = getWorld().getCombinedLight(pos.up(), 0);
            int upLMa = upCombined >> 16 & 65535;
            int upLMb = upCombined & 65535;

            bufferPos(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).tex(u1, v2).lightmap(upLMa, upLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).tex(u1, v1).lightmap(upLMa, upLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).tex(u2, v1).lightmap(upLMa, upLMb).endVertex();
            bufferPos(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).tex(u2, v2).lightmap(upLMa, upLMb).endVertex();
        }

        if (model.shouldRender(state, Direction.NORTH)) {
            int northCombined = getWorld().getCombinedLight(pos.north(), 0);
            int northLMa = northCombined >> 16 & 65535;
            int northLMb = northCombined & 65535;

            bufferPos(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).tex(u1, v1).lightmap(northLMa, northLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).tex(u1, v2).lightmap(northLMa, northLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).tex(u2, v2).lightmap(northLMa, northLMb).endVertex();
            bufferPos(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).tex(u2, v1).lightmap(northLMa, northLMb).endVertex();
        }

        if (model.shouldRender(state, Direction.SOUTH)) {
            int southCombined = getWorld().getCombinedLight(pos.south(), 0);
            int southLMa = southCombined >> 16 & 65535;
            int southLMb = southCombined & 65535;

            bufferPos(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).tex(u2, v1).lightmap(southLMa, southLMb).endVertex();
            bufferPos(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).tex(u2, v2).lightmap(southLMa, southLMb).endVertex();
            bufferPos(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).tex(u1, v2).lightmap(southLMa, southLMb).endVertex();
            bufferPos(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).tex(u1, v1).lightmap(southLMa, southLMb).endVertex();
        }

        if (model.shouldRender(state, Direction.WEST)) {
            int westCombined = getWorld().getCombinedLight(pos.west(), 0);
            int westLMa = westCombined >> 16 & 65535;
            int westLMb = westCombined & 65535;

            bufferPos(buffer, bounds.getBackBottomLeft()).color(red, green, blue, alpha).tex(u1, v2).lightmap(westLMa, westLMb).endVertex();
            bufferPos(buffer, bounds.getBackTopLeft()).color(red, green, blue, alpha).tex(u2, v2).lightmap(westLMa, westLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopLeft()).color(red, green, blue, alpha).tex(u2, v1).lightmap(westLMa, westLMb).endVertex();
            bufferPos(buffer, bounds.getFrontBottomLeft()).color(red, green, blue, alpha).tex(u1, v1).lightmap(westLMa, westLMb).endVertex();
        }

        if (model.shouldRender(state, Direction.EAST)) {
            int eastCombined = getWorld().getCombinedLight(pos.east(), 0);
            int eastLMa = eastCombined >> 16 & 65535;
            int eastLMb = eastCombined & 65535;

            bufferPos(buffer, bounds.getFrontBottomRight()).color(red, green, blue, alpha).tex(u1, v1).lightmap(eastLMa, eastLMb).endVertex();
            bufferPos(buffer, bounds.getFrontTopRight()).color(red, green, blue, alpha).tex(u2, v1).lightmap(eastLMa, eastLMb).endVertex();
            bufferPos(buffer, bounds.getBackTopRight()).color(red, green, blue, alpha).tex(u2, v2).lightmap(eastLMa, eastLMb).endVertex();
            bufferPos(buffer, bounds.getBackBottomRight()).color(red, green, blue, alpha).tex(u1, v2).lightmap(eastLMa, eastLMb).endVertex();
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
    public FluidHolderRenderer bind(PlaceableItemsBlock block, FluidModel model) {
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

    // Convenience method to buffer a Vector3d. It would be much uglier otherwise
    private BufferBuilder bufferPos(BufferBuilder buffer, Vector3d pos) {
        return buffer.pos(pos.x, pos.y, pos.z);
    }

}
