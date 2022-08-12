package me.ferdz.placeableitems.block.component;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public interface IBlockComponent {

    /**
     * {@link Block#use(BlockState, Level, BlockPos, Player, InteractionHand, BlockHitResult)}
     */
    boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws AbstractBlockComponent.NotImplementedException;

    /**
     * {@link Block#createBlockStateDefinition(StateDefinition.Builder)}
     */
    void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

    /**
     * {@link Block#getStateForPlacement(BlockPlaceContext)}
     */
    BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState);

    /**
     * {@link Block#getShape(BlockState, BlockGetter, BlockPos, CollisionContext)}
     */
    VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context);

    /**
     * {@link Block#getDrops(BlockState, LootContext.Builder)}
     */
    List<ItemStack> getDrops(BlockState state, LootContext.Builder builder);

    /**
     * {@link Block#getCloneItemStack(BlockState, HitResult, BlockGetter, BlockPos, Player)}
     */
    ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player);
    
    /**
     * Get the class of the tile entity this component provides. The provided state may be null, therefore
     * this should be taken into consideration. The default tile entity should be returned instead.
     *
     * @param state the state for which to get the tile entity class
     *
     * @return the tile entity class
     */
    // TODO: Is this necessary?
    Class<? extends BlockEntity> getBlockEntityClass(BlockState state);

    /**
     * {@link net.minecraft.world.level.block.EntityBlock#newBlockEntity(BlockPos, BlockState)}
     */
    @Nullable
    BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    /**
     * {@link Block#setPlacedBy(Level, BlockPos, BlockState, LivingEntity, ItemStack)}
     */
    void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack);

    /**
     * {@link Block#updateEntityAfterFallOn(BlockGetter, Entity)}}
     */
    void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) throws AbstractBlockComponent.NotImplementedException;

    /**
     * {@link Block#fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance)}
     */
    void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) throws AbstractBlockComponent.NotImplementedException;

    /**
     * {@link Block#animateTick(BlockState, Level, BlockPos, Random)}
     */
    void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random random);
    
    /**
     * Called when the block is being registered, use for custom Item bindings
     */
    void register(PlaceableItemsBlock block, String name);

    /**
     * @return A localized description of this component to be displayed as an item tooltip
     */
    MutableComponent getDescription(ItemStack itemStack);
}
