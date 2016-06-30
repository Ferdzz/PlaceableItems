package me.ferdz.placeableitems.block.tool;

import java.util.Random;

import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.tileentity.TETool;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockTool extends BlockPlaceableItems implements ITileEntityProvider {

	public BlockTool(String name) {
		super(name);
		
		this.isBlockContainer = true;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TETool) {
			ItemStack is = stack.copy();
			is.stackSize = 1;
			((TETool) te).setTool(is);
		}
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TETool te = (TETool) world.getTileEntity(pos);
		return te.getTool();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TETool();
	}
}
