package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

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
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide) {
            return true;
        }

        if (worldIn.random.nextFloat() < chance) {
            ItemStack stack = itemSupplier.get();
            if (stack != null && !stack.isEmpty()) {
                Block.popResource(worldIn, pos, stack);
            }
        }

        return true;
    }
}
