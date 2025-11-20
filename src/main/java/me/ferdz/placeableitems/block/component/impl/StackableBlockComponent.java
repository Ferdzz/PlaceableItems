package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        if (!(state.getBlock() instanceof PlaceableItemsBlock block)) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }
        ItemStack stack = player.getItemInHand(handIn);

        int count = state.getValue(filled);

        // TODO: Store in individual ItemStacks to not lose NBT for each item
        Item placedItem = PlaceableItemsMap.instance().getItemForBlock((PlaceableItemsBlock) state.getBlock());

        if (stack.getItem() == Items.AIR || stack.isEmpty()) {
            Block.popResource(worldIn, pos, new ItemStack(placedItem, 1));
            // If block only has 1 stack left, pop the last resource and destroy the block
            if (count == 1) {
                worldIn.destroyBlock(pos, false, player);
            } else {
                worldIn.setBlockAndUpdate(pos, state.setValue(filled, count - 1));
            }
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }

        if (stack.getItem() == placedItem) {
            if (count == maxCount) {
                return InteractionResult.PASS;
            }

            if (!player.isCreative()) {
                stack.shrink(1);
            }
            worldIn.setBlockAndUpdate(pos, state.setValue(filled, count + 1));
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }

        return InteractionResult.PASS;
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
