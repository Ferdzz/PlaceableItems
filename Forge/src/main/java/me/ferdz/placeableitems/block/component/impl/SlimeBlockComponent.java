package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@WikiBlockComponentDefinition(description = "Bouncy!")
public class SlimeBlockComponent extends AbstractBlockComponent {

    @Override
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
        Blocks.SLIME_BLOCK.updateEntityAfterFallOn(worldIn, entityIn);
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        Blocks.SLIME_BLOCK.fallOn(worldIn, state, pos, entityIn, fallDistance);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.slime");
    }
}
