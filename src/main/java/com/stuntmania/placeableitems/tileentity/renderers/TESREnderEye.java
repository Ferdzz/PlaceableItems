package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.model.ModelEnder;
import com.stuntmania.placeableitems.tileentity.TEPlaceableItems;

public class TESREnderEye extends TileEntitySpecialRenderer {

	ModelEnder model = new ModelEnder();
	ResourceLocation enderPearl = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/ender_eye.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TEPlaceableItems enderPearlEntity = (TEPlaceableItems) entity;
		
		bindTexture(enderPearl);
		
        GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
        int facing = enderPearlEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        model.Shape1.render(0.0625F);
        GL11.glPopMatrix();
	}
}
