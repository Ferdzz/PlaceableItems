package me.ferdz.placeableitems.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEStack extends TEBase {

	private ItemStack stack;

	public TEStack() {
		stack = new ItemStack(Items.STICK);
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		stack.readFromNBT(nbttagcompound.getCompoundTag("stack"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound nbt2 = stack.writeToNBT(new NBTTagCompound());
		nbttagcompound.setTag("stack", nbt2);
		return nbttagcompound;
	}
}
