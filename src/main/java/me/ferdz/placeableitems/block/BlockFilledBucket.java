package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFilledBucket extends BlockPlaceableItems {

	private Item bucketItem;
	
	public BlockFilledBucket(String name) {
		super(Material.WOOD, name);
	}
	
	public BlockFilledBucket setBucketItem(Item item) {
		this.bucketItem = item;
		return this;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem != null && heldItem.getItem().equals(Items.BUCKET)) {
			if(playerIn.inventory.addItemStackToInventory(new ItemStack(bucketItem, 1))) {
				worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.getDefaultState().withProperty(FACING, state.getValue(FACING)));
				heldItem.stackSize--;
				return true;
			}
		}
		return false;
	}
}