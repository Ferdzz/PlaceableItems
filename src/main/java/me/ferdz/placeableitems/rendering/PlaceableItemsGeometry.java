package me.ferdz.placeableitems.rendering;

import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

import java.util.List;
import java.util.function.Function;

public class PlaceableItemsGeometry implements IUnbakedGeometry<PlaceableItemsGeometry> {
    private final BlockModel base;

    // Store the block model for usage below.
    public PlaceableItemsGeometry(BlockModel base) {
        this.base = base;
    }

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker modelBaker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, List<ItemOverride> list) {
//        BakedModel test = new SimpleBakedModel(base.getElements())
//        BakedModel bakedBase = new ElementsModel(base.getElements()).bake(context, baker, spriteGetter, modelState, overrides);
        // TODO: Chjeck
        return new PlaceableItemsDynamicModel(
                base.bake(modelBaker, spriteGetter, modelState), context.useAmbientOcclusion(), context.isGui3d(),
                spriteGetter.apply(context.getMaterial("particle"))
        );
    }

    @Override
    public void resolveDependencies(UnbakedModel.Resolver modelGetter, IGeometryBakingContext context) {
        base.resolveDependencies(modelGetter);
    }

}