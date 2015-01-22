package com.stuntmania.PlaceableItems.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.HorseArmorStandTileEntity;

public class HorseArmorStandBlock extends BlockContainer {

	public HorseArmorStandBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
	}
	
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new HorseArmorStandTileEntity();
	}
}
