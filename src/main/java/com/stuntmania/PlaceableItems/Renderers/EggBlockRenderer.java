package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.EggModel;

public class EggBlockRenderer extends TileEntitySpecialRenderer {
	
	EggModel model = new EggModel();
	ResourceLocation textures = (new ResourceLocation(PlaceableItems.MODID + ":textures/blocks/eggBlock.png"));
	
	 @Override
     public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
             GL11.glPushMatrix();
             GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
             Minecraft.getMinecraft().renderEngine.bindTexture(textures);                       
             GL11.glPushMatrix();
             GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
             model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
             GL11.glPopMatrix();
             GL11.glPopMatrix();
     }
}
