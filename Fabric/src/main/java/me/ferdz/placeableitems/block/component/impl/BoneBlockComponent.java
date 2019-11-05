package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// TODO: Generalize this to a drop component

public class BoneBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        if (worldIn.isClient())
            return true;

        Block.dropStack(worldIn, pos, new ItemStack(Items.BONE_MEAL, 3));
        worldIn.breakBlock(hit.getBlockPos(), false);
        //state.removedByPlayer(worldIn, pos, player, false, null);
        return true;
    }
}
