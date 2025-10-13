package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class FireworkRocketBlockComponent extends AbstractBlockComponent {

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) level.getBlockEntity(pos);
        ItemStack itemStack = blockEntity.getTheItem();
        if (itemStack == null || itemStack.isEmpty()) {
            return InteractionResult.PASS;
        }
        // Code inspired from class FireworkRocketItem
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, itemStack);
        level.addFreshEntity(fireworkrocketentity);
        state.onDestroyedByPlayer(level, pos, player, false, level.getFluidState(pos));

        return InteractionResult.SUCCESS;
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.firework");
    }
}
