package me.ferdz.placeableitems.block.component.impl;

import java.util.function.Supplier;

import com.google.common.base.Preconditions;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        if (worldIn.isClient) {
            return true;
        }

        if (worldIn.random.nextFloat() < chance) {
            ItemStack stack = itemSupplier.get();
            if (stack != null && !stack.isEmpty()) {
                Block.dropStack(worldIn, pos, stack);
            }
        }

        return true;
    }

}
