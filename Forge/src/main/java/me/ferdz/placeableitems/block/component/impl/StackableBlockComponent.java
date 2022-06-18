package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.List;

@WikiBlockComponentDefinition(description = "Right click this block with more of the same item to add to the stack")
public class StackableBlockComponent extends AbstractBlockComponent {

    private final int maxCount;
    private final IntegerProperty filled;

    public StackableBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.filled = IntegerProperty.create("count", 1, maxCount);
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int count = state.getValue(filled);
        ItemStack heldItemStack = player.getItemInHand(handIn);
        Item heldItem = heldItemStack.getItem();

        if (heldItem == Items.AIR) {
            Block.popResource(worldIn, pos, new ItemStack(state.getBlock().asItem(), 1));
            // If block only has 1 stack left, pop the last resource and destroy the block
            if (count == 1) {
                worldIn.destroyBlock(pos, false, player);
            } else {
                worldIn.setBlockAndUpdate(pos, state.setValue(filled, count - 1));
            }
            return true;
        }

        if (heldItem == state.getBlock().asItem()) {
            if (count == maxCount) {
                return false;
            }

            if (!player.isCreative()) {
                heldItemStack.shrink(1);
            }
            worldIn.setBlockAndUpdate(pos, state.setValue(filled, count + 1));
            return true;
        }

        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        int count = state.getValue(filled);
        return Collections.singletonList(new ItemStack(state.getBlock().asItem(), count));
    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(filled);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.stackable", itemStack.getItem().getDescription());
    }
}
