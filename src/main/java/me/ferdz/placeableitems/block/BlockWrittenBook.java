package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.tileentity.TEBook;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWrittenBook extends BlockPlaceableItems implements ITileEntityProvider {

	public BlockWrittenBook(String name) {
		super(name);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEBook) {
			((TEBook) te).setBook(stack);
			System.out.println(stack);
			worldIn.notifyBlockOfStateChange(pos, ModBlocks.blockBookAndQuill);
			te.markDirty();
		}
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEBook();
	}
}
