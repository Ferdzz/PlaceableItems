package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumCooked;
import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockBiEdible extends BlockEdible {
	
	public static final PropertyEnum<EnumCooked> TYPE = PropertyEnum.create("type", EnumCooked.class);
	
	private int rawFoodLevel, cookedFoodLevel;
	private float rawSaturation, cookedSaturation;
	private Item rawItem, cookedItem;
	
	public BlockBiEdible(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name);
		this.rawItem = rawItem;
		this.rawFoodLevel = rawFoodLevel;
		this.rawSaturation = rawSaturation;
		this.cookedItem = cookedItem;
		this.cookedFoodLevel = cookedFoodLevel;
		this.cookedSaturation = cookedSaturation;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdible) {
			switch (state.getValue(TYPE)) {
			case RAW:
				((TEEdible)te).bite(rawFoodLevel, rawSaturation, playerIn, worldIn);
				break;
			case COOKED:
				((TEEdible)te).bite(cookedFoodLevel, cookedSaturation, playerIn, worldIn);
				break;
			}
			
			return true;
		}
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(placer.getHeldItemMainhand().getItem().equals(rawItem)) {
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumCooked.RAW);
		} else if (placer.getHeldItemMainhand().getItem().equals(cookedItem)) {
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumCooked.COOKED);
		}
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		switch (state.getValue(TYPE)) {
		case RAW:
			return new ItemStack(rawItem);
		case COOKED:
			return new ItemStack(cookedItem);

		default:
			return null;
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch (state.getValue(TYPE)) {
		case RAW:
			return rawItem;
		case COOKED:
			return cookedItem;

		default:
			return null;
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(TYPE, EnumCooked.values()[(int)(meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int position = state.getValue(TYPE).getID();
		return face + (position * 8);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ TYPE, FACING });
    }
}
