package me.ferdz.placeableitems.blockentities;

import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import me.ferdz.placeableitems.utils.FluidStack;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.util.NbtType;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundTag;

/**
 * Represents a block entity capable of holding a {@link FluidStack}.
 *
 * @author Parker Hawke - Choco
 */
public class FluidHolderBlockEntity extends BlockEntity implements BlockEntityClientSerializable {

    private FluidStack fluid = FluidStack.EMPTY;

    public FluidHolderBlockEntity() {
        super(PlaceableItemBlockEntityRegistry.FLUID_HOLDER_BLOCK_ENTITY);
    }

    /**
     * Set the FluidStack held by this entity.
     *
     * @param fluid the fluid to set. If null, empty will be used.
     */
    public void setFluid(FluidStack fluid) {
        this.fluid = (fluid != null) ? fluid : FluidStack.EMPTY;
        this.markDirty();
    }

    /**
     * Set the Fluid to be held by this entity. Amount will be maintained.
     * Only the fluid's type is changed.
     *
     * @param fluid the fluid to set. If null, empty will be used
     *
     * @return true if successful, false otherwise
     */
    public boolean setFluid(Fluid fluid) {
        if (fluid == null) {
            if (this.fluid.isEmpty()) {
                return false;
            }

            this.setFluid(FluidStack.EMPTY);
            return true;
        }

        if (this.fluid.getFluid() != fluid) {
            this.setFluid(new FluidStack(fluid, getFluidAmount()));
            return true;
        }

        return false;
    }

    /**
     * Get the amount of fluid held by this tile entity. If no fluid, 0 will be returned.
     *
     * @return this tile's fluid amount
     */
    public int getFluidAmount() {
        return fluid.getAmount();
    }

    /**
     * Set the amount of fluid held by this tile entity. If no fluid, this method fails
     * silently.
     *
     * @param amount the amount of fluid to set.
     *
     * @return true if changed, false if no fluid is held by this tile
     */
    public boolean setFluidAmount(int amount) {
        if (fluid.isEmpty()) {
            return false;
        }

        this.fluid.setAmount(amount);
        this.markDirty();
        return true;
    }

    /**
     * Get a copy of the FluidStack held by this tile entity.
     *
     * @return the fluid stack
     */
    public FluidStack getFluid() {
        return fluid.copy();
    }

    @Override
    public CompoundTag toTag(CompoundTag compound) {
        super.toTag(compound);

        if (!fluid.isEmpty()) {
            compound.put("Fluid", fluid.writeToNBT(new CompoundTag()));
        }

        return compound;
    }

    @Override
    public void fromTag(CompoundTag compound) {
        super.fromTag(compound);

        if (compound.containsKey("Fluid", NbtType.COMPOUND)) {
            this.fluid = FluidStack.fromNBT(compound.getCompound("Fluid"));
        }
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        this.fluid.writeToNBT(tag);
        return tag;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        this.fluid = FluidStack.fromNBT(tag);
    }

}
