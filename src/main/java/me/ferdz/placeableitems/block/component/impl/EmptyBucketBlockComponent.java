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

import java.util.Map;

public class EmptyBucketBlockComponent extends AbstractBlockComponent {

    private final Map<Item, PlaceableItemsBlock> itemBlockDictionary;


    /**
     * Creates a new {@link EmptyBucketBlockComponent}
     * @param itemBlockDictionary A dictionary representing the links between the items used, and the block to replace.
     *                            For example, <LavaBucket, LavaBucketBlock> would replace the EmptyBucketBlockComponent
     *                            with a LavaBucketBlock when right clicked with a LavaBucket
     */
    public EmptyBucketBlockComponent(Map<Item, PlaceableItemsBlock> itemBlockDictionary) {
        this.itemBlockDictionary = itemBlockDictionary;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        ItemStack stack = player.getItemInHand(handIn);
        if (stack.isEmpty()) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }

        // Get the block associated with the item that was held on right click
        PlaceableItemsBlock replacingBlock = itemBlockDictionary.get(stack.getItem());
        if (replacingBlock == null) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }

        // Replace the bucket with the filled version
        worldIn.setBlockAndUpdate(pos,
                replacingBlock.defaultBlockState()
                        .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
        );
        if (worldIn.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            blockEntity.setTheItem(stack.copyWithCount(1));
        }

        // Play a sound effect appropriate to the fluid
        player.playNotifySound(stack.getItem().equals(Items.LAVA_BUCKET) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);

        stack.shrink(1);
        player.setItemInHand(handIn, new ItemStack(Items.BUCKET));

        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}
