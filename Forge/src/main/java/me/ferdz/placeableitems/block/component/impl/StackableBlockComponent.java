package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
    public boolean use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
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

    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(filled);
    }

    @Override
    public IFormattableTextComponent getDescription(ItemStack itemStack) {
        return new TranslationTextComponent("key.placeableitems.component.stackable", itemStack.getItem().getDescription());
    }
}
