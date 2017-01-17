package me.ferdz.placeableitems.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TEIngot extends TEBase {
	public int stackSize;
	
	public TEIngot() {
		stackSize = 1;
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
}
