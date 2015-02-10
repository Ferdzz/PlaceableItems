package com.stuntmania.placeableitems.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSaddle extends ModelBase
{
  //fields
    public ModelRenderer Base;
    public ModelRenderer Leg1;
    public ModelRenderer Leg2;
    public ModelRenderer Leg3;
    public ModelRenderer Leg4;
    public ModelRenderer Frame1;
    public ModelRenderer Frame2;
    public ModelRenderer Saddle_base;
    public ModelRenderer Strip1;
    public ModelRenderer Strip2;
    public ModelRenderer IronPlate1;
    public ModelRenderer IronPlate2;
    public ModelRenderer Saddle_base_2;
    public ModelRenderer Saddle_base_1;
  
  public ModelSaddle()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 10, 2, 16);
      Base.setRotationPoint(-5F, 8F, -8F);
      Base.setTextureSize(64, 32);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 52, 14);
      Leg1.addBox(0F, 0F, 0F, 3, 15, 3);
      Leg1.setRotationPoint(-5F, 9F, -8F);
      Leg1.setTextureSize(64, 32);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0.0872665F);
      Leg2 = new ModelRenderer(this, 52, 14);
      Leg2.addBox(0F, 0F, 0F, 3, 15, 3);
      Leg2.setRotationPoint(2F, 9.2F, -8F);
      Leg2.setTextureSize(64, 32);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, -0.0872665F);
      Leg3 = new ModelRenderer(this, 52, 14);
      Leg3.addBox(0F, 0F, 0F, 3, 15, 3);
      Leg3.setRotationPoint(2F, 9.2F, 5F);
      Leg3.setTextureSize(64, 32);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, -0.0872665F);
      Leg4 = new ModelRenderer(this, 52, 14);
      Leg4.addBox(0F, 0F, 0F, 3, 15, 3);
      Leg4.setRotationPoint(-5F, 9F, 5F);
      Leg4.setTextureSize(64, 32);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0.0872665F);
      Frame1 = new ModelRenderer(this, 0, 19);
      Frame1.addBox(3F, 0F, 0F, 1, 1, 12);
      Frame1.setRotationPoint(1F, 17F, -6F);
      Frame1.setTextureSize(64, 32);
      Frame1.mirror = true;
      setRotation(Frame1, 0F, 0F, 0F);
      Frame2 = new ModelRenderer(this, 0, 19);
      Frame2.addBox(0F, 0F, 0F, 1, 1, 12);
      Frame2.setRotationPoint(-5F, 17F, -6F);
      Frame2.setTextureSize(64, 32);
      Frame2.mirror = true;
      setRotation(Frame2, 0F, 0F, 0F);
      Saddle_base = new ModelRenderer(this, 15, 19);
      Saddle_base.addBox(0F, 0F, 0F, 10, 1, 8);
      Saddle_base.setRotationPoint(-5F, 7F, -4F);
      Saddle_base.setTextureSize(64, 32);
      Saddle_base.mirror = true;
      setRotation(Saddle_base, 0F, 0F, 0F);
      Strip1 = new ModelRenderer(this, 0, 9);
      Strip1.addBox(0F, 0F, 0F, 1, 6, 1);
      Strip1.setRotationPoint(-5.5F, 7F, -1F);
      Strip1.setTextureSize(64, 32);
      Strip1.mirror = true;
      setRotation(Strip1, 0F, 0F, 0F);
      Strip2 = new ModelRenderer(this, 0, 9);
      Strip2.addBox(0F, 0F, 0F, 1, 6, 1);
      Strip2.setRotationPoint(4.5F, 7F, -1F);
      Strip2.setTextureSize(64, 32);
      Strip2.mirror = true;
      setRotation(Strip2, 0F, 0F, 0F);
      IronPlate1 = new ModelRenderer(this, 5, 12);
      IronPlate1.addBox(0F, 0F, 0F, 1, 2, 2);
      IronPlate1.setRotationPoint(-5.5F, 13F, -1.5F);
      IronPlate1.setTextureSize(64, 32);
      IronPlate1.mirror = true;
      setRotation(IronPlate1, 0F, 0F, 0F);
      IronPlate2 = new ModelRenderer(this, 5, 12);
      IronPlate2.addBox(0F, 0F, 0F, 1, 2, 2);
      IronPlate2.setRotationPoint(4.5F, 13F, -1.5F);
      IronPlate2.setTextureSize(64, 32);
      IronPlate2.mirror = true;
      setRotation(IronPlate2, 0F, 0F, 0F);
      Saddle_base_2 = new ModelRenderer(this, 52, 0);
      Saddle_base_2.addBox(0F, 0F, 0F, 3, 1, 2);
      Saddle_base_2.setRotationPoint(-1.5F, 6F, -4F);
      Saddle_base_2.setTextureSize(64, 32);
      Saddle_base_2.mirror = true;
      setRotation(Saddle_base_2, 0F, 0F, 0F);
      Saddle_base_1 = new ModelRenderer(this, 0, 0);
      Saddle_base_1.addBox(0F, 0F, 0F, 6, 1, 2);
      Saddle_base_1.setRotationPoint(-3F, 6F, 2F);
      Saddle_base_1.setTextureSize(64, 32);
      Saddle_base_1.mirror = true;
      setRotation(Saddle_base_1, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Leg1.render(f5);
    Leg2.render(f5);
    Leg3.render(f5);
    Leg4.render(f5);
    Frame1.render(f5);
    Frame2.render(f5);
    Saddle_base.render(f5);
    Strip1.render(f5);
    Strip2.render(f5);
    IronPlate1.render(f5);
    IronPlate2.render(f5);
    Saddle_base_2.render(f5);
    Saddle_base_1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
