package me.ferdz.placeableitems.rendering;

import com.mojang.math.Transformation;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.QuadTransformers;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.util.List;
import java.util.Map;

//public class PlaceableItemsBakedModel extends SimpleBakedModel {
//    public PlaceableItemsBakedModel(List<BakedQuad> unculledFaces, Map<Direction, List<BakedQuad>> culledFaces, boolean hasAmbientOcclusion, boolean usesBlockLight, boolean isGui3d, TextureAtlasSprite particleIcon, ItemTransforms transforms, ItemOverrides overrides, RenderTypeGroup renderTypes) {
//        super(unculledFaces, culledFaces, hasAmbientOcclusion, usesBlockLight, isGui3d, particleIcon, transforms, overrides, renderTypes);
//    }
//
//    @Override
//    public List<BakedQuad> getQuads(@org.jetbrains.annotations.Nullable BlockState state, @org.jetbrains.annotations.Nullable Direction direction, @NotNull RandomSource random) {
//        float angle = 0f;
//        if (state != null && state.hasProperty(BlockStateProperties.ROTATION_16)) {
//            // Magical method that transforms 0-15 indexes to equivalent rotation in 360 degrees
//            angle = state.getValue(BlockStateProperties.ROTATION_16) * -22.5f;
//        }
//
//        // Apply a transformation matrix
//        Transformation tm = new Transformation(null, new Quaternionf(0, angle, 0, 0), null, null);
//        tm = tm.blockCenterToCorner();
//        return QuadTransformers.applying(tm).process(super.getQuads(state, direction, random));
//    }

//
//    /**
//     * Most of this is copied from
//     */
//    @OnlyIn(Dist.CLIENT)
//    public static class Builder {
//        private final List<BakedQuad> builderGeneralQuads = Lists.newArrayList();
//        private final Map<Direction, List<BakedQuad>> builderFaceQuads = Maps.newEnumMap(Direction.class);
//        private final ItemOverrides builderItemOverrideList;
//        private final boolean builderAmbientOcclusion;
//        private TextureAtlasSprite builderTexture;
//        private final boolean usesBlockLight;
//        private final boolean builderGui3d;
//        private final ItemTransforms builderCameraTransforms;
//
//        public Builder(net.minecraftforge.client.model.IModelConfiguration model, ItemOverrides overrides) {
//            this(model.useSmoothLighting(), model.isShadedInGui(), model.isSideLit(), model.getCameraTransforms(), overrides);
//        }
//
//        public Builder(BlockModel p_i230060_1_, ItemOverrides p_i230060_2_, boolean p_i230060_3_) {
//            this(p_i230060_1_.hasAmbientOcclusion(), p_i230060_1_.getGuiLight().lightLikeBlock(), p_i230060_3_, p_i230060_1_.getTransforms(), p_i230060_2_);
//        }
//
//        private Builder(boolean p_i230061_1_, boolean p_i230061_2_, boolean p_i230061_3_, ItemTransforms p_i230061_4_, ItemOverrides p_i230061_5_) {
//            for(Direction direction : Direction.values()) {
//                this.builderFaceQuads.put(direction, Lists.newArrayList());
//            }
//
//            this.builderItemOverrideList = p_i230061_5_;
//            this.builderAmbientOcclusion = p_i230061_1_;
//            this.usesBlockLight = p_i230061_2_;
//            this.builderGui3d = p_i230061_3_;
//            this.builderCameraTransforms = p_i230061_4_;
//        }
//
//        Builder addFaceQuad(Direction facing, BakedQuad quad) {
//            this.builderFaceQuads.get(facing).add(quad);
//            return this;
//        }
//
//        Builder addGeneralQuad(BakedQuad quad) {
//            this.builderGeneralQuads.add(quad);
//            return this;
//        }
//
//        public Builder setTexture(TextureAtlasSprite texture) {
//            this.builderTexture = texture;
//            return this;
//        }
//
//        BakedModel build() {
//            if (this.builderTexture == null) {
//                throw new RuntimeException("Missing particle!");
//            } else {
//                // TODO: Figure out how to re-enable ambient occlusion for horse armor stand
//                return new PlaceableItemsBakedModel(this.builderGeneralQuads, this.builderFaceQuads, this.builderAmbientOcclusion, this.usesBlockLight, this.builderGui3d, this.builderTexture, this.builderCameraTransforms, this.builderItemOverrideList);
//            }
//        }
//    }
//}
