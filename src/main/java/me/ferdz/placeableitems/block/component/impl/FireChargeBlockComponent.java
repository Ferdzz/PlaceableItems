package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class FireChargeBlockComponent extends AbstractBlockComponent {

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        // Code inspired by IDispenseItemBehavior
        Direction direction = hitResult.getDirection().getOpposite();
        double d0 = pos.getX() + (0.5F);
        double d1 = pos.getY() + (0.5F);
        double d2 = pos.getZ() + (0.5F);
        double d3 = direction.getStepX();
        double d4 = direction.getStepY();
        double d5 = direction.getStepZ();
        state.onDestroyedByPlayer(level, pos, player, false, level.getFluidState(pos));
        level.addFreshEntity(Util.make(new SmallFireball(level, d0, d1, d2, new Vec3(d3, d4, d5)), (p_218404_1_) -> {
        }));
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.firecharge");
    }
}
