package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SlimeBlockComponent extends AbstractBlockComponent {

    @Override
    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        Blocks.SLIME_BLOCK.updateEntityAfterFallOn(worldIn, entityIn);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        Blocks.SLIME_BLOCK.fallOn(worldIn, pos, entityIn, fallDistance);
    }
}
