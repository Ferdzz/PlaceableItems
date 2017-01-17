package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.block.BlockFaceable;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TEEdible extends TEBase {

	protected int eaten = 0;
	protected boolean plated = false;

	public TEEdible() {
	}

	public int getEaten() {
		return this.eaten;
	}

	public void setEaten(int eaten) {
		this.eaten = eaten;
	}
	
	public boolean isPlated() {
		return plated;
	}
	
	public void setPlated(boolean plated) {
		this.plated = plated;
	}

	/**
	 * Take a bite off the TE
	 * 
	 * @return true if the block is finished consuming
	 */
	public boolean bite(int foodLevel, float saturation, EntityPlayer player, World world) {
		return bite(foodLevel, saturation, player, world, SoundEvents.ENTITY_GENERIC_EAT);
	}
	
	/**
	 * Take a bite off the TE
	 * 
	 * @return true if the block is finished consuming
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
				
				if(sound != SoundEvents.ENTITY_GENERIC_DRINK) { // if the block is not a drink
					EnumPreciseFacing facing = world.getBlockState(pos).getValue(BlockFaceable.FACING);
					world.destroyBlock(pos, false);
					if(plated) { // if the block was plated
						world.setBlockState(pos, ModBlocks.blockPlate.getDefaultState().withProperty(BlockFaceable.FACING, facing));
					}
				}
				return true;
			}
//		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		eaten = nbttagcompound.getInteger("eaten");
		plated = nbttagcompound.getBoolean("plated");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("eaten", eaten);
		nbttagcompound.setBoolean("plated", plated);
		return nbttagcompound;
	}
}
