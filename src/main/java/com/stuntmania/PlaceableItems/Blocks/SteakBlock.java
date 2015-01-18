package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import com.stuntmania.PlaceableItems.TileEntities.SteakTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SteakBlock extends BlockContainer {

	public SteakBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
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
		return new SteakTileEntity();
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.cooked_beef;
	}
}
