package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.Random;

public class LavaBucketBlockComponent extends AbstractBlockComponent {
    // Taken from LavaFluid class
    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.above();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }
}
