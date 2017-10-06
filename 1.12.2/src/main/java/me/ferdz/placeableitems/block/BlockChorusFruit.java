package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockChorusFruit extends BlockEdible {

	public BlockChorusFruit(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		if (worldIn.isAirBlock(pos) && !worldIn.isRemote) {
			double d0 = pos.getX();
			double d1 = pos.getY();
			double d2 = pos.getZ();

			for (int i = 0; i < 16; ++i) {
				double d3 = playerIn.getPosition().getX() + (playerIn.getRNG().nextDouble() - 0.5D) * 16.0D;
				double d4 = MathHelper.clamp(playerIn.getPosition().getY() + (double) (playerIn.getRNG().nextInt(16) - 8), 0.0D, (double) (worldIn.getActualHeight() - 1));
				double d5 = playerIn.getPosition().getZ() + (playerIn.getRNG().nextDouble() - 0.5D) * 16.0D;

				if (playerIn.attemptTeleport(d3, d4, d5)) {
					worldIn.playSound((EntityPlayer) null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
					playerIn.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
					break;
				}
			}
		}
		return b;
	}
}
