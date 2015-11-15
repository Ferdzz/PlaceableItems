package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TESteak;

public class TESRSteak extends TileEntitySpecialRenderer {
	
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/steak.obj"));
	ResourceLocation raw = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/steak_raw.png");
	ResourceLocation cooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/steak_cooked.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TESteak facedEntity = (TESteak) entity;
		
		if(entity.getBlockMetadata() == 0)
			bindTexture(raw);
		else if (entity.getBlockMetadata() == 1)
			bindTexture(cooked);
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(1, 1, 1);
		
		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90 + 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		
		model.renderAll();
		GL11.glPopMatrix();
	}
}
