package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.IngotBlockModel;

public class IngotBlockRenderer extends TileEntitySpecialRenderer {

	IngotBlockModel model = new IngotBlockModel();
	ResourceLocation iron = new ResourceLocation(PlaceableItems.MODID, "ironBlock.png");
	ResourceLocation gold = new ResourceLocation(PlaceableItems.MODID, "goldBlock.png");
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		if(entity.getBlockMetadata() == 0)
			bindTexture(iron);
		if(entity.getBlockMetadata() == 1)
			bindTexture(gold);
		
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        this.model.Shape1.render(0.0625F);
        this.model.Shape2.render(0.0625F);
        this.model.Shape3.render(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
	}
}
