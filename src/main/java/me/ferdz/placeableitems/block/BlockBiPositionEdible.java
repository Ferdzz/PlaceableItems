package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumUpDown;
import me.ferdz.placeableitems.tileentity.TEEdibleBiPosition;
import me.ferdz.placeableitems.utils.BiPositionUtils;
import me.ferdz.placeableitems.utils.Utils;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBiPositionEdible extends BlockEdible implements IBlockBiPosition {

	public BlockBiPositionEdible(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB box = super.getBoundingBox(state, source, pos);
		return BiPositionUtils.getReverseBound(box, state.getValue(BlockBiPosition.POSITION));
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return true;
		} else if (Utils.isValidBlock(worldIn,pos.subtract(new Vec3i(0, 1, 0)))) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);
		EnumUpDown upDown = EnumUpDown.DOWN;
		if(te instanceof TEEdibleBiPosition) {
			upDown = ((TEEdibleBiPosition) world.getTileEntity(pos)).getPosition();
		} else {
			upDown = state.getValue(BlockBiPosition.POSITION);
		}
		return BiPositionUtils.canBlockStay(upDown, world, pos);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if (facing == EnumFacing.DOWN)
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockBiPosition.POSITION, EnumUpDown.UP);
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(BlockBiPosition.POSITION, EnumUpDown.DOWN);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(BlockBiPosition.POSITION, EnumUpDown.values()[(int) (meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int position = state.getValue(BlockBiPosition.POSITION).getID();
		return face + (position * 8);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockBiPosition.POSITION, FACING });
	}
}
