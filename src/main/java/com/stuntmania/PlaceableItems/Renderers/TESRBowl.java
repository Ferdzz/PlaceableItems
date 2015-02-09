package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.ModelBowl;
import com.stuntmania.PlaceableItems.TileEntities.TEBowl;

public class TESRBowl extends TileEntitySpecialRenderer {

	ModelBowl model = new ModelBowl();
	ResourceLocation empty = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/woodBowlBlock.png");
	ResourceLocation black = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/blackBowlBlock.png");
	ResourceLocation red = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/redBowlBlock.png");
	ResourceLocation green = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/greenBowlBlock.png");
	ResourceLocation brown = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/brownBowlBlock.png");
	ResourceLocation blue = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/blueBowlBlock.png");
	ResourceLocation purple = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/purpleBowlBlock.png");
	ResourceLocation cyan = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/cyanBowlBlock.png");
	ResourceLocation lightGray = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/lightGrayBowlBlock.png");
	ResourceLocation gray = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/grayBowlBlock.png");
	ResourceLocation pink = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/pinkBowlBlock.png");
	ResourceLocation lime = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/limeBowlBlock.png");
	ResourceLocation yellow = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/yellowBowlBlock.png");
	ResourceLocation lightBlue = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/lightBlueBowlBlock.png");
	ResourceLocation magenta = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/magentaBowlBlock.png");
	ResourceLocation orange = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/orangeBowlBlock.png");
	ResourceLocation white = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/whiteBowlBlock.png");

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
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        
        int facing = facedEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        model.Shape1.render(0.0625F);
        model.Shape2.render(0.0625F);
        model.Shape3.render(0.0625F);
        model.Shape5.render(0.0625F);
        model.Shape7.render(0.0625F);
        model.Shape8.render(0.0625F);
        model.Shape9.render(0.0625F);
        model.Shape10.render(0.0625F);
        model.Shape11.render(0.0625F);
        model.Shape12.render(0.0625F);
        model.Shape13.render(0.0625F);
        model.Shape14.render(0.0625F);
        model.Shape15.render(0.0625F);
        model.Shape16.render(0.0625F);
        model.Shape17.render(0.0625F);
        model.Shape18.render(0.0625F);
        model.Shape19.render(0.0625F);
        model.Shape20.render(0.0625F);
        model.Shape21.render(0.0625F);
        model.Shape22.render(0.0625F);
        model.Shape23.render(0.0625F);
        model.Shape24.render(0.0625F);
        model.Shape25.render(0.0625F);
        if(facedEntity.getState() != 0)
        	model.Content.render(0.0625F);
        GL11.glPopMatrix();
	}
}
