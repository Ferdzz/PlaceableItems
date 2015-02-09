package com.stuntmania.PlaceableItems.Renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Models.ModelArmorStand;
import com.stuntmania.PlaceableItems.TileEntities.TEHorseArmorStand;

public class TESRHorseArmorStand extends TileEntitySpecialRenderer implements IItemRenderer {

	ModelArmorStand model = new ModelArmorStand();
	ResourceLocation empty = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/horse_armor_empty.png"); // 0
	ResourceLocation iron = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/horse_armor_iron.png"); // 1
	ResourceLocation gold = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/horse_armor_gold.png"); // 2
	ResourceLocation diamond = new ResourceLocation(PlaceableItems.MODID, "textures/blocks/horse_armor_diamond.png"); // 3

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float p_147500_8_) {
		TEHorseArmorStand facedEntity = (TEHorseArmorStand) entity;
		
		switch (entity.getBlockMetadata()) {
		case 0:
			bindTexture(empty);
			break;
		case 1:
			bindTexture(iron);
			break;
		case 2:
			bindTexture(gold);
			break;
		case 3:
			bindTexture(diamond);
			break;
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);

		int facing = facedEntity.getFacing();
		int k = 0;
		k = facing * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180, 0, 1F, 0);

		model.mouthBottom.render(0.0625F);
		model.mouthTop.render(0.0625F);
		model.horseLeftEar.render(0.0625F);
		model.horseRightEar.render(0.0625F);
		model.backLeftLeg.render(0.0625F);
		model.backLeftShin.render(0.0625F);
		model.backLeftHoof.render(0.0625F);
		model.backRightLeg.render(0.0625F);
		model.backRightShin.render(0.0625F);
		model.backRightHoof.render(0.0625F);
		model.frontLeftLeg.render(0.0625F);
		model.frontLeftShin.render(0.0625F);
		model.frontLeftHoof.render(0.0625F);
		model.frontRightLeg.render(0.0625F);
		model.frontRightShin.render(0.0625F);
		model.frontRightHoof.render(0.0625F);
		model.body.render(0.0625F);
		model.tailBase.render(0.0625F);
		model.tailMiddle.render(0.0625F);
		model.tailTip.render(0.0625F);
		model.neck.render(0.0625F);
		model.mane.render(0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		bindTexture(empty);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) 0 + 0.5F, (float) 0 + 0.75F, (float) 0 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glScalef(0.65F, 0.65F, 0.65F);
		GL11.glRotatef(180, 0, 1, 0);

		model.mouthBottom.render(0.0625F);
		model.mouthTop.render(0.0625F);
		model.horseLeftEar.render(0.0625F);
		model.horseRightEar.render(0.0625F);
		model.backLeftLeg.render(0.0625F);
		model.backLeftShin.render(0.0625F);
		model.backLeftHoof.render(0.0625F);
		model.backRightLeg.render(0.0625F);
		model.backRightShin.render(0.0625F);
		model.backRightHoof.render(0.0625F);
		model.frontLeftLeg.render(0.0625F);
		model.frontLeftShin.render(0.0625F);
		model.frontLeftHoof.render(0.0625F);
		model.frontRightLeg.render(0.0625F);
		model.frontRightShin.render(0.0625F);
		model.frontRightHoof.render(0.0625F);
		model.body.render(0.0625F);
		model.tailBase.render(0.0625F);
		model.tailMiddle.render(0.0625F);
		model.tailTip.render(0.0625F);
		model.neck.render(0.0625F);
		model.mane.render(0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
