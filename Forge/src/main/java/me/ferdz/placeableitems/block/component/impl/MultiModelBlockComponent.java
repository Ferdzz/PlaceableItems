package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "This item has many models. Right click to cycle through them")
public class MultiModelBlockComponent extends AbstractBlockComponent {

    private final int maxCount;
    private final IntegerProperty model;

    public MultiModelBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.model = IntegerProperty.create("model", 0, maxCount);
    }

    @Override
    public boolean use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int nextModel = state.getValue(model) + 1;
        if (nextModel > maxCount) {
            nextModel = 0;
        }
        worldIn.setBlockAndUpdate(pos, state.setValue(model, nextModel));
        return true;
    }

    @Override
    public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(model);
    }

    @Override
    public IFormattableTextComponent getDescription(ItemStack itemStack) {
        return new TranslationTextComponent("key.placeableitems.component.multimodel", new StringTextComponent(String.valueOf(maxCount + 1)));
    }
}
