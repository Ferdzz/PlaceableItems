package com.stuntmania.placeableitems.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

//TODO: [IMPORTANT!] Remake the placeable food system
public class TEPlaceableFood extends TEPlaceableItems {
	protected int eaten;
	
	public TEPlaceableFood() {
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		eaten = nbt.getInteger("Eaten");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Eaten", eaten);
	}
	
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
					world.setBlockToAir(x, y, z);
				}
				return true;
			}
		}
		return false;
	}
}
