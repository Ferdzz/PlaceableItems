package me.ferdz.placeableitems.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Transformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsDynamicModel implements IDynamicBakedModel {
    private final BakedModel base;
    private final boolean useAmbientOcclusion;
    private final boolean isGui3d;
    private final TextureAtlasSprite particleIcon;

    public PlaceableItemsDynamicModel(BakedModel base, boolean useAmbientOcclusion, boolean isGui3d, TextureAtlasSprite particleIcon) {
        this.base = base;
        this.useAmbientOcclusion = useAmbientOcclusion;
        this.isGui3d = isGui3d;
        this.particleIcon = particleIcon;
    }

    // other override methods here

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource rand, ModelData extraData, @Nullable RenderType renderType) {
        List<BakedQuad> quads = new ArrayList<>();
        // Add the base model's quads. Can also do something different with the quads here, depending on what you need.
        quads.addAll(base.getQuads(state, side, rand, extraData, renderType));

        if (state != null && state.hasProperty(BlockStateProperties.ROTATION_16)) {
            float angle = state.getValue(BlockStateProperties.ROTATION_16) * -22.5f;

            Transformation tm = new Transformation(null, Axis.YP.rotationDegrees(angle), null, null);
            tm = tm.blockCenterToCorner();
            quads = QuadTransformers.applying(tm).process(quads);
        }

        return quads;
    }

    // Apply the base model's transforms to our model as well.
    @Override
    public BakedModel applyTransform(ItemDisplayContext transformType, PoseStack poseStack, boolean applyLeftHandTransform) {
        return base.applyTransform(transformType, poseStack, applyLeftHandTransform);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return useAmbientOcclusion;
    }

    @Override
    public boolean isGui3d() {
        return isGui3d;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return particleIcon;
    }

    @Override
    public ItemOverrides getOverrides() {
        return null;
    }
}


