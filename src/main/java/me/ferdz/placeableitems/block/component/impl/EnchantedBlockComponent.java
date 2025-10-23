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

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);

        if (blockEntity instanceof StackHolderBlockEntity stackHolder) {
            ItemStack itemStack = stackHolder.getTheItem();

            if (itemStack != null && !itemStack.isEmpty() && itemStack.isEnchanted()) {

                if (random.nextInt(3) == 0) {

                    double d0 = pos.getX() + 0.2D + (random.nextDouble() * 0.6D);
                    double d1 = pos.getY() + 0.2D + (random.nextDouble() * 0.8D);
                    double d2 = pos.getZ() + 0.2D + (random.nextDouble() * 0.6D);

                    worldIn.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }

                if (random.nextInt(3) == 0) {

                    double d0 = pos.getX() + 0.2D + (random.nextDouble() * 0.6D);
                    double d1 = pos.getY() + 0.2D + (random.nextDouble() * 0.5D);
                    double d2 = pos.getZ() + 0.2D + (random.nextDouble() * 0.6D);

                    worldIn.addParticle(ParticleTypes.ENCHANTED_HIT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}
