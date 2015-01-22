package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;

public class IngotBlock extends PlaceableItemsBlock {

	private IIcon[] icons = new IIcon[2];

	public IngotBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new IngotBlockTileEntity();
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F, 0.1F, 0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}

	// TODO FIX IT
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F, 0.1F, 0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(PlaceableItems.MODID + ":ironBlock");
		icons[1] = reg.registerIcon(PlaceableItems.MODID + ":goldBlock");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int p_149650_3_) {
		switch (meta) {
		case 0:
			return Items.iron_ingot;
		case 1:
			return Items.gold_ingot;
		default:
			return null;
		}
	}
}
