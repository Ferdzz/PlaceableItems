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
import com.stuntmania.placeableitems.tileentity.TEPaper;

public class BlockPaper extends BlockPlaceableItems {
	
	public BlockPaper() {
		super(Material.sponge);
		this.setBlockBounds(0.2F, 0, 0.2F, 0.8F, 0.0625F, 0.8F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/paper");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEPaper();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.paper;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.paper);
	}
}
