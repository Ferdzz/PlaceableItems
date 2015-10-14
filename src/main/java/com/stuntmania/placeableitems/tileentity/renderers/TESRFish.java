package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEFish;

public class TESRFish extends TileEntitySpecialRenderer {
	
	IModelCustom normal_uncooked = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/fish.obj"));
	IModelCustom normal_cooked = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/fish_cooked.obj"));
	IModelCustom salmon_uncooked = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/salmon.obj"));
	IModelCustom salmon_cooked = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/salmon_cooked.obj"));
	
	ResourceLocation texture_normal_uncooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/fish.png");
	ResourceLocation texture_normal_cooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/fish_cooked.png");
	ResourceLocation texture_salmon_uncooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/salmon.png");
	ResourceLocation texture_salmon_cooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/salmon_cooked.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TEFish facedEntity = (TEFish) entity;
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(1, 1, 1);
		
		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90 - 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		switch (entity.getBlockMetadata()) {
		case 0:
			bindTexture(texture_normal_uncooked);
			normal_uncooked.renderAll();
			break;
		case 1:
			bindTexture(texture_normal_cooked);
			normal_cooked.renderAll();
			break;
		case 3:
			bindTexture(texture_salmon_uncooked);
			salmon_uncooked.renderAll();
			break;
		case 4:
			bindTexture(texture_salmon_cooked);
			salmon_cooked.renderAll();
			break;
		}
		
		GL11.glPopMatrix();
	}
}
