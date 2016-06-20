package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBeetrootSoup extends BlockDrinkable {

	public BlockBeetrootSoup(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if(worldIn.isAirBlock(pos)) {
			worldIn.setBlockState(pos, ModBlocks.blockBowl.getDefaultState().withProperty(FACING, state.getValue(FACING)));
		}
		return b;
	}
}
