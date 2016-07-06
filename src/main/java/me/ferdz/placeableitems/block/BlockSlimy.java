package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSlimy extends BlockPlaceableItems {

	public BlockSlimy(String name) {
		super(name);
	}

	@Override
	public void onFallenUpon(World world, BlockPos position, Entity entity, float amount) {
		if (entity.isSneaking())
			super.onFallenUpon(world, position, entity, amount);
		else
			entity.fall(amount, 0.0F);
	}
	
	@Override
	public void onLanded(World world, Entity player) {
		if (player.isSneaking()) {
			super.onLanded(world, player);
		} else if (player.motionY < 0.0D) {
			player.motionY = (-player.motionY);

			if (!(player instanceof EntityLivingBase))
				player.motionY *= 0.8D;
		}
	}
}
