package me.ferdz.placeableitems.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TECookie extends TEEdible implements ITEStackHolder {
	public int stackSize;

	public TECookie() {
		stackSize = 1;
	}
	
	@Override
	public ItemStack getDroppedItemStack(IBlockState state) {
		return new ItemStack(Items.COOKIE, stackSize);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		stackSize = nbttagcompound.getInteger("stackSize");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("stackSize", stackSize);
		return nbttagcompound;
	}
	
	@Override
	public ItemStack getStack() {
		throw new RuntimeException("Method getStack() for TECookie should not be called");
	}
	
	@Override
	public void setStack(ItemStack stack) {	
		throw new RuntimeException("Method setStack() for TECookie should not be called");
	}
}
