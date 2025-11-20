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
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) worldIn.getBlockEntity(pos);
        ItemStack itemStack = blockEntity.getTheItem();
        if (itemStack == null) {
            return InteractionResult.PASS;
        }
        // Code inspired from class FireworkRocketItem
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(worldIn, hit.getLocation().x, hit.getLocation().y, hit.getLocation().z, itemStack);
        worldIn.addFreshEntity(fireworkrocketentity);
        state.onDestroyedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.firework");
    }
}
