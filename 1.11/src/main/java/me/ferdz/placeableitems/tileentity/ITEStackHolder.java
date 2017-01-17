package me.ferdz.placeableitems.tileentity;

import net.minecraft.item.ItemStack;

public interface ITEStackHolder {
	public ItemStack getStack();

	public void setStack(ItemStack stack);	
}