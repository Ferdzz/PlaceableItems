package me.ferdz.placeableitems.block.component.impl;

import java.util.function.Supplier;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FragileBlockComponent extends AbstractBlockComponent {

    private final ContextualItemStackCreator itemStackCreator;

    public FragileBlockComponent(ContextualItemStackCreator itemStackCreator) {
        this.itemStackCreator = itemStackCreator;
    }

    public FragileBlockComponent(Supplier<ItemStack> itemCreator) {
        this((state, world, pos, player, hand, hit) -> itemCreator.get());
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        if (worldIn.isClient()) {
            return true;
        }

        if (itemStackCreator != null) {
            ItemStack stack = itemStackCreator.createItem(state, worldIn, pos, player, handIn, hit);
            if (stack != null && !stack.isEmpty()) {
                Block.dropStack(worldIn, pos, stack);
            }
        }

        worldIn.breakBlock(hit.getBlockPos(), false);
        return true;
    }

    @FunctionalInterface
    public static interface ContextualItemStackCreator {

        public ItemStack createItem(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit);

    }

}
