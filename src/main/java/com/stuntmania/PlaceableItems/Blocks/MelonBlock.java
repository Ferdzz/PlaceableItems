package com.stuntmania.PlaceableItems.Blocks;

import com.stuntmania.PlaceableItems.TileEntities.MelonBlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MelonBlock extends PlaceableItemsBlock {
	
	public MelonBlock(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new MelonBlockTileEntity();
	}
	
}
