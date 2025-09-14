package me.ferdz.placeableitems.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public abstract class AbstractBlockComponent implements IBlockComponent {

    public static class NotImplementedException extends Exception { }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws AbstractBlockComponent.NotImplementedException {
        throw new NotImplementedException();
    }

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

    /**
     * {@link Block#updateEntityAfterFallOn(BlockGetter, Entity)}}
     */
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) throws AbstractBlockComponent.NotImplementedException {
        throw new NotImplementedException();
    }

    /**
     * {@link Block#fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance)}
     */
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) throws AbstractBlockComponent.NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) { }
}
