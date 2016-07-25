package me.ferdz.placeableitems.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMagmaCream extends BlockPlaceableItems {

	public BlockMagmaCream(String name) {
		super(name);
	}

	@Override
	public boolean isBurning(IBlockAccess world, BlockPos pos) {
		return true;
	}
}
