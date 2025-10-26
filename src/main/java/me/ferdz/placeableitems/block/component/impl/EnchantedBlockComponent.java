package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedBlockComponent extends AbstractBlockComponent {

    private boolean alwaysAnimated;

    public EnchantedBlockComponent() {
        this.alwaysAnimated = false;
    }

    public EnchantedBlockComponent(boolean alwaysAnimated) {
        this.alwaysAnimated = alwaysAnimated;
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);

        if (blockEntity instanceof StackHolderBlockEntity stackHolder) {
            ItemStack itemStack = stackHolder.getTheItem();

            if (itemStack != null && !itemStack.isEmpty() && (itemStack.isEnchanted() || this.alwaysAnimated)) {

                if (random.nextInt(3) == 0) {

                    double d0 = pos.getX() + 0.2D + (random.nextDouble() * 0.5D);
                    double d1 = pos.getY() + 0.2D + (random.nextDouble() * 0.1D);
                    double d2 = pos.getZ() + 0.2D + (random.nextDouble() * 0.5D);

                    worldIn.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, random.nextFloat() - 0.5, random.nextFloat() * 0.5, random.nextFloat() - 0.5);
                }

                if (random.nextInt(12) == 0) {

                    double d0 = pos.getX() + 0.2D + (random.nextDouble() * 0.5D);
                    double d1 = pos.getY() + 0.2D + (random.nextDouble() * 0.1D);
                    double d2 = pos.getZ() + 0.2D + (random.nextDouble() * 0.5D);

                    worldIn.addParticle(ParticleTypes.GLOW, d0, d1, d2, 0.3D, 0.3D, 0.3D);
                }
            }
        }
    }

}
