package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;
import net.neoforged.neoforge.registries.DeferredBlock;

public class EdibleBlockComponent extends AbstractBlockComponent {
    // TODO: Make some sort of progress when eating, not instantly on right click

    private final  DeferredBlock<PlaceableItemsBlock> replacesWithBlock;

    public EdibleBlockComponent() {
        this(null);
    }

    /**
     * Creates a new EdibleBlockComponent
     *
     * @param replacesWithBlock if the block should be replaced with a block after consumption
     */
    public EdibleBlockComponent(DeferredBlock<PlaceableItemsBlock> replacesWithBlock) {
        this.replacesWithBlock = replacesWithBlock;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws AbstractBlockComponent.NotImplementedException {
        if (!(state.getBlock() instanceof PlaceableItemsBlock)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            Item item = blockEntity.getTheItem().getItem();
            FoodProperties food = item.getFoodProperties(new ItemStack(item), player);
            if (food == null) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            ItemStack itemStack = new ItemStack(item);
            if (player.canEat(food.canAlwaysEat()) || player.isCreative()) {
                itemStack.finishUsingItem(level, player);
                player.eat(level, itemStack);
                state.onDestroyedByPlayer(level, pos, player, false, level.getFluidState(pos));

                // Replace the block with a Bowl if it was requested
                if (this.replacesWithBlock != null) {
                    PlaceableItemsBlock replacingBlock = this.replacesWithBlock.get();
                    BlockState replacementBlockState = replacingBlock.defaultBlockState()
                            .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION));
                    level.setBlockAndUpdate(pos, replacementBlockState);
                    // Ensure placed item is registered in the TE for drops
                    replacingBlock.setPlacedBy(level, pos, state, player, new ItemStack(PlaceableItemsMap.instance().getItemForBlock(replacingBlock)));
                }

                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.edible");
    }
}
