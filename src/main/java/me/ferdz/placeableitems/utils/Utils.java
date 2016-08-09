package me.ferdz.placeableitems.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Utils {
		
	/**
	 * @param state
	 *            the block to check
	 * @return true if the block is solid enough to hold a placeable item
	 */
	public static boolean isValidBlock(IBlockAccess world, BlockPos pos) {
		return isValidBlock(EnumFacing.UP, world, pos);
	}
	
	public static boolean isValidBlock(EnumFacing face, IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		
		try {
			if (block == Blocks.LAVA || block == Blocks.WATER || block == Blocks.TORCH || block == Blocks.AIR)
				return false;
			
			if (block.isSideSolid(state, world, pos, face) || block.isNormalCube(state, world, pos) 
					|| block == Blocks.LEAVES || block == Blocks.LEAVES2)
				return true;
		} catch (Exception e) { /* this was a commonly thrown exception back in 1.7.10, so just to be safe let's catch it */
		}
		
		return false;
	}
}
