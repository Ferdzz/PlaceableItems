package me.ferdz.placeableitems.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.BucketPickupHandlerWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class PlaceableItemsFluidUtil {
    /**
     * Same as {{@link FluidUtil#tryPickUpFluid(ItemStack, PlayerEntity, World, BlockPos, Direction)}} but doesn't actually fill the bucket
     */
    public static FluidActionResult tryVirtualPickUpFluid(@Nonnull ItemStack emptyContainer, @Nullable Player playerIn, Level worldIn, BlockPos pos, Direction side)
    {
        if (emptyContainer.isEmpty() || worldIn == null || pos == null)
        {
            return FluidActionResult.FAILURE;
        }

        BlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        IFluidHandler targetFluidHandler;
        if (block instanceof IFluidBlock)
        {
            targetFluidHandler = new FluidBlockWrapper((IFluidBlock) block, worldIn, pos);
        }
        else if (block instanceof BucketPickup)
        {
            targetFluidHandler = new BucketPickupHandlerWrapper((BucketPickup) block, worldIn, pos);
        }
        else
        {
            Optional<IFluidHandler> fluidHandler = FluidUtil.getFluidHandler(worldIn, pos, side).resolve();
            if (!fluidHandler.isPresent())
            {
                return FluidActionResult.FAILURE;
            }
            targetFluidHandler = fluidHandler.get();
        }
        return FluidUtil.tryFillContainer(emptyContainer, targetFluidHandler, Integer.MAX_VALUE, playerIn, false);
    }
}
