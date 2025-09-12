package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class MultiModelBlockComponent extends AbstractBlockComponent {

    private final int maxCount;
    public final IntegerProperty model;

    public MultiModelBlockComponent(int maxCount) {
        this.maxCount = maxCount;
        this.model = IntegerProperty.create("model", 0, maxCount);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        int nextModel = state.getValue(model) + 1;
        if (nextModel > maxCount) {
            nextModel = 0;
        }
        level.setBlockAndUpdate(pos, state.setValue(model, nextModel));
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(model);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable(
                "key.placeableitems.component.multimodel",
                Component.literal(String.valueOf(maxCount + 1))
        );
    }
}
