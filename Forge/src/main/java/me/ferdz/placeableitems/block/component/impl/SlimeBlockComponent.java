package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Bouncy!")
public class SlimeBlockComponent extends AbstractBlockComponent {

    @Override
    public void updateEntityAfterFallOn(IBlockReader worldIn, Entity entityIn) {
        Blocks.SLIME_BLOCK.updateEntityAfterFallOn(worldIn, entityIn);
    }

    @Override
    public void fallOn(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        Blocks.SLIME_BLOCK.fallOn(worldIn, pos, entityIn, fallDistance);
    }

    @Override
    public IFormattableTextComponent getDescription(ItemStack itemStack) {
        return new TranslationTextComponent("key.placeableitems.component.slime");
    }
}
