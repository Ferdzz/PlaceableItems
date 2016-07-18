package me.ferdz.placeableitems.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSalmon extends BlockBiPositionBiEdible {

	public BlockSalmon(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getDrops(world, pos, state, 0).get(0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		switch(state.getValue(TYPE)) {
		case RAW:
			list.add(new ItemStack(Items.FISH, 1, FishType.SALMON.getMetadata()));
			break;
		case COOKED:
			list.add(new ItemStack(Items.COOKED_FISH, 1, FishType.SALMON.getMetadata()));
			break;
		}
		return list;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
}
