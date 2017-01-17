package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumBlazeRod;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBlazeRod extends BlockRod {

	public static final PropertyEnum<EnumBlazeRod> STATE = PropertyEnum.create("state", EnumBlazeRod.class);
	
	public BlockBlazeRod(String name) {
		super(name);
		
		this.setDefaultState(super.getDefaultState().withProperty(STATE, EnumBlazeRod.OFF));
	}
	
	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		if(state.getValue(STATE) == EnumBlazeRod.ON)
			return 15;
		return 0;
	}

	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (stateIn.getValue(STATE) == EnumBlazeRod.ON) {
			EnumFacing enumfacing = (EnumFacing) stateIn.getValue(BlockDirectional.FACING);
			double d0 = (double) pos.getX() + 0.55D - (double) (rand.nextFloat() * 0.1F);
			double d1 = (double) pos.getY() + 0.55D - (double) (rand.nextFloat() * 0.1F);
			double d2 = (double) pos.getZ() + 0.55D - (double) (rand.nextFloat() * 0.1F);
			double d3 = (double) (0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);

			if (rand.nextInt(5) == 0) {
				worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + (double) enumfacing.getFrontOffsetX() * d3, d1 + (double) enumfacing.getFrontOffsetY() * d3, d2 + (double) enumfacing.getFrontOffsetZ() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, new int[0]);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem == null || !heldItem.getItem().equals(Items.FLINT_AND_STEEL))
			return false;
		
		heldItem.damageItem(1, playerIn);
		worldIn.setBlockState(pos, state.withProperty(STATE, EnumBlazeRod.ON));
		
		return true;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		iblockstate = iblockstate.withProperty(BlockDirectional.FACING, EnumFacing.getFront(meta % 6));
		iblockstate = iblockstate.withProperty(STATE, EnumBlazeRod.values()[meta / 6]);
		return iblockstate;
	}
	
	public int getMetaFromState(IBlockState state) {
		int meta = ((EnumFacing) state.getValue(BlockDirectional.FACING)).getIndex();
		if(state.getValue(STATE) == EnumBlazeRod.ON)
			meta = meta + 6;
		return meta;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STATE, BlockDirectional.FACING });
	}
}