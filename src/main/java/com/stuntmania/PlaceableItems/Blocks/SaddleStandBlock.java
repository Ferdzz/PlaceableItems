package com.stuntmania.PlaceableItems.Blocks;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SaddleStandBlock extends BlockContainer {

	private IIcon[] icon = new IIcon[1];
	
	public SaddleStandBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new SaddleStandTileEntity();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon[0] = reg.registerIcon(PlaceableItems.MODID + ":saddleStand");
	}
	
	@Override
	public IIcon getIcon(int face, int meta) {
		return icon[0]; //TODO FIX BROKEN PARTICLE EFFECTS
	}
}
