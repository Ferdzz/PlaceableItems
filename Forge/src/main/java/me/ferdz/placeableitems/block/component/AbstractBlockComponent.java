package me.ferdz.placeableitems.block.component;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractBlockComponent implements IBlockComponent {

    public class NotImplementedException extends Exception { }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        // Nothing to do in default implementation
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context, BlockState blockState) {
        return blockState;
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return null;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

    }

    @Override
    public void register(PlaceableItemsBlock block, String name) {
        // Nothing to do in default implementation
    }
}
