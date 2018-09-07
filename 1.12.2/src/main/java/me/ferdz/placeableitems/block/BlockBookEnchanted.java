package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBookEnchanted extends BlockFaceable implements ITileEntityProvider {

	public BlockBookEnchanted(String s) {
		super(s);
		
		this.hasTileEntity= true;
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
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEStack();
	}
}
