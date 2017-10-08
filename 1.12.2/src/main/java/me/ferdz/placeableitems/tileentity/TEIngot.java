package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.block.BlockIngot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEIngot extends TEBase implements ITEStackHolder {
	public int stackSize;
	
	public TEIngot() {
		stackSize = 1;
	}
	
	@Override
	public ItemStack getDroppedItemStack(IBlockState state) {
		ItemStack stack = null;
		switch (state.getValue(BlockIngot.TYPE)) {
		case IRON:
			stack = new ItemStack(Items.IRON_INGOT, stackSize);
			break;
		case GOLD:
			stack = new ItemStack(Items.GOLD_INGOT, stackSize);
			break;
		}
		return stack;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		stackSize = nbttagcompound.getInteger("stackSize");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("stackSize", stackSize);
		return nbttagcompound;
	}
	
	@Override
	public ItemStack getStack() {
		throw new RuntimeException("Method getStack() for TEIngot should not be called");
	}
	
	@Override
	public void setStack(ItemStack stack) {	
		throw new RuntimeException("Method setStack() for TEIngot should not be called");
	}
}
