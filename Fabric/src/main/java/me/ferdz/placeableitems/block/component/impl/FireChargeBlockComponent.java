package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FireChargeBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        // Code inspired by IDispenseItemBehavior
        Direction direction = hit.getSide().getOpposite();
        double d0 = pos.getX() + (0.5F);
        double d1 = pos.getY() + (0.5F);
        double d2 = pos.getZ() + (0.5F);
        double d3 = (double)direction.getOffsetX();
        double d4 = (double)direction.getOffsetY();
        double d5 = (double)direction.getOffsetZ();
        worldIn.breakBlock(hit.getBlockPos(), false);
        //state.removedByPlayer(worldIn, pos, player, false, null);
        worldIn.spawnEntity(new SmallFireballEntity(worldIn, d0, d1, d2, d3,d4,d5));
        //worldIn.addEntity(Util.make(new SmallFireballEntity(worldIn, d0, d1, d2, d3, d4, d5), (p_218404_1_) -> {
        //}));
        return true;
    }
}
