package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.block.BlockState;
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
        this.fluid = (fluid != null) ? fluid.copy() : FluidStack.EMPTY;
        this.setChanged();
    }

    /**
     * Set the Fluid to be held by this entity. Amount will be maintained.
     * Only the fluid's type is changed.
     *
     * @param fluid the fluid to set. If null, empty will be used
     */
    public void setFluid(Fluid fluid) {
        if (fluid == null) {
            if (this.fluid.isEmpty()) {
                return;
            }

            this.setFluid(FluidStack.EMPTY);
        }
        else if (this.fluid.getFluid() != fluid) {
            this.setFluid(new FluidStack(fluid, this.fluid.getAmount()));
        }
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
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);

        if (fluid != FluidStack.EMPTY) {
            compound.put("Fluid", fluid.writeToNBT(new CompoundNBT()));
        }

        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);

        if (compound.contains("Fluid", Constants.NBT.TAG_COMPOUND)) {
            this.fluid = FluidStack.loadFluidStackFromNBT(compound.getCompound("Fluid"));
        }
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return fluid.writeToNBT(super.getUpdateTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        this.fluid = FluidStack.loadFluidStackFromNBT(tag);
    }

}
