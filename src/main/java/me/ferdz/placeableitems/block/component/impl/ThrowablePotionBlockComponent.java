package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ThrowablePotionBlockComponent extends AbstractBlockComponent {

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        ThrownPotion thrownPotion = new ThrownPotion(level, player);

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof StackHolderBlockEntity)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        level.removeBlock(pos, true);

        thrownPotion.setItem(((StackHolderBlockEntity) blockEntity).getTheItem().copy());
        thrownPotion.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        level.addFreshEntity(thrownPotion);

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
