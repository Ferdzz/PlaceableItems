package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class EnderEyeBlockComponent extends AbstractBlockComponent {

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            worldIn.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, random.nextGaussian(), 0.0D, random.nextGaussian());
        }
    }
}
