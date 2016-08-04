package me.ferdz.placeableitems.block;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Code taken from BlockEndRod
 * 
 * @author Ferdz
 */
public class BlockRod extends BlockPlaceableItems {

	protected static final AxisAlignedBB END_ROD_VERTICAL_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
	protected static final AxisAlignedBB END_ROD_NS_AABB = new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
	protected static final AxisAlignedBB END_ROD_EW_AABB = new AxisAlignedBB(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);

	public BlockRod(String name) {
		super(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDirectional.FACING, EnumFacing.UP));
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(BlockDirectional.FACING, rot.rotate((EnumFacing) state.getValue(BlockDirectional.FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed blockstate.
	 */
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withProperty(BlockDirectional.FACING, mirrorIn.mirror((EnumFacing) state.getValue(BlockDirectional.FACING)));
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (((EnumFacing) state.getValue(BlockDirectional.FACING)).getAxis()) {
		case X:
		default:
			return END_ROD_EW_AABB;
		case Z:
			return END_ROD_NS_AABB;
		case Y:
			return END_ROD_VERTICAL_AABB;
		}
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the IBlockstate
	 */
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));

		if (iblockstate.getBlock() == this) {
			EnumFacing enumfacing = (EnumFacing) iblockstate.getValue(BlockDirectional.FACING);

			if (enumfacing == facing) {
				return this.getDefaultState().withProperty(BlockDirectional.FACING, facing.getOpposite());
			}
		}

		return this.getDefaultState().withProperty(BlockDirectional.FACING, facing);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();
		iblockstate = iblockstate.withProperty(BlockDirectional.FACING, EnumFacing.getFront(meta));
		return iblockstate;
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(BlockDirectional.FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockDirectional.FACING });
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
}
