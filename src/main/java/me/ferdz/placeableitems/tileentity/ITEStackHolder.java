package me.ferdz.placeableitems.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public interface ITEStackHolder {
	public ItemStack getStack();

	public void setStack(ItemStack stack);
	
	public ItemStack getDroppedItemStack(IBlockState state);
}