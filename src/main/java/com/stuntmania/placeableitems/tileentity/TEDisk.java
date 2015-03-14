package com.stuntmania.placeableitems.tileentity;

import net.minecraft.nbt.NBTTagCompound;


public class TEDisk extends TEPlaceableItems {
	
	/*
	 * 0=13
	 * 1=cat
	 * 2=blocks
	 * 3=chirp
	 * 4=far
	 * 5=mall
	 * 6=mellohi
	 * 7=stal
	 * 8=strad
	 * 9=ward
	 * 10=11
	 * 11=wait
	 */
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
