package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public class IngotBlock extends PlaceableItemsBlock {

	private IIcon[] icons = new IIcon[2];

	public IngotBlock() {
		super(Material.iron);
		setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		GameRegistry.registerBlock(this, "ingotBlock");
		setBlockName("ingotBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new IngotBlockTileEntity();
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

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.iron_ingot);
		case 1:
			return new ItemStack(Items.gold_ingot);
		default:
			return null;
		}
	}
}
