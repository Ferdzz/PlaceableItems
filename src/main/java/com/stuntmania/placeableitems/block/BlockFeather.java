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
import com.stuntmania.placeableitems.tileentity.TEFeather;

public class BlockFeather extends BlockPlaceableItems {
	
	public BlockFeather() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.2F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/feather");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEFeather();
	}
	
	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		return Items.feather;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.feather);
	}
}
