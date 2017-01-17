package me.ferdz.placeableitems.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEPotion extends TEEdibleBiPosition implements ITEStackHolder {

	private ItemStack stack;

	public TEPotion() {
		stack = new ItemStack(Items.STICK);
	}
	
	@Override
	public ItemStack getDroppedItemStack(IBlockState state) {
		if(stack.getItem() == Items.STICK)
			return null;
		return stack;
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

	public ItemStack getStack() {
		return this.stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}
}
