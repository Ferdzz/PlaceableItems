package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.ModelBucketFilled;
import com.stuntmania.PlaceableItems.TileEntities.TEBucket;

public class TESRBucket extends TileEntitySpecialRenderer {

    ModelBucketFilled filledModel = new ModelBucketFilled();
	ResourceLocation empty = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucket.png");
	ResourceLocation water = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucketWater.png");
	ResourceLocation lava = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/bucketLava.png");
    
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
	TEBucket facedEntity = (TEBucket) entity;
	
	switch (facedEntity.getBlockMetadata()) {
	case 0:
	    bindTexture(empty);
	    break;
	case 1:
	    bindTexture(water);
	    break;
	case 2:
	    bindTexture(lava);
	    break;
	}
	
	GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        
        int facing = facedEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
        switch (facedEntity.getBlockMetadata()) {
        case 0:
            filledModel.Shape1.render(0.0625F);
            filledModel.Shape2.render(0.0625F);
            filledModel.Shape3.render(0.0625F);
            filledModel.Shape4.render(0.0625F);
            filledModel.Shape5.render(0.0625F);
            filledModel.Shape6.render(0.0625F);
            filledModel.Shape7.render(0.0625F);
            filledModel.Shape8.render(0.0625F);
            break;
        case 1:
            filledModel.Shape1.render(0.0625F);
            filledModel.Shape2.render(0.0625F);
            filledModel.Shape3.render(0.0625F);
            filledModel.Shape4.render(0.0625F);
            filledModel.Shape5.render(0.0625F);
            filledModel.Shape6.render(0.0625F);
            filledModel.Shape7.render(0.0625F);
            filledModel.Shape8.render(0.0625F);
            filledModel.Shape9.render(0.0625F);
            break;
        case 2:
            filledModel.Shape1.render(0.0625F);
            filledModel.Shape2.render(0.0625F);
            filledModel.Shape3.render(0.0625F);
            filledModel.Shape4.render(0.0625F);
            filledModel.Shape5.render(0.0625F);
            filledModel.Shape6.render(0.0625F);
            filledModel.Shape7.render(0.0625F);
            filledModel.Shape8.render(0.0625F);
            filledModel.Shape9.render(0.0625F);
            break;
        }
        GL11.glPopMatrix();
    }
}
