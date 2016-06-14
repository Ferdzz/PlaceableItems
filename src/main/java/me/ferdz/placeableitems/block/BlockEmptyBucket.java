package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEmptyBucket extends BlockPlaceableItems {

	public BlockEmptyBucket(String name) {
		super(Material.WOOD, name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem != null) {
			if(heldItem.getItem().equals(Items.LAVA_BUCKET) || heldItem.getItem().equals(Items.WATER_BUCKET) || heldItem.getItem().equals(Items.MILK_BUCKET)) {
				heldItem.stackSize--;
				if(playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1))) {
					worldIn.setBlockState(pos, ModBlocks.blockLavaBucket.onBlockPlaced(worldIn, pos, EnumFacing.DOWN, hitX, hitY, hitZ, 0, playerIn));
					return true;
				}
			}
		}
		return false;
	}
}