package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPotatoPoisoned extends BlockEdible {

	public BlockPotatoPoisoned(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if(worldIn.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
			if(worldIn.rand.nextInt(100) <= 60)
				playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(19), 20 * 4));
		}
		return b;
	}
}
