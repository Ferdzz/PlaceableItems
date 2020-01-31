package me.ferdz.placeableitems.block.component.impl;

import java.util.function.Supplier;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right clicking this item will break it and drop an item or entity")
public class FragileBlockComponent extends AbstractBlockComponent {

    private ContextualObjectCreator<ItemStack> itemStackCreator;
    private ContextualObjectCreator<? extends Entity> entityCreator;

    public FragileBlockComponent(ContextualObjectCreator<ItemStack> itemStackCreator, ContextualObjectCreator<? extends Entity> entityCreator) {
        this.itemStackCreator = itemStackCreator;
        this.entityCreator = entityCreator;
    }

    public FragileBlockComponent(Supplier<ItemStack> itemStackCreator, Supplier<? extends Entity> entityCreator) {
        this(ContextualObjectCreator.fromSupplier(itemStackCreator), ContextualObjectCreator.fromSupplier(entityCreator));
    }

    public FragileBlockComponent(ContextualObjectCreator<ItemStack> itemStackCreator, Supplier<? extends Entity> entityCreator) {
        this(itemStackCreator, ContextualObjectCreator.fromSupplier(entityCreator));
    }

    public FragileBlockComponent(Supplier<ItemStack> itemStackCreator, ContextualObjectCreator<? extends Entity> entityCreator) {
        this(ContextualObjectCreator.fromSupplier(itemStackCreator), entityCreator);
    }

    public FragileBlockComponent() {
        this((ContextualObjectCreator<ItemStack>) null, (ContextualObjectCreator<? extends Entity>) null);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        if (worldIn.isRemote) {
            return true;
        }

        if (itemStackCreator != null) {
            ItemStack stack = itemStackCreator.create(state, worldIn, pos, player, handIn, hit);
            if (stack != null && !stack.isEmpty()) {
                Block.spawnAsEntity(worldIn, pos, stack);
            }
        }

        if (entityCreator != null) {
            Entity entity = entityCreator.create(state, worldIn, pos, player, handIn, hit);
            if (entity != null) {
                entity.setPositionAndRotation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0F, 0F);
                worldIn.addEntity(entity);
            }
        }

        state.removedByPlayer(worldIn, pos, player, false, null);
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

        public T create(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit);

        public static <T> ContextualObjectCreator<T> fromSupplier(Supplier<T> supplier) {
            return supplier != null ? ((state, world, pos, player, hand, hit) -> supplier.get()) : null;
        }

    }

}
