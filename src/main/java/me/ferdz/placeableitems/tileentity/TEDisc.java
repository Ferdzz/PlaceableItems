package me.ferdz.placeableitems.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEDisc extends TileEntity {

	private ItemStack disc;

	public TEDisc() {
		disc = new ItemStack(Items.RECORD_11);
	}

	public ItemStack getDisc() {
		return this.disc;
	}

	public void setDisc(ItemStack disc) {
		this.disc = disc;
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
		IBlockState state = worldObj.getBlockState(pos);
		readFromNBT(pkt.getNbtCompound());
		IBlockState newState = worldObj.getBlockState(pos);
		worldObj.notifyBlockUpdate(pos, state, newState, 2);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		disc.readFromNBT(nbttagcompound.getCompoundTag("disc"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound nbt2 = disc.writeToNBT(new NBTTagCompound());
		nbttagcompound.setTag("disc", nbt2);
		return nbttagcompound;
	}
}
