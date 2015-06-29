package com.stuntmania.placeableitems.block;

import java.util.Random;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEClay;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockClay extends BlockPlaceableItems {
	
	public BlockClay() {
		super(Material.clay);
		this.setBlockBounds(0, 0, 0, 1, 0.5F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/clay_ball");
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.clay_ball;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.clay_ball);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEClay();
	}
}
