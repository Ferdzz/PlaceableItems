package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;

import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ObjectHolder;

/**
 * Represents a tile entity capable of holding a {@link FluidStack}.
 *
 * @author Parker Hawke - Choco
 */
public class FluidHolderTileEntity extends TileEntity {

    @ObjectHolder(PlaceableItems.MODID + ":fluid_holder")
    public static final TileEntityType<FluidHolderTileEntity> TYPE = null;

    private FluidStack fluid = FluidStack.EMPTY;

    public FluidHolderTileEntity() {
        super(TYPE);
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
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        if (fluid != FluidStack.EMPTY) {
            compound.put("Fluid", fluid.writeToNBT(new CompoundNBT()));
        }

        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        if (compound.contains("Fluid", Constants.NBT.TAG_COMPOUND)) {
            this.fluid = FluidStack.loadFluidStackFromNBT(compound.getCompound("Fluid"));
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return fluid.writeToNBT(super.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        super.handleUpdateTag(tag);
        this.fluid = FluidStack.loadFluidStackFromNBT(tag);
    }

}
