package me.ferdz.placeableitems.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class TEEdible extends TileEntity {

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
				world.setBlockToAir(pos);
				return true;
			}
//		}
		return false;
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		return writeToNBT(tag);
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		super.handleUpdateTag(tag);
		readFromNBT(tag);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		tag = writeToNBT(tag);
		return new SPacketUpdateTileEntity(this.pos, this.getBlockMetadata(), tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
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
