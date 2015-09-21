package com.stuntmania.placeableitems.tileentity.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEBucket;

public class TESRBucket extends TileEntitySpecialRenderer {
	
	IModelCustom filledModel = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/bucket_filled.obj"));
	IModelCustom emptyModel = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/bucket_empty.obj"));
	ResourceLocation water = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucket_water.png");
	ResourceLocation lava = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucket_lava.png");
	ResourceLocation milk = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucket_milk.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		TEBucket facedEntity = (TEBucket) entity;
		
		switch (facedEntity.getBlockMetadata()) {
		case 0:
		case 1:
			bindTexture(water);
			break;
		case 2:
			bindTexture(lava);
			break;
		case 3:
			bindTexture(milk);
			break;
		}
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(1, 1, 1);
		
		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		
		switch (facedEntity.getBlockMetadata()) {
		case 0: // empty
			emptyModel.renderAll();
			break;
		case 1:
		case 2:
		case 3:
			filledModel.renderAll();
			break;
		}
		GL11.glPopMatrix();
	}
}
