package me.ferdz.placeableitems.rendering;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.IModelBuilder;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.ModelLoaderRegistry;

import java.util.List;
import java.util.function.Function;

public class PlaceableItemsModelGeometry extends ModelLoaderRegistry.VanillaProxy {

    PlaceableItemsModelGeometry(List<BlockElement> list) {
        super(list);
    }

    /**
     * Override ModelLoaderRegistry.VanillaProxy but return a custom model builder instead
     */
    @Override
    public BakedModel bake(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides, ResourceLocation modelLocation) {
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
        public BakedModel build() {
            return builder.build();
        }
    }
}
