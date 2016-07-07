package me.ferdz.placeableitems.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPufferFish extends BlockEdible {

	public BlockPufferFish(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}
	
//	@Override
//	public SoundType getSoundType() {
//		return SoundType.SLIME;
//	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if (worldIn.isAirBlock(pos) && !worldIn.isRemote) {
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 1, 15 * 20));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 3, 60 * 20));
			playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 2, 15 * 20));
		}
		return b;
	}
}
