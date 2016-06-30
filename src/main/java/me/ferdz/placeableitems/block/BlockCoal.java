package me.ferdz.placeableitems.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ferdz.placeableitems.block.state.EnumCoal;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCoal extends BlockPlaceableItems {

	public static final PropertyEnum<EnumCoal> STATE = PropertyEnum.create("state", EnumCoal.class);

	public BlockCoal(String name) {
		super(name);
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		ItemStack is = placer.getHeldItemMainhand();
		if(is.getItemDamage() == 0)
			return getDefaultState().withProperty(STATE, EnumCoal.COAL);
		else
			return getDefaultState().withProperty(STATE, EnumCoal.CHARCOAL);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getDrops(world, pos, state, 0).get(0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		switch(state.getValue(STATE)) {
		case COAL:
			list.add(new ItemStack(Items.COAL, 1, 0));
			break;
		case CHARCOAL:
			list.add(new ItemStack(Items.COAL, 1, 1));
			break;
		}
		return list;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(STATE, EnumCoal.values()[(int) (meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int position = state.getValue(STATE).getID();
		return face + (position * 8);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STATE, FACING });
	}
}
