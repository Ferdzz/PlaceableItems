package me.ferdz.placeableitems.block;

import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSlimeball extends BlockBiPosition implements IBlockBiPosition {

	public BlockSlimeball(String name) {
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
	public SoundType getSoundType() {
		return SoundType.SLIME;
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
