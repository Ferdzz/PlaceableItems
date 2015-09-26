package com.stuntmania.placeableitems.tileentity.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEBowl;

public class TESRBowl extends TileEntitySpecialRenderer {

	IModelCustom modelEmpty = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/bowl.obj"));
	IModelCustom modelDye = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "obj/bowl_dye.obj"));
	ResourceLocation empty = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_empty.png");
	ResourceLocation black = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_black.png");
	ResourceLocation red = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_red.png");
	ResourceLocation green = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_green.png");
	ResourceLocation brown = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_brown.png");
	ResourceLocation blue = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_blue.png");
	ResourceLocation purple = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_purple.png");
	ResourceLocation cyan = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_cyan.png");
	ResourceLocation lightGray = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_light_gray.png");
	ResourceLocation gray = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_gray.png");
	ResourceLocation pink = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_pink.png");
	ResourceLocation lime = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_lime.png");
	ResourceLocation yellow = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_yellow.png");
	ResourceLocation lightBlue = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_light_blue.png");
	ResourceLocation magenta = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_magenta.png");
	ResourceLocation orange = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_orange.png");
	ResourceLocation white = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bowl/bowl_white.png");

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		TEBowl facedEntity = (TEBowl) entity;

		switch(facedEntity.getState()) {
		case 0:
			bindTexture(empty);
			break;
		case 1:
			bindTexture(black);
			break;
		case 2:
			bindTexture(red);
			break;
		case 3:
			bindTexture(green);
			break;
		case 4:
			bindTexture(brown);
			break;
		case 5:
			bindTexture(blue);
			break;
		case 6:
			bindTexture(purple);
			break;
		case 7:
			bindTexture(cyan);
			break;
		case 8:
			bindTexture(lightGray);
			break;
		case 9:
			bindTexture(gray);
			break;
		case 10:
			bindTexture(pink);
			break;
		case 11:
			bindTexture(lime);
			break;
		case 12:
			bindTexture(yellow);
			break;
		case 13:
			bindTexture(lightBlue);
			break;
		case 14:
			bindTexture(magenta);
			break;
		case 15:
			bindTexture(orange);
			break;
		case 16:
			bindTexture(white);
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
		GL11.glRotatef(180, 0, 0.5F, 0F);
		
		if(facedEntity.getState() == 0)
			modelEmpty.renderAll();
		else
			modelDye.renderAll();
		GL11.glPopMatrix();
	}
}
