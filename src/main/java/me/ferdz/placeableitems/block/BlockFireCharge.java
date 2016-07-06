package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFireCharge extends BlockPlaceableItems {

	public BlockFireCharge(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote)
			return true;
		
		EntitySmallFireball fireball = null;

		switch (side) {
		case EAST:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D - 1, pos.getY() + 0.3D, pos.getZ() + 0.5D, -1, 0, 0);
			worldIn.spawnEntityInWorld(fireball);
			worldIn.destroyBlock(pos, false);
			return true;
		case NORTH:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D + 1, 0, 0, 1);
			worldIn.spawnEntityInWorld(fireball);
			worldIn.destroyBlock(pos, false);
			return true;
		case SOUTH:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D - 1, 0, 0, -1);
			worldIn.spawnEntityInWorld(fireball);
			worldIn.destroyBlock(pos, false);
			return true;
		case WEST:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D + 1, pos.getY() + 0.3D, pos.getZ() + 0.5D, 1, 0, 0);
			worldIn.spawnEntityInWorld(fireball);
			worldIn.destroyBlock(pos, false);
			return true;
		}
		return false;
	}
}
