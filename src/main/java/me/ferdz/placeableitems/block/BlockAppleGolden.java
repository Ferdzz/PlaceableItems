package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumGoldenApple;
import me.ferdz.placeableitems.tileentity.TEGoldenApple;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAppleGolden extends BlockApple {

	public static final PropertyEnum<EnumGoldenApple> STATE = PropertyEnum.create("state", EnumGoldenApple.class);

	public BlockAppleGolden(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if(worldIn.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 20 * 5, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 20 * 60 * 2));
		}
		return b;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEGoldenApple) {
			ItemStack is = stack.copy();
			is.stackSize = 1;
			((TEGoldenApple) te).setApple(is);
		}
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TEGoldenApple) {
			ItemStack is = ((TEGoldenApple)te).getApple();
			if(is.getItemDamage() == 0)
				return state.withProperty(STATE, EnumGoldenApple.NORMAL);
			else
				return state.withProperty(STATE, EnumGoldenApple.NOTCH);
		}
		return state;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileEntity te = world.getTileEntity(pos);
		if(te != null && te instanceof TEGoldenApple) {
			return ((TEGoldenApple)te).getApple();
		}
		return null;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STATE, BlockBiPosition.POSITION, FACING });
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEGoldenApple();
	}
}
