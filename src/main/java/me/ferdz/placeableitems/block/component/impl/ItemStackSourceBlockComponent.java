package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class ItemStackSourceBlockComponent extends AbstractBlockComponent {

    private final float chance;
    private final Supplier<ItemStack> itemSupplier;

    public ItemStackSourceBlockComponent(float chance, Supplier<ItemStack> itemSupplier) {
        Preconditions.checkArgument(itemSupplier != null, "Item supplier must not be null");

        this.chance = chance;
        this.itemSupplier = itemSupplier;
    }

    public ItemStackSourceBlockComponent(Supplier<ItemStack> itemSupplier) {
        this(1.0F, itemSupplier);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        if (worldIn.isClientSide) {
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }

        if (worldIn.random.nextFloat() < chance) {
            ItemStack droppedStack = itemSupplier.get();
            if (droppedStack != null && !droppedStack.isEmpty()) {
                Block.popResource(worldIn, pos, droppedStack);
            }
        }

        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}
