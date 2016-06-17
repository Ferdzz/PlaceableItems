package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEdible extends BlockPlaceableItems implements ITileEntityProvider {

	private int foodLevel;
	private float saturation;

	public BlockEdible(String name) {
		super(name);
		
		this.isBlockContainer = true;
	}
	
	public BlockEdible(String name, int foodLevel, float saturation) {
		super(name);
		
		this.foodLevel = foodLevel;
		this.saturation = saturation;
		this.isBlockContainer = true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdible) {
			((TEEdible)te).bite(foodLevel, saturation, playerIn, worldIn);
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEEdible();
	}
}
