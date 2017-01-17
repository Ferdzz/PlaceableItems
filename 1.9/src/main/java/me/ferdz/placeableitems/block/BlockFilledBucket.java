package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFilledBucket extends BlockFaceable implements ITileEntityProvider {

	private Item bucketItem;
	
	public BlockFilledBucket(String name) {
		super(name);
	}
	
	public BlockFilledBucket setBucketItem(Item item) {
		this.bucketItem = item;
		return this;
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		if(bucketItem == Items.LAVA_BUCKET) {
			return 10;
		}
		return super.getLightValue(state, world, pos);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem != null && heldItem.getItem().equals(Items.BUCKET)) {
			worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.getDefaultState().withProperty(FACING, state.getValue(FACING)));
			if(!playerIn.isCreative())
				if(playerIn.inventory.addItemStackToInventory(new ItemStack(bucketItem, 1))) {
					heldItem.stackSize--;
					return true;
				}
		} else if (bucketItem.equals(Items.MILK_BUCKET)) {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TEEdible) {
				TEEdible te2 = (TEEdible)te;
				if(te2.bite(0, 0, playerIn, worldIn, SoundEvents.ENTITY_GENERIC_DRINK)) {
					playerIn.clearActivePotions();
					worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.getDefaultState().withProperty(FACING, state.getValue(FACING)));
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEEdible();
	}
}