package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.function.Supplier;

@WikiBlockComponentDefinition(description = "Right clicking this block will have a chance to drop an item")
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
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return true;
        }

        if (worldIn.rand.nextFloat() < chance) {
            ItemStack stack = itemSupplier.get();
            if (stack != null && !stack.isEmpty()) {
                Block.spawnAsEntity(worldIn, pos, stack);
            }
        }

        return true;
    }
}
