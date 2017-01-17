package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.state.EnumUpDown;
import net.minecraft.nbt.NBTTagCompound;

public class TEEdibleBiPosition extends TEEdible {
	private EnumUpDown position;
	
	public TEEdibleBiPosition() {
		super();
		position = EnumUpDown.DOWN;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		position = EnumUpDown.values()[nbttagcompound.getInteger("position")];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("position", position.ordinal());
		return nbttagcompound;
	}
	
	public EnumUpDown getPosition() {
		return position;
	}
	
	public void setPosition(EnumUpDown position) {
		this.position = position;
	}
}
