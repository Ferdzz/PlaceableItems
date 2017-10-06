package me.ferdz.placeableitems.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
	
public class TEGoldenApple extends TEEdibleBiPosition {

	private ItemStack apple;

	public TEGoldenApple() {
		super();
		apple = new ItemStack(Items.GOLDEN_APPLE);
	}

	public ItemStack getStack() {
		return this.apple;
	}

	public void setStack(ItemStack apple) {
		this.apple = apple;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		apple = new ItemStack(nbttagcompound.getCompoundTag("apple"));
//		apple.readFromNBT(nbttagcompound.getCompoundTag("apple"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound nbt2 = apple.writeToNBT(new NBTTagCompound());
		nbttagcompound.setTag("apple", nbt2);
		return nbttagcompound;
	}
}
