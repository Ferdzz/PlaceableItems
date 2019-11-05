package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

// TODO: Allow for setting a custom UP collision shape

public class BiPositionBlockComponent extends AbstractBlockComponent {

    private static final BooleanProperty UP = Properties.UP;
    
    @Override
    public void fillStateContainer(StateFactory.Builder<Block, BlockState> builder) {
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(ItemPlacementContext context, BlockState blockState) {
        if(context.getPlayer().pitch > 25f) {
        //if (context.getPlayerLookDirection() == Direction.DOWN) {
            return blockState.with(UP, false);
        } else {
            return blockState.with(UP, true);
        }
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockView worldIn, BlockPos pos, EntityContext context) {
        // Automatically shift the shape up when the UP state is true
        if (state.get(UP)) { //CauldronBlock
            return shape.offset(0,  1 - shape.getBoundingBox().getYSize(), 0);
        } else {
            return shape;
        }
    }
}
