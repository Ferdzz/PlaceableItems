package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right launch a fireball in the direction you're facing")
public class FireChargeBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        // Code inspired by IDispenseItemBehavior
        Direction direction = hit.getFace().getOpposite();
        double d0 = pos.getX() + (0.5F);
        double d1 = pos.getY() + (0.5F);
        double d2 = pos.getZ() + (0.5F);
        double d3 = (double)direction.getXOffset();
        double d4 = (double)direction.getYOffset();
        double d5 = (double)direction.getZOffset();
        state.removedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));
        worldIn.addEntity(Util.make(new SmallFireballEntity(worldIn, d0, d1, d2, d3, d4, d5), (p_218404_1_) -> {
        }));
        return true;
    }
}
