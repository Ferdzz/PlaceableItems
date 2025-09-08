package me.ferdz.placeableitems.block.component;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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

public interface IBlockComponent {

    /**
     * {@link Block#useItemOn(ItemStack, BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult)}
     */
    ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws AbstractBlockComponent.NotImplementedException;

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

    /**
     * {@link Block#animateTick(BlockState, Level, BlockPos, RandomSource)}
     */
    void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random);

    // TODO:
    /**
     * @return A localized description of this component to be displayed as an item tooltip
     */
    public MutableComponent getDescription(ItemStack itemStack);
}
