package me.ferdz.placeableitems.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TEEdible extends TEBase {

	protected int eaten = 0;

	public TEEdible() {
	}

	public int getEaten() {
		return this.eaten;
	}

	public void setEaten(int eaten) {
		this.eaten = eaten;
	}

	public boolean bite(int foodLevel, float saturation, EntityPlayer player, World world) {
		return bite(foodLevel, saturation, player, world, SoundEvents.ENTITY_GENERIC_EAT);
	}
	
	/**
	 * Take a bite off the TE
	 * 
	 * @param foodLevel
	 * @param saturation
	 * @param player
	 * @param world
	 * @return
	 */
	public boolean bite(int foodLevel, float saturation, EntityPlayer player, World world, SoundEvent sound) {
		FoodStats food = player.getFoodStats();
//		if (food.needFood()) {
			eaten++;
			world.playSound(player, pos, sound, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			if (eaten < 0) {
				eaten = 0;
			} else if (eaten > 6) {
				eaten = 0;
				food.addStats(foodLevel, saturation);
				world.playSound(player, pos, sound, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
				
				if(sound != SoundEvents.ENTITY_GENERIC_DRINK)
					world.destroyBlock(pos, false);
				return true;
			}
//		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		eaten = nbttagcompound.getInteger("eaten");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("eaten", eaten);
		return nbttagcompound;
	}
}
