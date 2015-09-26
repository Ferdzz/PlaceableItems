package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEPorkchop;

public class TESRPorkchop extends TileEntitySpecialRenderer {
	
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/steak.obj"));
	ResourceLocation raw = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/porkchop_raw.png");
	ResourceLocation cooked = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/porkchop_cooked.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TEPorkchop facedEntity = (TEPorkchop) entity;
		
		if(entity.getBlockMetadata() == 0)
			bindTexture(raw);
		else if (entity.getBlockMetadata() == 1)
			bindTexture(cooked);
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);
		GL11.glScalef(1, 1, 1);
		
		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180, 0, 0.5F, 0F);
		
		model.renderAll();
		GL11.glPopMatrix();
	}
}
