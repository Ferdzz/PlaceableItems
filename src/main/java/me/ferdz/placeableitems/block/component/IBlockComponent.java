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

public interface IBlockComponent {
    /**
     * {@link Block#createBlockStateDefinition(StateDefinition.Builder)}
     */
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

    /**
     * {@link Block#getStateForPlacement(BlockPlaceContext)}
     */
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState);

    /**
     * {@link Block#getShape(BlockState, BlockGetter, BlockPos, CollisionContext)}
     */
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);

    // TODO:
    /**
     * @return A localized description of this component to be displayed as an item tooltip
     */
    public MutableComponent getDescription(ItemStack itemStack);
}
