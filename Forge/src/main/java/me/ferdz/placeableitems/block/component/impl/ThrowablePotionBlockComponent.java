package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import me.ferdz.placeableitems.blockentity.StackHolderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ThrowablePotionBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        ThrownPotion thrownPotion = new ThrownPotion(worldIn, player);

        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (!(blockEntity instanceof StackHolderBlockEntity)) {
            return false;
        }
        worldIn.removeBlock(pos, true);

        thrownPotion.setItem(((StackHolderBlockEntity) blockEntity).getItemStack().copy());
        thrownPotion.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        worldIn.addFreshEntity(thrownPotion);

        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return PlaceableItemsBlockEntityTypeRegistry.SYNCED_STACK_HOLDER.create(pos, state);
    }
}
