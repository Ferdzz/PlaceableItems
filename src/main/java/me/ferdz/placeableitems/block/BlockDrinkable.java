package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDrinkable extends BlockEdible {
	
	public BlockDrinkable(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	/**
	 * Block at BlockPos will be set to Blocks.AIR if the blockActivated was the last iteration
	 */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdible) {
			((TEEdible)te).bite(foodLevel, saturation, playerIn, worldIn, SoundEvents.ENTITY_GENERIC_DRINK);
			return true;
		}
		return false;
	}
}
