package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;

public class EmptyBucketBlockComponent extends AbstractBlockComponent {

    private final Map<Item, DeferredBlock<PlaceableItemsBlock>> itemBlockDictionary;


    /**
     * Creates a new {@link EmptyBucketBlockComponent}
     * @param itemBlockDictionary A dictionary representing the links between the items used, and the block to replace.
     *                            For example, <LavaBucket, LavaBucketBlock> would replace the EmptyBucketBlockComponent
     *                            with a LavaBucketBlock when right clicked with a LavaBucket
     */
    public EmptyBucketBlockComponent(Map<Item, DeferredBlock<PlaceableItemsBlock>> itemBlockDictionary) {
        this.itemBlockDictionary = itemBlockDictionary;
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        if (stack.isEmpty()) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        // Get the block associated with the item that was held on right click
        DeferredBlock<PlaceableItemsBlock> replacingBlock = itemBlockDictionary.get(stack.getItem());
        if (replacingBlock == null) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        // Replace the bucket with the filled version
        level.setBlockAndUpdate(pos,
                replacingBlock.get().defaultBlockState()
                        .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
        );
        if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            blockEntity.setTheItem(stack.copyWithCount(1));
        }

        // Play a sound effect appropriate to the fluid
        player.playNotifySound(stack.getItem().equals(Items.LAVA_BUCKET) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);

        // TODO: Check behavior
        return InteractionResult.SUCCESS.heldItemTransformedTo(new ItemStack(Items.BUCKET));
    }
}
