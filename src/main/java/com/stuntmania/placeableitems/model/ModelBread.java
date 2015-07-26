package com.stuntmania.placeableitems.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Made by PrimoSupremeX
//for the Placeable Items mod

public class ModelBread extends ModelBase {
	
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape4;
	ModelRenderer Shape3;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ModelBread() {
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-4F, 0F, -7F, 8, 5, 14);
		Shape1.setRotationPoint(0F, 19F, 0F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 44, 0);
		Shape2.addBox(-3F, 0F, -7F, 6, 1, 14);
		Shape2.setRotationPoint(0F, 18F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 84, 0);
		Shape4.addBox(0F, 0F, -6F, 1, 1, 12);
		Shape4.setRotationPoint(3F, 18F, 0F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 84, 0);
		Shape3.addBox(0F, 0F, -6F, 1, 1, 12);
		Shape3.setRotationPoint(-4F, 18F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 19);
		Shape5.addBox(-2F, 0F, -5F, 4, 1, 10);
		Shape5.setRotationPoint(0F, 17.5F, 0F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 28, 19);
		Shape6.addBox(0F, 0F, -3F, 1, 1, 6);
		Shape6.setRotationPoint(-3F, 17.5F, 0F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 28, 19);
		Shape7.addBox(0F, 0F, -3F, 1, 1, 6);
		Shape7.setRotationPoint(2F, 17.5F, 0F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 30, 28);
		Shape8.addBox(-2F, 0F, 0F, 4, 1, 1);
		Shape8.setRotationPoint(0F, 17F, -2F);
		Shape8.setTextureSize(128, 128);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 30, 28);
		Shape9.addBox(-2F, 0F, 0F, 4, 1, 1);
		Shape9.setRotationPoint(0F, 17F, 1F);
		Shape9.setTextureSize(128, 128);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape4.render(f5);
		Shape3.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
