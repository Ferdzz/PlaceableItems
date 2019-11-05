package me.ferdz.placeableitems.block.component;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;

import java.util.List;



public interface IBlockComponent {
    /**
     * {@link net.minecraft.block.Block#onBlockActivated(BlockState, World, BlockPos, PlayerEntity, Hand, BlockHitResult)}
     */
    boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws AbstractBlockComponent.NotImplementedException;
    
    /**
     * {@link net.minecraft.block.Block#fillStateContainer(StateManager.Builder)}
     */
    void fillStateContainer(StateFactory.Builder<Block, BlockState> builder);

    /**
     * {@link net.minecraft.block.Block#getStateForPlacement(ItemPlacementContext)}
     */
    BlockState getStateForPlacement(ItemPlacementContext context, BlockState blockState);

    /**
     * {@link net.minecraft.block.Block#getShape(BlockState, BlockView, BlockPos, EntityContext)}
     */
    VoxelShape getShape(VoxelShape shape, BlockState state, BlockView worldIn, BlockPos pos, EntityContext context);

    /**
     * {@link net.minecraft.block.Block#getDrops(BlockState, LootContext.Builder)}
     */
    List<ItemStack> getDrops(BlockState state, LootContext.Builder builder);

    /**
     * {@link Block#asItem()}
     */
    Item asItem();

    /**
     * {@link net.minecraftforge.common.extensions.IForgeBlock#hasTileEntity()}
     */
    boolean hasTileEntity();

    /**
     * {@link net.minecraftforge.common.extensions.IForgeBlock#createTileEntity(BlockView)}
     */

    BlockEntity createTileEntity(BlockView world);

    /**
     * {@link Block#onBlockPlacedBy(World, BlockPos, BlockState, LivingEntity, ItemStack)}
     */
    void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack);

    /**
     * Called when the block is being registered, use for custom Item bindings
     */
    void register(PlaceableItemsBlock block, String name);

}
