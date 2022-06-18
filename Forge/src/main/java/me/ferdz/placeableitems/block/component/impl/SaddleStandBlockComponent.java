package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsItemRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;

public class SaddleStandBlockComponent extends AbstractBlockComponent {

    private static final BooleanProperty FILLED = BooleanProperty.create("filled");;

    @Override
    public Item asItem() {
        return PlaceableItemsItemRegistry.SADDLE_STAND;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        if (state.getValue(FILLED)) {
            if (!worldIn.isClientSide()) {
                Block.popResource(worldIn, pos, new ItemStack(Items.SADDLE, 1));
                worldIn.setBlockAndUpdate(pos, state.setValue(FILLED, false));
            }
            return true;
        } else if (player.getItemInHand(handIn).getItem().equals(Items.SADDLE)) {
            if (!worldIn.isClientSide()) {
                if (!player.isCreative()) {
                    player.getItemInHand(handIn).shrink(1);
                }
                worldIn.setBlockAndUpdate(pos, state.setValue(FILLED, true));
            }
            return true;
        }

        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        if (state.getValue(FILLED)) {
            itemStacks.add(new ItemStack(Items.SADDLE));
        }
        return itemStacks;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        return blockState.setValue(FILLED, false);
    }
}
