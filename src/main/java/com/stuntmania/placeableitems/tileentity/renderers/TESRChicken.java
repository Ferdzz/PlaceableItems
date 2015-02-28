package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEChicken;

public class TESRChicken extends TileEntitySpecialRenderer {

	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/chicken.obj"));
	ResourceLocation texture = new ResourceLocation("minecraft", "textures/items/chicken_raw.png");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TEChicken facedEntity = (TEChicken) entity;
		
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.05F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(0.23F, 0.23F, 0.23F);
		
        int facing = facedEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        
		model.renderAll();
		GL11.glPopMatrix();
	}
}
