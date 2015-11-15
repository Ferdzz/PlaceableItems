package com.stuntmania.placeableitems.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class TEBowl extends TEPlaceableFood {
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
	
	@Override
	public boolean bite(int foodLevel, float saturation, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.isRemote) {
			FoodStats food = player.getFoodStats();
			if (food.needFood()) {
				eaten++;
				world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
				if (eaten < 0) {
					eaten = 0;
				}
				else if (eaten > 6) {
					eaten = 0;
					food.addStats(foodLevel, saturation);
					this.setState(0);
				}
				return true;
			}
		}
		return false;
	}
}
