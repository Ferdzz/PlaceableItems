package me.ferdz.placeableitems.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Super interface for all blocks from placeable items
 * @author Ferdz
 */
public interface IBlockPlaceableItems {

	/**
	 * Will set the bounding box. Parameters are in pixels  
	 * 1 = 1 / 16 of a block
	 * @return instance of the block
	 */
	public BlockPlaceableItems setBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2);

	/**
	 * Will set the bounding box. Parameters are in pixels  
	 * 1 = 1 / 16 of a block
	 * @return
	 */
	public BlockPlaceableItems setBoundingBox(double x, double y, double z);

	public BlockPlaceableItems setBoundingBox(AxisAlignedBB a);

	public BlockPlaceableItems setItem(Item item);

	public BlockPlaceableItems setItems(Item... items);

	/**
	 * Overriden if the block needs post-te logic with accessible side
	 * 
	 */
	public void onBlockPlacedBySide(EnumFacing side, BlockPos pos, EntityPlayer player, World world);
}
