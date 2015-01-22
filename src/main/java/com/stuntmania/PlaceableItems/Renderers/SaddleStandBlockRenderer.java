package com.stuntmania.PlaceableItems.Renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.ModelSaddle;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class SaddleStandBlockRenderer extends TileEntitySpecialRenderer implements IItemRenderer {

	ResourceLocation texture = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/saddleStand.png");
	ModelSaddle model = new ModelSaddle();

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		SaddleStandTileEntity facedEntity = (SaddleStandTileEntity) entity;
		
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		
        int facing = facedEntity.getFacing();
        int k = 0;
        k = facing * 90;
        GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
        
		model.Base.render(0.0625F);
		model.Frame1.render(0.0625F);
		model.Frame2.render(0.0625F);
		model.Leg1.render(0.0625F);
		model.Leg2.render(0.0625F);
		model.Leg3.render(0.0625F);
		model.Leg4.render(0.0625F);

		if (entity.getBlockMetadata() == 1) {
			// Draw with saddle
			model.IronPlate1.render(0.0625F);
			model.IronPlate2.render(0.0625F);
			model.Saddle_base.render(0.0625F);
			model.Saddle_base_2.render(0.0625F);
			model.Strip1.render(0.0625F);
			model.Strip2.render(0.0625F);
		}
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		bindTexture(texture);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) 0 + 0.5F, (float) 0 + 1.5F, (float) 0 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		model.Base.render(0.0625F);
		model.Frame1.render(0.0625F);
		model.Frame2.render(0.0625F);
		model.Leg1.render(0.0625F);
		model.Leg2.render(0.0625F);
		model.Leg3.render(0.0625F);
		model.Leg4.render(0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
}
