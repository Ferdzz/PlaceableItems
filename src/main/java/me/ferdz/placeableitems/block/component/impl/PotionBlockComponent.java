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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PotionBlockComponent extends AbstractBlockComponent {

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof StackHolderBlockEntity)) {
            return InteractionResult.PASS;
        }

        ItemStack itemStack = ((StackHolderBlockEntity) blockEntity).getTheItem().copy();
        itemStack.finishUsingItem(level, player);
        Block glassBottleBlock = PlaceableItemsBlockRegistry.GLASS_BOTTLE.get();
        level.setBlockAndUpdate(pos, glassBottleBlock
                .defaultBlockState()
                .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION)));
        // Ensure placed item is registered in the TE for drops
        glassBottleBlock.setPlacedBy(level, pos, state, player, new ItemStack(Items.GLASS_BOTTLE));

        return InteractionResult.SUCCESS.withoutItem();
    }
}
