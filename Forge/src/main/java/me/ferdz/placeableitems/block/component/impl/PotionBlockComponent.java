package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import me.ferdz.placeableitems.blockentity.StackHolderBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PotionBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (!(blockEntity instanceof StackHolderBlockEntity)) {
            return false;
        }

        ItemStack itemStack = ((StackHolderBlockEntity) blockEntity).getItemStack().copy();
        itemStack.finishUsingItem(worldIn, player);
        worldIn.setBlockAndUpdate(pos, PlaceableItemsBlockRegistry.GLASS_BOTTLE
                .defaultBlockState()
                .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION)));

        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return PlaceableItemsBlockEntityTypeRegistry.SYNCED_STACK_HOLDER.create(pos, state);
    }
}
