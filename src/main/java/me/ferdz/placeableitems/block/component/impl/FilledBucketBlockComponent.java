package me.ferdz.placeableitems.block.component.impl;


import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FilledBucketBlockComponent extends AbstractBlockComponent {

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        if (stack.getItem() != Items.BUCKET) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }

        if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            player.addItem(blockEntity.getTheItem());

            // Replace the bucket with the filled version
            level.setBlockAndUpdate(pos,
                    PlaceableItemsBlockRegistry.BUCKET.get().defaultBlockState()
                            .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                            .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
            );

            if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity newBlockEntity) {
                newBlockEntity.setTheItem(stack.copyWithCount(1));
            }

            stack.shrink(1);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
