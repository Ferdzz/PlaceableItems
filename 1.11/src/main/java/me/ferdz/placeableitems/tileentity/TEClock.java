package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.state.EnumClockSide;
import net.minecraft.nbt.NBTTagCompound;

public class TEClock extends TEBase {

	private EnumClockSide side;
	
	public TEClock() {
		this.side = EnumClockSide.DOWN; 
	}

	public EnumClockSide getSide() {
		return side;
	}

	public void setSide(EnumClockSide side) {
		this.side = side;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.side = EnumClockSide.values()[compound.getInteger("side")];
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("side", side.ordinal());
		return super.writeToNBT(compound);
	}
}
