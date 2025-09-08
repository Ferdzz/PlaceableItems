package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Random;

public class EnderEyeBlockComponent extends AbstractBlockComponent {

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            worldIn.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, random.nextGaussian(), 0.0D, random.nextGaussian());
        }
    }
}
