package me.ferdz.placeableitems.tileentity.tool;

import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.nbt.NBTTagCompound;

public class TESword extends TEStack {
	private EnumSword model;
	
	public TESword() {
		super();
		model = EnumSword.TOP;
	}
	
	public EnumSword getModel() {
		return model;
	}

	public void setModel(EnumSword model) {
		this.model = model;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		model = EnumSword.values()[(nbttagcompound.getInteger("swordType"))];
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		NBTTagCompound nbttagcompound2 = super.writeToNBT(nbttagcompound);
		nbttagcompound2.setInteger("swordType", model.ordinal());
		return nbttagcompound2;
	}
}
