package com.stuntmania.placeableitems.tileentity.renderers;

import org.lwjgl.opengl.GL11;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.model.ModelSlimeBall;
import com.stuntmania.placeableitems.tileentity.TEPlaceableItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TESRSlimeBall extends TileEntitySpecialRenderer {
	
	ModelSlimeBall model = new ModelSlimeBall();
	ResourceLocation textures = (new ResourceLocation(PlaceableItems.MODID + ":textures/blocks/slimeBall.png"));
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glPushMatrix();
		
		int facing = ((TEPlaceableItems)te).getFacing();
		int k = 0;
		k = facing * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
