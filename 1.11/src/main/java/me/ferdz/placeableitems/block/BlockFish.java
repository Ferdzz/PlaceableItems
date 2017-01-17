package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;

public class BlockFish extends BlockBiPositionBiEdible {

	public BlockFish(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
