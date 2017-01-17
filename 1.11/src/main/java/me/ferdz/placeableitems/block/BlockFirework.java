package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockFirework extends BlockFaceable implements ITileEntityProvider {

	public BlockFirework(String name) {
		super(name);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEStack) {
			ItemStack is = stack.copy();
			is.setCount(1);
			((TEStack) te).setStack(is);
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TEStack) {
				ItemStack is = ((TEStack)te).getStack();
				
				EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(worldIn, (double)((float)pos.getX() + hitX), (double)((float)pos.getY() + hitY), (double)((float)pos.getZ() + hitZ), is);
				worldIn.spawnEntity(entityfireworkrocket);
//			is.onItemUse(playerIn, worldIn, pos, hand, side, hitX, hitY, hitZ);
				worldIn.setBlockToAir(pos);
				worldIn.removeTileEntity(pos);
			}
        }
		return true;
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TEStack te = (TEStack) world.getTileEntity(pos);
		return te.getStack();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEStack();
	}
}
