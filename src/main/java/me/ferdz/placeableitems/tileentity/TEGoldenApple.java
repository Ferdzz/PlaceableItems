package me.ferdz.placeableitems.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
	
public class TEGoldenApple extends TEEdible implements ITEStackHolder {

	private ItemStack apple;

	public TEGoldenApple() {
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
		apple.readFromNBT(nbttagcompound.getCompoundTag("apple"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		NBTTagCompound nbt2 = apple.writeToNBT(new NBTTagCompound());
		nbttagcompound.setTag("apple", nbt2);
		return nbttagcompound;
	}
}
