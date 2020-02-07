package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaddleStandBlockComponent extends AbstractBlockComponent {

    private static final BooleanProperty FILLED = BooleanProperty.create("filled");;

    @Override
    public Item asItem() {
        return PlaceableItemsItemRegistry.SADDLE_STAND;
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        if (state.get(FILLED)) {
            if (!worldIn.isRemote()) {
                Block.spawnAsEntity(worldIn, pos, new ItemStack(Items.SADDLE, 1));
                worldIn.setBlockState(pos, state.with(FILLED, false));
            }
            return true;
        } else if (player.getHeldItem(handIn).getItem().equals(Items.SADDLE)) {
            if (!worldIn.isRemote()) {
                if (!player.isCreative()) {
                    player.getHeldItem(handIn).shrink(1);
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
    public BlockState getStateForPlacement(BlockItemUseContext context, BlockState blockState) {
        return blockState.with(FILLED, false);
    }
}
