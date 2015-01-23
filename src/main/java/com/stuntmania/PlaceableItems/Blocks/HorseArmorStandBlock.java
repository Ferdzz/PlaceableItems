package com.stuntmania.PlaceableItems.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.HorseArmorStandTileEntity;

public class HorseArmorStandBlock extends PlaceableItemsBlock {
	private IIcon icon;

	
	public HorseArmorStandBlock(Material material) {
		super(material);
	}
	
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new HorseArmorStandTileEntity();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":woodBowlBlock");
	}
	
	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}
}
