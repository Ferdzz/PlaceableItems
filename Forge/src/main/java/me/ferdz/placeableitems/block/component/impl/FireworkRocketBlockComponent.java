package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right click to launch a firework")
public class FireworkRocketBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemStack = ((StackHolderTileEntity) worldIn.getBlockEntity(pos)).getItemStack();
        // Code inspired from class FireworkRocketItem
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(worldIn, hit.getLocation().x, hit.getLocation().y, hit.getLocation().z, itemStack);
        worldIn.addFreshEntity(fireworkrocketentity);
        state.removedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

        return true;
    }
}
