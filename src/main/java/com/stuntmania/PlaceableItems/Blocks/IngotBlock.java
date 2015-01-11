package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;

public class IngotBlock extends BlockContainer {

	public IngotBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new IngotBlockTileEntity();
	}
	
	@Override
	public int getRenderType() {
		return 8;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F,0.1F,0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x  + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}
	//TODO MAKE IT 1/9 OF A METER
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F,0.1F,0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x  + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}
	
	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		if(meta == 0) {
			return Items.iron_ingot;
		} else if (meta == 1) {
			return Items.gold_ingot;
		} else {
			return null;
		}
	}
}
