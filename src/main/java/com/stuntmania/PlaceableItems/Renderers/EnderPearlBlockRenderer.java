package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.EnderModel;
import com.stuntmania.PlaceableItems.TileEntities.PlaceableItemsTileEntity;

public class EnderPearlBlockRenderer extends TileEntitySpecialRenderer
{
	EnderModel model = new EnderModel();
	ResourceLocation enderPearl = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/enderPearl.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale)
	{
		PlaceableItemsTileEntity enderPearlEntity = (PlaceableItemsTileEntity) entity;
		
		bindTexture(enderPearl);
		
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float) y + 0.02F , (float) z + 0.5F);
        GL11.glScaled(0.055f, 0.055f, 0.055f);
        
        int facing = enderPearlEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        model.Shape1.render(0.0625F);
        GL11.glPopMatrix();
	}
}
