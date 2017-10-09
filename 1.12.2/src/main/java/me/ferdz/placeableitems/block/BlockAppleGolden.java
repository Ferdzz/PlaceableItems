package me.ferdz.placeableitems.block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.ferdz.placeableitems.state.EnumCooked;
import me.ferdz.placeableitems.tileentity.TEGoldenApple;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAppleGolden extends BlockBiPositionBiEdible {

	public BlockAppleGolden(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		boolean b = super.onBlockActivated(worldIn, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);

		if (worldIn.getBlockState(pos).getBlock() == Blocks.AIR && te instanceof TEGoldenApple) {
			ItemStack is = ((TEGoldenApple) te).getStack();
			if (is.getItemDamage() == 0) {
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
			} else {
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 3));
			}
		}
		return b;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(placer.getHeldItemMainhand().getItemDamage() == 0) {
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumCooked.RAW);
		} else {
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumCooked.COOKED);
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ItemStack is = new ItemStack(Items.GOLDEN_APPLE);
		switch (state.getValue(TYPE)) {
		case RAW:
			is.setItemDamage(0);
			break;
		case COOKED:
			is.setItemDamage(1);
			break;
		}
		return Arrays.asList(is);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getDrops(world, pos, state, 0).get(0);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEGoldenApple();
	}
}
