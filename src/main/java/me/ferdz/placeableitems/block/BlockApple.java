package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.state.EnumApplePosition;
import me.ferdz.placeableitems.block.state.EnumCarrotType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockApple extends BlockPlaceableItems {

	public static final PropertyEnum<EnumApplePosition> POSITION = PropertyEnum.create("position", EnumApplePosition.class);
	
	public BlockApple(String name) {
		super(Material.WOOD, name);
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(facing == EnumFacing.DOWN)
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumApplePosition.UP);
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumApplePosition.DOWN);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta % 4));
		s = s.withProperty(POSITION, EnumApplePosition.values()[(int)(meta / 4)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal() - 2;
		int fluid = state.getValue(POSITION).getID();
		return face + (fluid * 4);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ POSITION, FACING });
    }
}
