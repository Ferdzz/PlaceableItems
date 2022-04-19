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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public abstract class AbstractBlockComponent implements IBlockComponent {

    public class NotImplementedException extends Exception { }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
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
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return null;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.emptyList();
    }

    @Override
    public Item asItem() {
        return null;
    }

    @Override
    public Class<? extends BlockEntity> getBlockEntityClass(BlockState state) {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) throws NotImplementedException {
        throw new NotImplementedException();
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random random) {

    }

    @Override
    public void register(PlaceableItemsBlock block, String name) {
        // Nothing to do in default implementation
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return null;
    }
}
