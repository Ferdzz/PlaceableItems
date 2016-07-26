package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumUpDown;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockApple extends BlockEdible implements IBlockBiPosition {

	public BlockApple(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB box = super.getBoundingBox(state, source, pos);
		
		switch (state.getValue(BlockBiPosition.POSITION)) {
		case DOWN:
			return box;
		case UP:
			return new AxisAlignedBB(box.minX, 1 - box.maxY, box.minZ, box.maxX, 1, box.maxZ);

		default:
			return null;
		}
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
