package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumIngot;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockIngot extends BlockPlaceableItems {

	public static final PropertyEnum<EnumIngot> TYPE = PropertyEnum.create("type", EnumIngot.class);
	
	public BlockIngot(String name) {
		super(name);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		switch (state.getValue(TYPE)) {
		case GOLD:
			return new ItemStack(Items.GOLD_INGOT, 1);
		case IRON:
			return new ItemStack(Items.IRON_INGOT, 1);

		default:
			return null;
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch (state.getValue(TYPE)) {
		case GOLD:
			return Items.GOLD_INGOT;
		case IRON:
			return Items.GOLD_INGOT;

		default:
			return null;
		}
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(placer.getHeldItemMainhand().getItem().equals(Items.GOLD_INGOT))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumIngot.GOLD);
		else if(placer.getHeldItemMainhand().getItem().equals(Items.IRON_INGOT))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumIngot.IRON);
		return null;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(TYPE, EnumIngot.values()[(int)(meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int type = state.getValue(TYPE).getID();
		return face + (type * 8);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ TYPE, FACING });
    }
}
