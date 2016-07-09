package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumUpDown;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class BlockBiPosition extends BlockPlaceableItems implements IBlockBiPosition {

	public static final PropertyEnum<EnumUpDown> POSITION = PropertyEnum.create("position", EnumUpDown.class);

	public BlockBiPosition(String name) {
		super(name);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos.subtract(new Vec3i(0, 1, 0)))) {
			pos = pos.add(new Vec3i(0, 1, 0));
			return !worldIn.isAirBlock(pos) && !(worldIn.getBlockState(pos).getBlock() instanceof BlockPlaceableItems);
		} else {
			return super.canPlaceBlockAt(worldIn, pos);
		}
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if (facing == EnumFacing.DOWN)
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumUpDown.UP);
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumUpDown.DOWN);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(POSITION, EnumUpDown.values()[(int) (meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int position = state.getValue(POSITION).getID();
		return face + (position * 8);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { POSITION, FACING });
	}
}
