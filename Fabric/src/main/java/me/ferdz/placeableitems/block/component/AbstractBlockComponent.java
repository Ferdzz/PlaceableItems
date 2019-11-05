package me.ferdz.placeableitems.block.component;

import java.util.ArrayList;
import java.util.List;

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

public abstract class AbstractBlockComponent implements IBlockComponent {
    
    public class NotImplementedException extends Exception { }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void fillStateContainer(StateFactory.Builder<Block, BlockState> builder) {
        // Nothing to do in default implementation
    }

    @Override
    public BlockState getStateForPlacement(ItemPlacementContext context, BlockState blockState) {
        return blockState;
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockView worldIn, BlockPos pos, EntityContext context) {
        return null;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return new ArrayList<>();
    }

    @Override
    public Item asItem() {
        return null;
    }

    @Override
    public boolean hasTileEntity() {
        return false;
    }

    @Override
    public BlockEntity createTileEntity(BlockView world) {
        return null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

    }

    @Override
    public void register(PlaceableItemsBlock block, String name) {
        // Nothing to do in default implementation
    }
}
