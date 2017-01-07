package me.ferdz.placeableitems.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPufferFish extends BlockBiPositionEdible {

	public BlockPufferFish(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getDrops(world, pos, state, 0).get(0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(Items.FISH, 1, FishType.PUFFERFISH.getMetadata()));
		return list;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		
		TEEdible te = (TEEdible)worldIn.getTileEntity(pos);	// TE is removed when destroying the block
		if (te == null) {
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 15 * 20, 1));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 60 * 20, 3));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 15 * 20, 2));
		}
		
		return b;
	}
}
