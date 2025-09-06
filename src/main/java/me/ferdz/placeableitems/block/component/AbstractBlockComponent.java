package me.ferdz.placeableitems.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractBlockComponent implements IBlockComponent {

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // Nothing to do in default implementation
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        return blockState;
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return null;
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return null;
    }
}
