package me.ferdz.placeableitems.blockentities.renderers;

import com.mojang.blaze3d.platform.GlStateManager;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;

public class PlaceableItemBlockEntityRenderer <T extends BlockEntity> extends BlockEntityRenderer<T> {

    public PlaceableItemBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher_1) {
        //super(blockEntityRenderDispatcher_1);
    }

    public BasicBakedModel getModel(BlockState state) {
        return (BasicBakedModel) MinecraftClient.getInstance().getBakedModelManager()
                .getModel(BlockModels.getModelId(state));
        //Renderer
    }

    public static BlockRenderManager brm = MinecraftClient.getInstance().getBlockRenderManager();


    public void translate(double x, double y, double z, int rotation)
    {
        double radius = 0.7;//0710678118654752440084436210485;
        double xOff = 0;
        double zOff = 0;
        
        switch(rotation)
        {
            case 0:
                xOff = 0.00;
                zOff = 0.00;
                break;
            case 4:
                xOff=0.00;
                zOff=-1.00;
                break;
            case 8:
                xOff=-1.00d;
                zOff=-1.00d;
                break;
            case 12: 
                xOff=-1.00d;
                zOff = 0.00d;
                break;

            case 1:
                xOff = 0.15d;
                zOff = -0.24d;
                break;
            case 5:
                xOff=-0.24d;
                zOff=-1.15d;
                break;
            case 9:
                xOff=-1.15d;
                zOff=-0.76d;
                break;
            case 13:
                xOff = -0.76d;
                zOff = 0.15d;
                break;

            case 2:
                xOff = 0.20d;
                zOff = -0.50d;
                break;
            case 6:
                xOff=-0.50d;
                zOff=-1.20d;
                break;
            case 10:
                xOff=-1.20d;
                zOff=-0.50d;
                break;
            case 14:
                xOff = -0.50d;
                zOff = 0.20d;
                break;

            case 3:
                xOff = 0.15d;
                zOff = -0.80d;
                break;
            case 7:
                xOff=-0.80d;
                zOff=-1.15d;
                break;
            case 11:
                xOff=-1.15d;
                zOff=-0.24d;
                break;
            case 15:
                xOff = -0.24d;
                zOff = 0.15d;
                break;
        }
        
        // double angle = Math.toRadians(((rotation) * 22.5D));
        // https://www.desmos.com/calculator/k2iftva5ss
        // the math should world, but nope
        // xOff = (radius *Math.cos(angle)-0.5d);
        // zOff = (radius * Math.sin(angle)-0.5d);
        xOff*=-1;
        zOff*=-1;
        //System.out.println(String.format("rotation:%s \nradians:%s \nx:%s \nz:%s", rotation,-22.5*(rotation+14), xOff, zOff));
        
        GlStateManager.translated(x + zOff, y, z + xOff);

    }   

    @Override
    public void render(T be, double x, double y, double z, float tickDelta, int arg8) {
        //SignBlockEntityRenderer
        //SkullBlockEntityRenderer
        BlockState state = be.getCachedState();
        GlStateManager.pushMatrix();
        this.bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
        this.translate(x, y, z, state.get(PlaceableItemsBlock.ROTATION));
        GlStateManager.rotatef(-((float)((Integer)state.get(PlaceableItemsBlock.ROTATION) * 360) / 16.0F), 0, 1f, 0);
        // GlStateManager.enableRescaleNormal();
        // GlStateManager.enableAlphaTest();
        // render
    //    renderManager.render(be, 1f, arg8);
        //renderManager.renderEntity(be, x, y, z, 1f);


        // MinecraftClient.getInstance().getBlockRenderManager().renderDynamic(blockState_1, float_1);
        MinecraftClient.getInstance()
            .getBlockRenderManager()
            .getModelRenderer()
            .render(state, this.getModel(state), 1f, (float)x, (float)y, (float)z);
        // GlStateManager.disableCull();
        GlStateManager.popMatrix();
    }


    
}