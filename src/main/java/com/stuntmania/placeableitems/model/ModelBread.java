package com.stuntmania.placeableitems.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * bread - Undefined
 * Created using Tabula 5.0.0
 */
public class ModelBread extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;
    public ModelRenderer shape9;
    public ModelRenderer shape10;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape15;

    public ModelBread() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.shape9 = new ModelRenderer(this, 1, 40);
        this.shape9.setRotationPoint(-7.0F, 23.0F, 1.0F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 11, 1, 1, 0.0F);
        this.shape15 = new ModelRenderer(this, 1, 58);
        this.shape15.setRotationPoint(-4.0F, 23.0F, 6.0F);
        this.shape15.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.shape8 = new ModelRenderer(this, 1, 36);
        this.shape8.setRotationPoint(-6.0F, 23.0F, 0.0F);
        this.shape8.addBox(0.0F, 0.0F, 0.0F, 11, 1, 1, 0.0F);
        this.shape12 = new ModelRenderer(this, 1, 49);
        this.shape12.setRotationPoint(-6.0F, 23.0F, 4.0F);
        this.shape12.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1, 0.0F);
        this.shape10 = new ModelRenderer(this, 1, 44);
        this.shape10.setRotationPoint(-7.0F, 23.0F, 2.0F);
        this.shape10.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
        this.shape1 = new ModelRenderer(this, 1, 4);
        this.shape1.setRotationPoint(0.0F, 23.0F, -7.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
        this.shape2 = new ModelRenderer(this, 1, 8);
        this.shape2.setRotationPoint(-2.0F, 23.0F, -6.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1, 0.0F);
        this.shape7 = new ModelRenderer(this, 1, 31);
        this.shape7.setRotationPoint(-6.0F, 23.0F, -1.0F);
        this.shape7.addBox(0.0F, 0.0F, 0.0F, 12, 1, 1, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 54);
        this.shape13.setRotationPoint(-5.0F, 23.0F, 5.0F);
        this.shape13.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
        this.shape6 = new ModelRenderer(this, 1, 27);
        this.shape6.setRotationPoint(-5.0F, 23.0F, -2.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 12, 1, 1, 0.0F);
        this.shape5 = new ModelRenderer(this, 1, 22);
        this.shape5.setRotationPoint(-4.0F, 23.0F, -3.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 11, 1, 1, 0.0F);
        this.shape11 = new ModelRenderer(this, 1, 61);
        this.shape11.setRotationPoint(-6.0F, 23.0F, 3.0F);
        this.shape11.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.shape3 = new ModelRenderer(this, 1, 12);
        this.shape3.setRotationPoint(-3.0F, 23.0F, -5.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 9, 1, 1, 0.0F);
        this.shape4 = new ModelRenderer(this, 1, 17);
        this.shape4.setRotationPoint(-3.0F, 23.0F, -4.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape9.render(f5);
        this.shape15.render(f5);
        this.shape8.render(f5);
        this.shape12.render(f5);
        this.shape10.render(f5);
        this.shape1.render(f5);
        this.shape2.render(f5);
        this.shape7.render(f5);
        this.shape13.render(f5);
        this.shape6.render(f5);
        this.shape5.render(f5);
        this.shape11.render(f5);
        this.shape3.render(f5);
        this.shape4.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
