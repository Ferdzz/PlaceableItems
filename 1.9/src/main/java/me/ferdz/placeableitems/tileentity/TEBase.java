package me.ferdz.placeableitems.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TEBase extends TileEntity {
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
}
