package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.PlaceableItems.PlaceableItems;

public class IngotBlockRenderer extends TileEntitySpecialRenderer {

	//IngotBlockModel model = new IngotBlockModel();
	ResourceLocation iron = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/ironBlock.png");
	ResourceLocation gold = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/goldBlock.png");
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "Ingot.obj"));
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		if(entity.getBlockMetadata() == 0)
			bindTexture(iron);
		if(entity.getBlockMetadata() == 1)
			bindTexture(gold);
		
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float) y + 0.09F , (float) z + 0.5F);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScaled(0.055f, 0.055f, 0.055f);
        model.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
}
