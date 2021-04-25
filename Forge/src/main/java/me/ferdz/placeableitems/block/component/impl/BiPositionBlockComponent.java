package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
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

@WikiBlockComponentDefinition(description = "This block can be placed both at the top and bottom of another block")
public class BiPositionBlockComponent extends AbstractBlockComponent {
    public static final BooleanProperty UP = BlockStateProperties.UP;

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context, BlockState blockState) {
        if (context.getClickedFace() == Direction.DOWN) {
            return blockState.setValue(UP, true);
        } else {
            return blockState.setValue(UP, false);
        }
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        // Automatically shift the shape up when the UP state is true
        if (state.getValue(UP)) {
            return shape.move(0,  1 - shape.bounds().getYsize(), 0);
        } else {
            return shape;
        }
    }
}
