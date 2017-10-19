package me.ferdz.placeableitems.utils;

import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Utils {
		
	public static BlockPlaceableItems getFishBlock(ItemStack is) {
		FishType type = FishType.byItemStack(is);
		switch (type) {
		case COD:
			return ModBlocks.blockFish;
		case CLOWNFISH:
			return ModBlocks.blockClownfish;
		case PUFFERFISH:
			return ModBlocks.blockPufferfish;
		case SALMON:
			return ModBlocks.blockSalmon;
		}
		return null;
	}

	
	public static IBlockState placeBlock(BlockPlaceableItems block, World world, BlockPos blockPos, EnumFacing face, EntityPlayer player, ItemStack stack) {
		IBlockState state = block.onBlockPlaced(world, blockPos, face, 0, 0, 0, 0, player);
		world.setBlockState(blockPos, state);
		block.onBlockPlacedBy(world, blockPos, state, player, stack);
		block.onBlockPlacedBySide(face, blockPos, player, world);
		
		if (!player.isCreative())
			stack.grow(-1);
		
		return state;
	}
	
	/**
	 * @param state the block to check
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
			
			if (state.isSideSolid(world, pos, face) || block.isNormalCube(state, world, pos) 
					|| block == Blocks.LEAVES || block == Blocks.LEAVES2)
				return true;
		} catch (Exception e) { /* this was a commonly thrown exception back in 1.7.10, so just to be safe let's catch it */
			e.printStackTrace();
		}
		
		return false;
	}
}
