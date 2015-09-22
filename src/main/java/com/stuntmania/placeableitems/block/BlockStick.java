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
import com.stuntmania.placeableitems.tileentity.TEStick;

public class BlockStick extends BlockPlaceableItems {
	
	public BlockStick() {
		super(Material.wood);
		this.setBlockBounds(0, 0, 0, 1, 0.0625F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/stick");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TEStick();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		return Items.stick;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.stick);
	}
	
}
