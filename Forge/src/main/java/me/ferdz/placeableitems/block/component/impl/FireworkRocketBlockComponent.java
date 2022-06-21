package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

@WikiBlockComponentDefinition(description = "Right click to launch a firework")
public class FireworkRocketBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemStack = ((StackHolderBlockEntity) worldIn.getBlockEntity(pos)).getItemStack();
        // Code inspired from class FireworkRocketItem
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(worldIn, hit.getLocation().x, hit.getLocation().y, hit.getLocation().z, itemStack);
        worldIn.addFreshEntity(fireworkrocketentity);
        state.onDestroyedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

        return true;
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.firework");
    }
}
