package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.stuntmania.placeableitems.PlaceableItems;

public class TESRIngot extends TileEntitySpecialRenderer {

	//IngotBlockModel model = new IngotBlockModel();
	ResourceLocation iron = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/iron.png");
	ResourceLocation gold = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/gold.png");
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/ingot.obj"));
	float scalex = 0.075F;
	float scaley = 0.12F;
	float scalez = 0.06F;
	float offset = 0.3F;
	float offseth = 0.07F;
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		//TEPlaceableItems ingotEntity = (TEPlaceableItems) entity;
		
		int meta = entity.getBlockMetadata();
		if(meta == 0 || meta == 2 || meta == 4 || meta == 6)
			bindTexture(iron);
		if(meta == 1 || meta == 3 || meta == 5 || meta == 7)
			bindTexture(gold);
		/*
        int facing = ingotEntity.getFacing();
        int k = 0;
        k = facing * 90;
		*/
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float) y + offseth , (float) z + 0.5F);
        if (meta >= 2)
        	GL11.glTranslatef(0.0F, 0.0F, offset / 2);
        //GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        GL11.glScaled(scalex, scaley, scalez);
        
        model.renderAll();
        GL11.glPopMatrix();

        if (meta >= 2) {
        	GL11.glPushMatrix();
        	GL11.glTranslatef((float)x + 0.5F, (float) y + offseth , (float) z + 0.5F);
        	GL11.glTranslatef(0.0F, 0.0F, offset / -2);
        	//GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        	GL11.glScaled(scalex, scaley, scalez);
        	model.renderAll();
        	GL11.glPopMatrix();
    	}
        
        if (meta >= 4) {
        	GL11.glPushMatrix();
        	GL11.glTranslatef((float)x + 0.5F, (float)y + offseth, (float)z + 0.5F);
        	GL11.glTranslatef((offset - 0.02F) / 2, 0.125F, 0.0F);
        	GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        	GL11.glScaled(scalex, scaley, scalez);
        	model.renderAll();
        	GL11.glPopMatrix();
        }
        
        if (meta >= 6) {
        	GL11.glPushMatrix();
        	GL11.glTranslatef((float)x + 0.5F, (float)y + offseth, (float)z + 0.5F);
        	GL11.glTranslatef((offset - 0.02F) / -2, 0.125F, 0.0F);
        	GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        	GL11.glScaled(scalex, scaley, scalez);
        	model.renderAll();
        	GL11.glPopMatrix();
        }
	}
}
