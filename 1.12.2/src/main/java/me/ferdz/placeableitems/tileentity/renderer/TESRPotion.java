package me.ferdz.placeableitems.tileentity.renderer;

import java.util.List;

import javax.annotation.Nullable;

import me.ferdz.placeableitems.tileentity.TEPotion;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class TESRPotion extends TileEntitySpecialRenderer<TEPotion> {
	
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	public void renderTileEntityAt(TEPotion te, double x, double y, double z, float partialTicks, int destroyStage) {
		BlockPos pos = new BlockPos(x, y, z);
		IBlockState state = te.getWorld().getBlockState(pos);
		IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);

		// try 2
//		Tessellator tessellator = Tessellator.getInstance();
//		VertexBuffer vertexbuffer = tessellator.getBuffer();
//
//		this.bindTexture(model.getParticleTexture().);
//		RenderHelper.disableStandardItemLighting();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        GlStateManager.enableBlend();
//        GlStateManager.disableCull();
//		
//        GlStateManager.shadeModel(7424);
//
//
//        vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
//        vertexbuffer.setTranslation(x,y,z);
//	    vertexbuffer.setTranslation((double)((float)x - (float)te.getPos().getX()), (double)((float)y - (float)te.getPos().getY()), (double)((float)z - (float)te.getPos().getZ()));
//        
//        vertexbuffer.setTranslation((double)((float)x - (float)te.getPos().getX() + te.getOffsetX(partialTicks)), (double)((float)y - (float)blockpos.getY() + te.getOffsetY(partialTicks)), (double)((float)z - (float)blockpos.getZ() + te.getOffsetZ(partialTicks)));
//        
//        
//		Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(getWorld(), model, state, pos, vertexbuffer, true);
//
//		
//		vertexbuffer.setTranslation(0.0D, 0.0D, 0.0D);
//        tessellator.draw();
//        RenderHelper.enableStandardItemLighting();
//        
//        vertexbuffer.setTranslation(0.0D, 0.0D, 0.0D);
//        tessellator.draw();
		
// try 1
		GlStateManager.translate(x, y, z);

		GlStateManager.depthMask(false);
		GlStateManager.disableLighting();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
		Minecraft.getMinecraft().renderEngine.bindTexture(RES_ITEM_GLINT);
		GlStateManager.matrixMode(5890);
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F); 
		float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
		GlStateManager.translate(f, 0.0F, 0.0F);
		GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
		
		this.renderModel(model, -1);
		
		GlStateManager.popMatrix();

//		GlStateManager.pushMatrix();
//		GlStateManager.scale(8.0F, 8.0F, 8.0F);
//		float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
//		GlStateManager.translate(-f1, 0.0F, 0.0F);
//		GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
//		this.renderModel(model, -8372020);
//		GlStateManager.popMatrix();
//		GlStateManager.matrixMode(5888);
//		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

		GlStateManager.enableLighting();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		
//		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	}
	
	private void renderModel(IBakedModel model, int color) {
		this.renderModel(model, color, (ItemStack) null);
	}
	
	private void renderModel(IBakedModel model, int color, @Nullable ItemStack stack) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.BLOCK);
		
		for (EnumFacing enumfacing : EnumFacing.values()) {
			this.renderQuads(vertexbuffer, model.getQuads((IBlockState) null, enumfacing, 0L), color, stack);
		}
		
		this.renderQuads(vertexbuffer, model.getQuads((IBlockState) null, (EnumFacing) null, 0L), color, stack);
		tessellator.draw();
	}
	
	private void renderQuads(BufferBuilder renderer, List<BakedQuad> quads, int color, @Nullable ItemStack stack)
    {
        boolean flag = color == -1 && stack != null;
        int i = 0;

        for (int j = quads.size(); i < j; ++i)
        {
            BakedQuad bakedquad = (BakedQuad)quads.get(i);
            int k = color;

            if (flag && bakedquad.hasTintIndex())
            {
                k = Minecraft.getMinecraft().getItemColors().getColorFromItemstack(stack, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    k = TextureUtil.anaglyphColor(k);
                }

                k = k | -16777216;
            }

            net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(renderer, bakedquad, k);
        }
    }
}
