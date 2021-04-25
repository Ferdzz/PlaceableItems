package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Map;


import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;

@WikiBlockComponentDefinition(description = "Right click with a filled bucket to fill the placed bucket")
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
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        ItemStack itemStack = player.getItemInHand(handIn);
        if (itemStack == ItemStack.EMPTY) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }

        // Get the block associated with the item that was held on right click
        PlaceableItemsBlock replacingBlock = itemBlockDictionary.get(itemStack.getItem());
        if (replacingBlock == null) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }

        // Replace the bucket with the filled version
        worldIn.setBlockAndUpdate(pos,
                replacingBlock.defaultBlockState()
                        .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
        );

        // Play a sound effect appropriate to the fluid
        player.playNotifySound(itemStack.getItem().equals(Items.LAVA_BUCKET) ? SoundEvents.BUCKET_FILL_LAVA : SoundEvents.BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);

        if (!player.abilities.instabuild) {
            itemStack.shrink(1);
            player.setItemInHand(handIn, new ItemStack(Items.BUCKET));
        }

        return true;
    }
}
