package me.ferdz.placeableitems.block.component.impl;

import java.util.function.Supplier;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FragileBlockComponent extends AbstractBlockComponent {

    private ContextualObjectCreator<ItemStack> itemStackCreator;
    private ContextualObjectCreator<? extends Entity> entityCreator;

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        if (worldIn.isClient()) {
            return true;
        }

        if (itemStackCreator != null) {
            ItemStack stack = itemStackCreator.create(state, worldIn, pos, player, handIn, hit);
            if (stack != null && !stack.isEmpty()) {
                Block.dropStack(worldIn, pos, stack);
            }
        }

        if (entityCreator != null) {
            Entity entity = entityCreator.create(state, worldIn, pos, player, handIn, hit);
            if (entity != null) {
                entity.setPositionAndAngles(pos, 0F, 0F);
                worldIn.spawnEntity(entity);
            }
        }

        worldIn.breakBlock(hit.getBlockPos(), false);
        return true;
    }

    public FragileBlockComponent withItemStack(ContextualObjectCreator<ItemStack> itemStackCreator) {
        this.itemStackCreator = itemStackCreator;
        return this;
    }

    public FragileBlockComponent withItemStack(Supplier<ItemStack> itemStackCreator) {
        return withItemStack(ContextualObjectCreator.fromSupplier(itemStackCreator));
    }

    public FragileBlockComponent withEntity(ContextualObjectCreator<? extends Entity> entityCreator) {
        this.entityCreator = entityCreator;
        return this;
    }

    public FragileBlockComponent withEntity(Supplier<? extends Entity> itemStackCreator) {
        return withEntity(ContextualObjectCreator.fromSupplier(itemStackCreator));
    }

    @FunctionalInterface
    public static interface ContextualObjectCreator<T> {

        public T create(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit);

        public static <T> ContextualObjectCreator<T> fromSupplier(Supplier<T> supplier) {
            return supplier != null ? ((state, world, pos, player, hand, hit) -> supplier.get()) : null;
        }

    }

}
