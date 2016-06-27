package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.state.EnumApplePosition;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAppleGolden extends BlockEdible {

	public static final PropertyEnum<EnumApplePosition> POSITION = PropertyEnum.create("position", EnumApplePosition.class);

	public BlockAppleGolden(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (state.getValue(POSITION)) {
		case DOWN:
			return new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.75, 0.8125);
		case UP:
			return new AxisAlignedBB(0.1875, 0.375, 0.1875, 0.8125, 1, 0.8125);

		default:
			return null;
		}
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if(worldIn.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 20 * 5, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(22), 20 * 60 * 2));
		}
		return b;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if (facing == EnumFacing.DOWN)
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumApplePosition.UP);
		return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(POSITION, EnumApplePosition.DOWN);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(POSITION, EnumApplePosition.values()[(int) (meta / 8)]);
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
