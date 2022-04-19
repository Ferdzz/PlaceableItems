package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import me.ferdz.placeableitems.tileentity.SyncedStackHolderTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class ThrowablePotionBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        ThrownPotion potionentity = new ThrownPotion(worldIn, player);

        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof StackHolderTileEntity)) {
            return false;
        }
        worldIn.removeBlock(pos, true);

        potionentity.setItem(((StackHolderTileEntity) tileEntity).getItemStack().copy());
        potionentity.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        worldIn.addFreshEntity(potionentity);

        return true;
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
        return new SyncedStackHolderTileEntity();
    }
}