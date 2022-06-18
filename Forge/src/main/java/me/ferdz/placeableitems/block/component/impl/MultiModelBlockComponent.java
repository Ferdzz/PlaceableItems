package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

@WikiBlockComponentDefinition(description = "This item has many models. Right click to cycle through them")
public class MultiModelBlockComponent extends AbstractBlockComponent {

    private final int maxCount;
    private final IntegerProperty model;

    public MultiModelBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.model = IntegerProperty.create("model", 0, maxCount);
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        int nextModel = state.getValue(model) + 1;
        if (nextModel > maxCount) {
            nextModel = 0;
        }
        worldIn.setBlockAndUpdate(pos, state.setValue(model, nextModel));
        return true;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(model);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.multimodel", new TextComponent(String.valueOf(maxCount + 1)));
    }
}
