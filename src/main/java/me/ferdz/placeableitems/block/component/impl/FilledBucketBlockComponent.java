package me.ferdz.placeableitems.block.component.impl;


import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FilledBucketBlockComponent extends AbstractBlockComponent {

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        ItemStack stack = player.getItemInHand(handIn);
        if (stack.getItem() != Items.BUCKET) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }

        if (worldIn.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            player.addItem(blockEntity.getTheItem());

            // Replace the bucket with the filled version
            worldIn.setBlockAndUpdate(pos,
                    PlaceableItemsBlockRegistry.BUCKET.get().defaultBlockState()
                            .setValue(BiPositionBlockComponent.UP, state.getValue(BiPositionBlockComponent.UP))
                            .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION))
            );

            if (worldIn.getBlockEntity(pos) instanceof StackHolderBlockEntity newBlockEntity) {
                newBlockEntity.setTheItem(stack.copyWithCount(1));
            }

            stack.shrink(1);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}
