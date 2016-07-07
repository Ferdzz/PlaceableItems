package me.ferdz.placeableitems.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSnowball extends BlockPlaceableItems {

	public BlockSnowball(String name) {
		super(name, Material.SNOW);
	}
	
	@Override
	public SoundType getSoundType() {
		return SoundType.SNOW;
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		worldIn.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState());
	}
}
