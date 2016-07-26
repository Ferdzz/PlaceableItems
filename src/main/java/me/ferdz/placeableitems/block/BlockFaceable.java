package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumPreciseFacing;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockFaceable extends BlockPlaceableItems {

	public static final PropertyEnum<EnumPreciseFacing> FACING = PropertyDirection.create("facing", EnumPreciseFacing.class);

	public BlockFaceable(String name) {
		this(name, Material.WOOD);
	}
	
	public BlockFaceable(String name, Material material) {
		super(name, material);
		
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumPreciseFacing.D0));
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.values()[MathHelper.abs_int((MathHelper.floor_double(placer.rotationYawHead / 45.0D + 0.5D) & 7) % EnumPreciseFacing.values().length)]);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).ordinal();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
    }
}
