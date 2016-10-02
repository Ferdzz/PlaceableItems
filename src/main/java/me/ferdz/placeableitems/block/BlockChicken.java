package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumCooked;
import me.ferdz.placeableitems.utils.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockChicken extends BlockBiPositionBiEdible {

	public BlockChicken(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if(Utils.isNotFood(state.getBlock()) && state.getValue(TYPE) == EnumCooked.RAW) {
			if(worldIn.rand.nextInt(100) < 30) {
				playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 30 * 20));
			}
		}
		return b;
	}
}
