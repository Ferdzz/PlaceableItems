package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

// TODO: Allow for setting a custom UP collision shape

public class BiPositionBlockComponent extends AbstractBlockComponent {
    private static final BooleanProperty UP = BlockStateProperties.UP;

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context, BlockState blockState) {
        if (context.getFace() == Direction.DOWN) {
            return blockState.with(UP, true);
        } else {
            return blockState.with(UP, false);
        }
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        // Automatically shift the shape up when the UP state is true
        if (state.get(UP)) {
            return shape.withOffset(0,  1 - shape.getBoundingBox().getYSize(), 0);
        } else {
            return shape;
        }
    }
}
