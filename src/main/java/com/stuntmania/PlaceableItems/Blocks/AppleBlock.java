package com.stuntmania.PlaceableItems.Blocks;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.AppleBlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class AppleBlock extends PlaceableItemsBlock {
	
	public AppleBlock(Material material) {
		super(material);
		setBlockTextureName(PlaceableItems.MODID + ":destroy/appleBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new AppleBlockTileEntity();
	}
}
