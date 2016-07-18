package me.ferdz.placeableitems.block;

import net.minecraft.item.Item;

public class BlockCarrot extends BlockBiPositionBiEdible {

	public BlockCarrot(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}
}
