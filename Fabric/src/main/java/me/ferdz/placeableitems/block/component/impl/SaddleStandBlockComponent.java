package me.ferdz.placeableitems.block.component.impl;

import java.util.ArrayList;
import java.util.List;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;

public class SaddleStandBlockComponent extends AbstractBlockComponent {

    private static final BooleanProperty FILLED = BooleanProperty.of("filled");

    @Override
    public Item asItem() {
        return PlaceableItemsItemsRegistry.SADDLE_STAND;
    }

    @Override
    public void fillStateContainer(StateFactory.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        if (state.get(FILLED)) {
            if (!worldIn.isClient()) {
                Block.dropStack(worldIn, pos, new ItemStack(Items.SADDLE, 1));
                worldIn.setBlockState(pos, state.with(FILLED, false));
            }
            return true;
        } else if (player.getStackInHand(handIn).getItem().equals(Items.SADDLE)) {
            if (!worldIn.isClient()) {
                if (!player.isCreative()) {
                    player.getStackInHand(handIn).decrement(1);
                }
                worldIn.setBlockState(pos, state.with(FILLED, true));
            }
            return true;
        }

        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        if (state.get(FILLED)) {
            itemStacks.add(new ItemStack(Items.SADDLE));
        }
        return itemStacks;
    }

    @Override
    public BlockState getStateForPlacement(ItemPlacementContext context, BlockState blockState) {
        return blockState.with(FILLED, false);
    }
}
