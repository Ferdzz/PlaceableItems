package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFireCharge extends BlockPlaceableItems {

	public BlockFireCharge(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		EntitySmallFireball fireball = null;

		double d = -(worldIn.rand.nextDouble() * (0.05 - 0.01) + 0.01);
		switch (side) {
		case EAST:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, -1, d, 0);
			break;
		case NORTH:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, 0, d, 1);
			break;
		case SOUTH:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, 0, d, -1);
			break;
		case WEST:
			fireball = new EntitySmallFireball(worldIn, pos.getX() + 0.5D, pos.getY() + 0.3D, pos.getZ() + 0.5D, 1, d, 0);
			break;
		default:
			return false;
		}

		worldIn.setBlockToAir(pos);
		worldIn.spawnEntityInWorld(fireball);
		worldIn.playSound(null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.2F + 1.0F);
		return true;
	}
}
