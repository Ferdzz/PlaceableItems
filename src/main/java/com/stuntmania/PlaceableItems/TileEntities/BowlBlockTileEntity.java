package com.stuntmania.PlaceableItems.TileEntities;

import net.minecraft.nbt.NBTTagCompound;

public class BowlBlockTileEntity extends PlaceableItemsTileEntity {
	private int state;
	
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		state = nbttagcompound.getInteger("state");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("state", state);
	}
}
