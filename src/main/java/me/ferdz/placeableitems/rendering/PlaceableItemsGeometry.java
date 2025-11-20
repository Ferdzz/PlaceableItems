package me.ferdz.placeableitems.rendering;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.ElementsModel;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

import java.util.function.Function;

public class PlaceableItemsGeometry implements IUnbakedGeometry<PlaceableItemsGeometry> {
    private final BlockModel base;

    // Store the block model for usage below.
    public PlaceableItemsGeometry(BlockModel base) {
        this.base = base;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
        BakedModel bakedBase = new ElementsModel(base.getElements()).bake(context, baker, spriteGetter, modelState, overrides, modelLocation);
        return new PlaceableItemsDynamicModel(bakedBase, context.useAmbientOcclusion(), context.isGui3d(), spriteGetter.apply(context.getMaterial("particle")));
    }


    @Override
    public void resolveParents(Function<ResourceLocation, UnbakedModel> modelGetter, IGeometryBakingContext context) {
        base.resolveParents(modelGetter);
    }
}

//    public PlaceableItemsBakedModel(List<BakedQuad> unculledFaces, Map<Direction, List<BakedQuad>> culledFaces, boolean hasAmbientOcclusion, boolean usesBlockLight, boolean isGui3d, TextureAtlasSprite particleIcon, ItemTransforms transforms, ItemOverrides overrides, RenderTypeGroup renderTypes) {

//
//    PlaceableItemsModelGeometry(List<BlockElement> list) {
//        super(list);
//    }
//
//    /**
//     * Override ModelLoaderRegistry.VanillaProxy but return a custom model builder instead
//     */
//    @Override
//    public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
//        TextureAtlasSprite particle = spriteGetter.apply(owner.resolveTexture("particle"));
//        IModelBuilder<?> builder = new Simple(new PlaceableItemsBakedModel.Builder(owner, overrides).setTexture(particle));
//        addQuads(owner, builder, bakery, spriteGetter, modelTransform, modelLocation);
//        return builder.build();
//    }
//
//    static class Simple implements IModelBuilder<Simple> {
//        final PlaceableItemsBakedModel.Builder builder;
//
//        Simple(PlaceableItemsBakedModel.Builder builder) {
//            this.builder = builder;
//        }
//
//        @Override
//        public Simple addFaceQuad(Direction facing, BakedQuad quad) {
//            builder.addFaceQuad(facing, quad);
//            return this;
//        }
//
//        @Override
//        public Simple addGeneralQuad(BakedQuad quad) {
//            builder.addGeneralQuad(quad);
//            return this;
//        }
//
//        @Override
//        public BakedModel build() {
//            return builder.build();
//        }
//    }
//}
