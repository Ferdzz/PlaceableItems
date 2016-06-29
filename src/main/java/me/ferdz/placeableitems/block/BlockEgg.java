package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEgg extends BlockPlaceableItems {

	public BlockEgg(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			worldIn.destroyBlock(pos, false);
			int rand = worldIn.rand.nextInt(8);
			if(rand == 0) {
                EntityChicken entitychicken = new EntityChicken(worldIn);
                entitychicken.setGrowingAge(-24000);
                entitychicken.setPosition(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
                worldIn.spawnEntityInWorld(entitychicken);
			}
		}
		return true;
	}
}
