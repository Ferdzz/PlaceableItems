package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class StackableBlockComponent extends AbstractBlockComponent {

    public final int maxCount;
    public final IntegerProperty filled;

    public StackableBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.filled = IntegerProperty.create("count", 1, maxCount);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        if (!(state.getBlock() instanceof PlaceableItemsBlock block)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        int count = state.getValue(filled);

        // TODO: Store in individual ItemStacks to not lose NBT for each item
        Item placedItem = PlaceableItemsMap.instance().getItemForBlock((PlaceableItemsBlock) state.getBlock());

        if (stack.getItem() == Items.AIR) {
            Block.popResource(level, pos, new ItemStack(placedItem, 1));
            // If block only has 1 stack left, pop the last resource and destroy the block
            if (count == 1) {
                level.destroyBlock(pos, false, player);
            } else {
                level.setBlockAndUpdate(pos, state.setValue(filled, count - 1));
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (stack.getItem() == placedItem) {
            if (count == maxCount) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            if (!player.isCreative()) {
                stack.shrink(1);
            }
            level.setBlockAndUpdate(pos, state.setValue(filled, count + 1));
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

//    @Override
//    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
//        if (!(state.getBlock() instanceof PlaceableItemsBlock block)) {
//            return super.getDrops(state, builder);
//        }
//
//        int count = state.getValue(filled);
//        return Collections.singletonList(new ItemStack(block.getPlacedItem(), count));
//    }

    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(filled);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.stackable", itemStack.getItem().getDescription());
    }
}
