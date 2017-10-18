package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExperienceBottle extends BlockBiPosition {

	public BlockExperienceBottle(String name) {
		super(name);
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {		
		if (!worldIn.isRemote) {
			worldIn.playEvent(2002, pos, 0);
			
			int i = 3 + worldIn.rand.nextInt(5) + worldIn.rand.nextInt(5);
			while (i > 0) {
				int j = EntityXPOrb.getXPSplit(i);
				i -= j;
				worldIn.spawnEntity(new EntityXPOrb(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, j));
//				worldIn.spawnEntityInWorld(new EntityXPOrb(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, j));
			}

			worldIn.setBlockToAir(pos);
		}

		return true;
	}
}
