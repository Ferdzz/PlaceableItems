package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;

@WikiBlockComponentDefinition(description = "Right launch a fireball in the direction you're facing")
public class FireChargeBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        // Code inspired by IDispenseItemBehavior
        Direction direction = hit.getDirection().getOpposite();
        double d0 = pos.getX() + (0.5F);
        double d1 = pos.getY() + (0.5F);
        double d2 = pos.getZ() + (0.5F);
        double d3 = direction.getStepX();
        double d4 = direction.getStepY();
        double d5 = direction.getStepZ();
        state.onDestroyedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));
        worldIn.addFreshEntity(Util.make(new SmallFireball(worldIn, d0, d1, d2, d3, d4, d5), (p_218404_1_) -> {
        }));
        return true;
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.firecharge");
    }
}
