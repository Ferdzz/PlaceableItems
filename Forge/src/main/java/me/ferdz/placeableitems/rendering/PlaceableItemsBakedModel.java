package me.ferdz.placeableitems.rendering;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.QuadTransformer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlaceableItemsBakedModel extends SimpleBakedModel {

    private PlaceableItemsBakedModel(List<BakedQuad> p_i230059_1_, Map<Direction, List<BakedQuad>> p_i230059_2_, boolean p_i230059_3_, boolean p_i230059_4_, boolean p_i230059_5_, TextureAtlasSprite p_i230059_6_, ItemCameraTransforms p_i230059_7_, ItemOverrideList p_i230059_8_) {
        super(p_i230059_1_, p_i230059_2_, p_i230059_3_, p_i230059_4_, p_i230059_5_, p_i230059_6_, p_i230059_7_, p_i230059_8_);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        float angle = 0f;
        if (state != null && state.hasProperty(BlockStateProperties.ROTATION_16)) {
            // Magical method that transforms 0-15 indexes to equivalent rotation in 360 degrees
            switch (state.getValue(BlockStateProperties.ROTATION_16)) {
                case 0:
                    angle = 0f;
                    break;
                case 1:
                    angle = -22.5f;
                    break;
                case 2:
                    angle = -45f;
                    break;
                case 3:
                    angle = -67.5f;
                    break;
                case 4:
                    angle = -90f;
                    break;
                case 5:
                    angle = -112.5f;
                    break;
                case 6:
                    angle = -135f;
                    break;
                case 7:
                    angle = -157.5f;
                    break;
                case 8:
                    angle = -180f;
                    break;
                case 9:
                    angle = -202.5f;
                    break;
                case 10:
                    angle = -225f;
                    break;
                case 11:
                    angle = -247.5f;
                    break;
                case 12:
                    angle = -270f;
                    break;
                case 13:
                    angle = -292.5f;
                    break;
                case 14:
                    angle = -315f;
                    break;
                case 15:
                    angle = -337.5f;
                    break;
            }
        }

        // Apply a transformation matrix
        TransformationMatrix tm = new TransformationMatrix(null, new Quaternion(0, angle, 0, true), null, null);
        tm = tm.blockCenterToCorner();
        QuadTransformer transformer = new QuadTransformer(tm);
        return transformer.processMany(super.getQuads(state, side, rand));
    }

    /**
     * Most of this is copied from
     */
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
        private final List<BakedQuad> builderGeneralQuads = Lists.newArrayList();
        private final Map<Direction, List<BakedQuad>> builderFaceQuads = Maps.newEnumMap(Direction.class);
        private final ItemOverrideList builderItemOverrideList;
        private final boolean builderAmbientOcclusion;
        private TextureAtlasSprite builderTexture;
        private final boolean usesBlockLight;
        private final boolean builderGui3d;
        private final ItemCameraTransforms builderCameraTransforms;

        public Builder(net.minecraftforge.client.model.IModelConfiguration model, ItemOverrideList overrides) {
            this(model.useSmoothLighting(), model.isShadedInGui(), model.isSideLit(), model.getCameraTransforms(), overrides);
        }

        public Builder(BlockModel p_i230060_1_, ItemOverrideList p_i230060_2_, boolean p_i230060_3_) {
            this(p_i230060_1_.hasAmbientOcclusion(), p_i230060_1_.getGuiLight().lightLikeBlock(), p_i230060_3_, p_i230060_1_.getTransforms(), p_i230060_2_);
        }

        private Builder(boolean p_i230061_1_, boolean p_i230061_2_, boolean p_i230061_3_, ItemCameraTransforms p_i230061_4_, ItemOverrideList p_i230061_5_) {
            for(Direction direction : Direction.values()) {
                this.builderFaceQuads.put(direction, Lists.newArrayList());
            }

            this.builderItemOverrideList = p_i230061_5_;
            this.builderAmbientOcclusion = p_i230061_1_;
            this.usesBlockLight = p_i230061_2_;
            this.builderGui3d = p_i230061_3_;
            this.builderCameraTransforms = p_i230061_4_;
        }

        Builder addFaceQuad(Direction facing, BakedQuad quad) {
            this.builderFaceQuads.get(facing).add(quad);
            return this;
        }

        Builder addGeneralQuad(BakedQuad quad) {
            this.builderGeneralQuads.add(quad);
            return this;
        }

        public Builder setTexture(TextureAtlasSprite texture) {
            this.builderTexture = texture;
            return this;
        }

        IBakedModel build() {
            if (this.builderTexture == null) {
                throw new RuntimeException("Missing particle!");
            } else {
                // TODO: Figure out how to re-enable ambient occlusion for horse armor stand
                return new PlaceableItemsBakedModel(this.builderGeneralQuads, this.builderFaceQuads, this.builderAmbientOcclusion, this.usesBlockLight, this.builderGui3d, this.builderTexture, this.builderCameraTransforms, this.builderItemOverrideList);
            }
        }
    }
}
