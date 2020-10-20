package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class LavaBucketBlockComponent extends AbstractBlockComponent {
    // Taken from LavaFluid class
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isOpaqueCube(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }
}
