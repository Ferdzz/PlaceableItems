package com.stuntmania.placeableitems.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelClay extends ModelBase {
	// fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	
	public ModelClay() {
		textureWidth = 131;
		textureHeight = 131;
		
		Shape1 = new ModelRenderer(this, 45, 27);
		Shape1.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape1.setRotationPoint(-1F, 23F, -1F);
		Shape1.setTextureSize(131, 131);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 18);
		Shape2.addBox(0F, 0F, 0F, 5, 1, 5);
		Shape2.setRotationPoint(-2.5F, 22F, -2.5F);
		Shape2.setTextureSize(131, 131);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 0F, 4, 1, 4);
		Shape3.setRotationPoint(-2F, 22.5F, -2F);
		Shape3.setTextureSize(131, 131);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 25);
		Shape4.addBox(0F, 0F, 0F, 6, 1, 6);
		Shape4.setRotationPoint(-3F, 21F, -3F);
		Shape4.setTextureSize(131, 131);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 35, 0);
		Shape5.addBox(0F, 0F, 0F, 7, 3, 7);
		Shape5.setRotationPoint(-3.5F, 18F, -3.5F);
		Shape5.setTextureSize(131, 131);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 39, 11);
		Shape6.addBox(0F, 0F, 0F, 6, 2, 6);
		Shape6.setRotationPoint(-3F, 16.5F, -3F);
		Shape6.setTextureSize(131, 131);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 43, 20);
		Shape7.addBox(0F, 0F, 0F, 5, 1, 5);
		Shape7.setRotationPoint(-2.5F, 16F, -2.5F);
		Shape7.setTextureSize(131, 131);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 26, 25);
		Shape8.addBox(0F, 0F, 0F, 4, 1, 4);
		Shape8.setRotationPoint(-2F, 15.5F, -2F);
		Shape8.setTextureSize(131, 131);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 45, 27);
		Shape9.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape9.setRotationPoint(-1F, 15F, -1F);
		Shape9.setTextureSize(131, 131);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 0, 10);
		Shape10.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape10.setRotationPoint(5F, 23F, -8F);
		Shape10.setTextureSize(131, 131);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 45, 27);
		Shape11.addBox(0F, 0F, 0F, 2, 1, 2);
		Shape11.setRotationPoint(6F, 23F, -7F);
		Shape11.setTextureSize(131, 131);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 19, 0);
		Shape12.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape12.setRotationPoint(7F, 23F, -5F);
		Shape12.setTextureSize(131, 131);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 0, 6);
		Shape13.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape13.setRotationPoint(7F, 22F, -8F);
		Shape13.setTextureSize(131, 131);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 14);
		Shape14.addBox(0F, 0F, 0F, 3, 1, 2);
		Shape14.setRotationPoint(-6F, 23F, 5F);
		Shape14.setTextureSize(131, 131);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
	
}
