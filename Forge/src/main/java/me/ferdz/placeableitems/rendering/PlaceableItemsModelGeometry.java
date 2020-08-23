package me.ferdz.placeableitems.rendering;

import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelBuilder;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.util.List;
import java.util.function.Function;

public class PlaceableItemsModelGeometry extends ModelLoaderRegistry.VanillaProxy {

    PlaceableItemsModelGeometry(List<BlockPart> list) {
        super(list);
    }

    /**
     * Override ModelLoaderRegistry.VanillaProxy but return a custom model builder instead
     */
    @Override
    public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform, ItemOverrideList overrides, ResourceLocation modelLocation) {
        TextureAtlasSprite particle = spriteGetter.apply(owner.resolveTexture("particle"));
        IModelBuilder<?> builder = new Simple(new PlaceableItemsBakedModel.Builder(owner, overrides).setTexture(particle));
        addQuads(owner, builder, bakery, spriteGetter, modelTransform, modelLocation);
        return builder.build();
    }

    static class Simple implements IModelBuilder<Simple> {
        final PlaceableItemsBakedModel.Builder builder;

        Simple(PlaceableItemsBakedModel.Builder builder) {
            this.builder = builder;
        }

        @Override
        public PlaceableItemsModelGeometry.Simple addFaceQuad(Direction facing, BakedQuad quad) {
            builder.addFaceQuad(facing, quad);
            return this;
        }

        @Override
        public PlaceableItemsModelGeometry.Simple addGeneralQuad(BakedQuad quad) {
            builder.addGeneralQuad(quad);
            return this;
        }

        @Override
        public IBakedModel build() {
            return builder.build();
        }
    }
}
