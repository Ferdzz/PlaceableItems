package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlazePowderBlockComponent extends AbstractBlockComponent {
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random random) {
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

    private double randomFireSpread(double spreadRadius, Random random) {
        return (-spreadRadius + (2 * spreadRadius * random.nextGaussian()));
    }

}
