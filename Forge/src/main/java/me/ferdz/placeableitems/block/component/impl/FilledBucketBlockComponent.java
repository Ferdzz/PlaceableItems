package me.ferdz.placeableitems.block.component.impl;


import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;

@WikiBlockComponentDefinition(description = "Right click with an empty bucket to empty the placed bucket")
public class FilledBucketBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        ItemStack itemStack = player.getItemInHand(handIn);
        if (itemStack.getItem() != Items.BUCKET) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }

        // Replace the bucket with the filled version
        worldIn.setBlockAndUpdate(pos,
                PlaceableItemsBlockRegistry.BUCKET.defaultBlockState()
                        .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
        );

        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
            player.setItemInHand(handIn, new ItemStack(((PlaceableItemsBlock) state.getBlock()).getPlacedItem()));
        }
        return true;
    }
}
