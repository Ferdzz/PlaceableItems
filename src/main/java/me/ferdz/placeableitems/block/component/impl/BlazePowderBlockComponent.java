package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class BlazePowderBlockComponent extends AbstractBlockComponent {
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) {
        if (random.nextBoolean()) {
            return;
        }
        final double xSpreadRadius = 0.05, ySpreadRadius = 0.025;
        worldIn.addParticle(
                ParticleTypes.FLAME,
                pos.getX() + 0.5 + randomFireSpread(xSpreadRadius, random),
                pos.getY() + 0.3 + randomFireSpread(ySpreadRadius, random),
                pos.getZ() + 0.5 + randomFireSpread(xSpreadRadius, random),
                0,
                0,
                0
        );
    }

    private double randomFireSpread(double spreadRadius, RandomSource random) {
        return (-spreadRadius + (2 * spreadRadius * random.nextGaussian()));
    }

}
