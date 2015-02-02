package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.AppleBlockTileEntity;

public class AppleBlock extends PlaceableItemsBlock {
	
	public AppleBlock(Material material) {
		super(material);
		setBlockTextureName(PlaceableItems.MODID + ":destroy/appleBlock");
		setBlockBounds(0.1F, 0, 0.1F, 0.9F, 0.9F, 0.9F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new AppleBlockTileEntity();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.apple;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(PlaceableItems.appleBlock);
	}
}
