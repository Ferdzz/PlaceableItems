package com.stuntmania.placeableitems.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFeather extends ModelBase
{
    ModelRenderer shaft1, shaft2, shaft3;
    ModelRenderer shaft4, shaft5, shaft6;
    ModelRenderer shaft7, shaft8, shaft9;
    ModelRenderer left1, left2, left3;
    ModelRenderer left4, left5, left6;
    ModelRenderer right1, right2, right3, right4;
    ModelRenderer right5, right6, right7;
  
  public ModelFeather()
  {
    textureWidth = 16;
    textureHeight = 16;
    
      shaft1 = new ModelRenderer(this, 0, 0);
      shaft1.addBox(0F, 0F, 0F, 2, 2, 2);
      shaft1.setRotationPoint(-5F, 24F, -6F);
      shaft1.setTextureSize(16, 16);
      shaft1.mirror = true;
      setRotation(shaft1, 1.570796F, 0F, 0F);
      shaft2 = new ModelRenderer(this, 0, 12);
      shaft2.addBox(0F, 0F, 0F, 1, 2, 2);
      shaft2.setRotationPoint(-3F, 24F, -5F);
      shaft2.setTextureSize(16, 16);
      shaft2.mirror = true;
      setRotation(shaft2, 1.570796F, 0F, 0F);
      shaft3 = new ModelRenderer(this, 0, 12);
      shaft3.addBox(0F, 0F, 0F, 1, 2, 2);
      shaft3.setRotationPoint(-2F, 24F, -4F);
      shaft3.setTextureSize(16, 16);
      shaft3.mirror = true;
      setRotation(shaft3, 1.570796F, 0F, 0F);
      shaft4 = new ModelRenderer(this, 12, 12);
      shaft4.addBox(0F, 0F, 0F, 1, 2, 1);
      shaft4.setRotationPoint(-1F, 22F, -2F);
      shaft4.setTextureSize(16, 16);
      shaft4.mirror = true;
      setRotation(shaft4, 0F, 0F, 0F);
      shaft5 = new ModelRenderer(this, 12, 12);
      shaft5.addBox(0F, 0F, 0F, 1, 2, 1);
      shaft5.setRotationPoint(0F, 22F, -1F);
      shaft5.setTextureSize(16, 16);
      shaft5.mirror = true;
      setRotation(shaft5, 0F, 0F, 0F);
      shaft6 = new ModelRenderer(this, 12, 12);
      shaft6.addBox(0F, 0F, 0F, 1, 2, 1);
      shaft6.setRotationPoint(1F, 22F, 0F);
      shaft6.setTextureSize(16, 16);
      shaft6.mirror = true;
      setRotation(shaft6, 0F, 0F, 0F);
      shaft7 = new ModelRenderer(this, 10, 12);
      shaft7.addBox(0F, 0F, 0F, 1, 2, 2);
      shaft7.setRotationPoint(2F, 22F, 1F);
      shaft7.setTextureSize(16, 16);
      shaft7.mirror = true;
      setRotation(shaft7, 0F, 0F, 0F);
      shaft8 = new ModelRenderer(this, 12, 12);
      shaft8.addBox(0F, 0F, 0F, 1, 2, 1);
      shaft8.setRotationPoint(3F, 22F, 3F);
      shaft8.setTextureSize(16, 16);
      shaft8.mirror = true;
      setRotation(shaft8, 0F, 0F, 0F);
      shaft9 = new ModelRenderer(this, 10, 12);
      shaft9.addBox(0F, 0F, 0F, 1, 2, 2);
      shaft9.setRotationPoint(4F, 22F, 4F);
      shaft9.setTextureSize(16, 16);
      shaft9.mirror = true;
      setRotation(shaft9, 0F, 0F, 0F);
      left1 = new ModelRenderer(this, 12, 0);
      left1.addBox(0F, 0F, 0F, 1, 4, 1);
      left1.setRotationPoint(-1F, 23.5F, -6F);
      left1.setTextureSize(16, 16);
      left1.mirror = true;
      setRotation(left1, 1.570796F, 0F, 0F);
      left2 = new ModelRenderer(this, 12, 6);
      left2.addBox(0F, 0F, 0F, 1, 4, 1);
      left2.setRotationPoint(0F, 23.5F, -5F);
      left2.setTextureSize(16, 16);
      left2.mirror = true;
      setRotation(left2, 1.570796F, 0F, 0F);
      left3 = new ModelRenderer(this, 12, 0);
      left3.addBox(0F, 0F, 0F, 1, 4, 1);
      left3.setRotationPoint(1F, 23.5F, -4F);
      left3.setTextureSize(16, 16);
      left3.mirror = true;
      setRotation(left3, 1.570796F, 0F, 0F);
      left4 = new ModelRenderer(this, 12, 6);
      left4.addBox(0F, 0F, 0F, 1, 4, 1);
      left4.setRotationPoint(2F, 23.5F, -3F);
      left4.setTextureSize(16, 16);
      left4.mirror = true;
      setRotation(left4, 1.570796F, 0F, 0F);
      left5 = new ModelRenderer(this, 12, 0);
      left5.addBox(0F, 0F, 0F, 1, 4, 1);
      left5.setRotationPoint(3F, 23.5F, -1F);
      left5.setTextureSize(16, 16);
      left5.mirror = true;
      setRotation(left5, 1.570796F, 0F, 0F);
      left6 = new ModelRenderer(this, 12, 6);
      left6.addBox(0F, 0F, 0F, 1, 4, 1);
      left6.setRotationPoint(4F, 23.5F, 0F);
      left6.setTextureSize(16, 16);
      left6.mirror = true;
      setRotation(left6, 1.570796F, 0F, 0F);
      right1 = new ModelRenderer(this, 0, 5);
      right1.addBox(0F, 0F, 0F, 1, 3, 1);
      right1.setRotationPoint(-1F, 23.5F, -2F);
      right1.setTextureSize(16, 16);
      right1.mirror = true;
      setRotation(right1, 1.570796F, -1.570796F, 0F);
      right2 = new ModelRenderer(this, 5, 5);
      right2.addBox(0F, 0F, 0F, 1, 3, 1);
      right2.setRotationPoint(0F, 23.5F, -1F);
      right2.setTextureSize(16, 16);
      right2.mirror = true;
      setRotation(right2, 1.570796F, -1.570796F, 0F);
      right3 = new ModelRenderer(this, 0, 5);
      right3.addBox(0F, 0F, 0F, 1, 3, 1);
      right3.setRotationPoint(1F, 23.5F, 0F);
      right3.setTextureSize(16, 16);
      right3.mirror = true;
      setRotation(right3, 1.570796F, -1.570796F, 0F);
      right4 = new ModelRenderer(this, 5, 5);
      right4.addBox(0F, 0F, 0F, 1, 3, 1);
      right4.setRotationPoint(2F, 23.5F, 1F);
      right4.setTextureSize(16, 16);
      right4.mirror = true;
      setRotation(right4, 1.570796F, -1.570796F, 0F);
      right5 = new ModelRenderer(this, 5, 5);
      right5.addBox(0F, 0F, 0F, 1, 1, 1);
      right5.setRotationPoint(2F, 23.5F, 2F);
      right5.setTextureSize(16, 16);
      right5.mirror = true;
      setRotation(right5, 1.570796F, -1.570796F, 0F);
      right6 = new ModelRenderer(this, 5, 5);
      right6.addBox(0F, 0F, 0F, 1, 1, 1);
      right6.setRotationPoint(3F, 23.5F, 3F);
      right6.setTextureSize(16, 16);
      right6.mirror = true;
      setRotation(right6, 1.570796F, -1.570796F, 0F);
      right7 = new ModelRenderer(this, 5, 5);
      right7.addBox(0F, 0F, 0F, 1, 1, 1);
      right7.setRotationPoint(4F, 23.5F, 4F);
      right7.setTextureSize(16, 16);
      right7.mirror = true;
      setRotation(right7, 1.570796F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    shaft1.render(f5);
    shaft2.render(f5);
    shaft3.render(f5);
    shaft4.render(f5);
    shaft5.render(f5);
    shaft6.render(f5);
    shaft7.render(f5);
    shaft8.render(f5);
    shaft9.render(f5);
    left1.render(f5);
    left2.render(f5);
    left3.render(f5);
    left4.render(f5);
    left5.render(f5);
    left6.render(f5);
    right1.render(f5);
    right2.render(f5);
    right3.render(f5);
    right4.render(f5);
    right5.render(f5);
    right6.render(f5);
    right7.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}