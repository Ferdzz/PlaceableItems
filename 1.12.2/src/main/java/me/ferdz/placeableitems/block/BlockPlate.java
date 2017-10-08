package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.tileentity.TEEdible;
import me.ferdz.placeableitems.utils.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlate extends BlockFaceable {

	public BlockPlate(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem == null || worldIn.isRemote)
			return false;
		
		if(heldItem.getItem() instanceof ItemFood) {
			BlockPlaceableItems block = null;
			
			if(heldItem.getItem() == Items.FISH || heldItem.getItem() == Items.COOKED_FISH)
				block = Utils.getFishBlock(heldItem);
			else 
				block = ModBlocks.blockMap.get(heldItem.getItem());
			
			
			if(block != null) { // if the block exists and there is an item associated to it
				if(block.getBlockState().getProperty("plated") == null) { // if the new block can't be plateable
					return false;
				}
				IBlockState newState = block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, 0, playerIn).withProperty(BlockEdible.PLATED, true).withProperty(FACING, state.getValue(FACING));
				block.onBlockPlacedBy(worldIn, pos, newState, playerIn, heldItem);
				worldIn.setBlockState(pos, newState);
				((TEEdible)worldIn.getTileEntity(pos)).setPlated(true);
				worldIn.updateObservingBlocksAt(pos, block);
				//worldIn.notifyBlockOfStateChange(pos, block);
				
				if(!playerIn.isCreative())
					heldItem.grow(-1);
				
				return true;
			}
		}
		
		return false;
	}
}
