package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.PlaceableItemsTileEntity;

public class IngotBlockRenderer extends TileEntitySpecialRenderer {

	//IngotBlockModel model = new IngotBlockModel();
	ResourceLocation iron = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/ironBlock.png");
	ResourceLocation gold = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/goldBlock.png");
	IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(PlaceableItems.MODID, "Ingot.obj"));
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		PlaceableItemsTileEntity ingotEntity = (PlaceableItemsTileEntity) entity;
		
		if(entity.getBlockMetadata() == 0)
			bindTexture(iron);
		if(entity.getBlockMetadata() == 1)
			bindTexture(gold);
		
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F, (float) y + 0.02F , (float) z + 0.5F);
        GL11.glScaled(0.055f, 0.055f, 0.055f);
        
        int facing = ingotEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        model.renderAll();
        GL11.glPopMatrix();
	}
}
