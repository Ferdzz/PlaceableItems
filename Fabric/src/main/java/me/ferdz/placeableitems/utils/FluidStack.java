package me.ferdz.placeableitems.utils;

import com.google.common.base.Preconditions;

import net.fabricmc.fabric.api.util.NbtType;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * The sister class to ItemStack allowing for fluids to be quantifiable
 * <p>
 * Credits for the conceptualization of this class go towards the Forge team. This class is
 * heavily inspired from the Forge codebase but incredibly simplified for the sake of this
 * mod's use.
 *
 * @author Parker Hawke - Choco
 */
public class FluidStack {

    public static final FluidStack EMPTY = new FluidStack(Fluids.EMPTY, 0);

    private Fluid fluid;
    private int amount;
    private boolean empty;

    public FluidStack(Fluid fluid, int amount) {
        this.fluid = fluid;
        this.amount = amount;
        this.empty = (fluid == Fluids.EMPTY || amount == 0);
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
        this.updateEmptyState();
    }

    public Fluid getFluid() {
        return empty ? Fluids.EMPTY : fluid;
    }

    public void setAmount(int amount) {
        this.amount = Math.max(amount, 0);
        this.updateEmptyState();
    }

    public void grow(int amount) {
        Preconditions.checkArgument(!isEmpty(), "Cannot grow an empty fluid");
        this.setAmount(getAmount() + amount);
    }

    public void shrink(int amount) {
        Preconditions.checkArgument(!isEmpty(), "Cannot shrink an empty fluid");
        this.setAmount(getAmount() - amount);
    }

    public int getAmount() {
        return empty ? 0 : amount;
    }

    public boolean isEmpty() {
        return empty;
    }

    public FluidStack copy() {
        return new FluidStack(getFluid(), getAmount());
    }

    public CompoundTag writeToNBT(CompoundTag tag) {
        tag.putString("Fluid", Registry.FLUID.getId(getFluid()).toString());
        tag.putInt("Amount", getAmount());
        return tag;
    }

    private void updateEmptyState() {
        this.empty = (fluid == Fluids.EMPTY || amount <= 0);
    }

    public static FluidStack fromNBT(CompoundTag tag) {
        Fluid fluid = Fluids.EMPTY;
        int amount = 0;

        if (tag.containsKey("Fluid", NbtType.STRING)) {
            fluid = Registry.FLUID.getOrEmpty(new Identifier(tag.getString("Fluid"))).orElse(Fluids.EMPTY);
        }

        if (tag.containsKey("Amount", NbtType.INT)) {
            amount = tag.getInt("Amount");
        }

        return new FluidStack(fluid, amount);
    }

}
