package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEDiamond;

public class BlockDiamond extends BlockPlaceableItems {

	public BlockDiamond() {
		super(Material.circuits);
		this.setBlockBounds(0, 0, 0, 1, 1, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/diamond");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TEDiamond();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.diamond;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.diamond);
	}
}
