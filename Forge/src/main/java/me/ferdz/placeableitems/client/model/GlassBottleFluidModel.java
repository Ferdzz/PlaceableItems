package me.ferdz.placeableitems.client.model;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.client.AllVertexBoundingBox;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * An implementation of {@link FluidModel} for the {@link PlaceableItemsBlockRegistry#GLASS_BOTTLE}.
 *
 * @author Parker Hawke - Choco
 */
public class GlassBottleFluidModel extends FluidModel {

    private static final Vector3d ROTATION_POINT = new Vector3d(8.0D, 1.0D, 8.0D);
    private static final Vector3d UP_ROTATION_POINT = new Vector3d(8.0D, 5.0D, 8.0D);

    private static final AllVertexBoundingBox BOUNDS = AllVertexBoundingBox.fromAABB(new AxisAlignedBB(-2.5F / 16F, 0F / 16F, -2.5F / 16F, 2.5F / 16F, 4F / 16F, 2.5F / 16F));

    @Override
    public AllVertexBoundingBox calculateRenderBounds(BlockState state) {
        return BOUNDS.rotateAroundY(Math.toRadians((-state.get(PlaceableItemsBlock.ROTATION) * 360F / 16.0F)));
    }

    @Override
    public Vector3d getRotationPoint(BlockState state) {
        return state.get(BiPositionBlockComponent.UP) ? UP_ROTATION_POINT : ROTATION_POINT;
    }

    @Override
    public boolean shouldRender(BlockState state, Direction direction) {
        return direction != Direction.DOWN;
    }

}
