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
import com.stuntmania.placeableitems.tileentity.TEBook;

public class BlockBook extends BlockPlaceableItems {
	
	public BlockBook() {
		super(Material.wood);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/book");
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.1F, 0.8F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEBook();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.book;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.book);
	}
}
