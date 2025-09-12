package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// TODO: Allow for setting a custom UP collision shape

public class BiPositionBlockComponent extends AbstractBlockComponent {
    public static final BooleanProperty UP = BlockStateProperties.UP;

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        if (context.getClickedFace() == Direction.DOWN) {
            return blockState.setValue(UP, true);
        } else {
            return blockState.setValue(UP, false);
        }
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // Automatically shift the shape up when the UP state is true
        if (state.getValue(UP)) {
            return shape.move(0,  1 - shape.bounds().getYsize(), 0);
        } else {
            return shape;
        }
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.biposition");
    }
}
