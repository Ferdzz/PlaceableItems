package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEDisk;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockDisk extends BlockPlaceableItems {
	
	public BlockDisk() {
		super(Material.sponge);
		setBlockBounds(0.15F, 0, 0.15F, 0.85F, 0.1F, 0.85F);
		setBlockName("diskBlock");
		setBlockTextureName(PlaceableItems.MODID + ":destroy/disk");
		GameRegistry.registerBlock(this, "diskBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEDisk();
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			return new ItemStack(Items.record_13);
		case 1:
			return new ItemStack(Items.record_cat);
		case 2:
			return new ItemStack(Items.record_blocks);
		case 3:
			return new ItemStack(Items.record_chirp);
		case 4:
			return new ItemStack(Items.record_far);
		case 5:
			return new ItemStack(Items.record_mall);
		case 6:
			return new ItemStack(Items.record_mellohi);
		case 7:
			return new ItemStack(Items.record_stal);
		case 8:
			return new ItemStack(Items.record_strad);
		case 9:
			return new ItemStack(Items.record_ward);
		case 10:
			return new ItemStack(Items.record_11);
		case 11:
			return new ItemStack(Items.record_wait);
		}
		return null;
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		switch (metadata) {
		case 0:
			return Items.record_13;
		case 1:
			return Items.record_cat;
		case 2:
			return Items.record_blocks;
		case 3:
			return Items.record_chirp;
		case 4:
			return Items.record_far;
		case 5:
			return Items.record_mall;
		case 6:
			return Items.record_mellohi;
		case 7:
			return Items.record_stal;
		case 8:
			return Items.record_strad;
		case 9:
			return Items.record_ward;
		case 10:
			return Items.record_11;
		case 11:
			return Items.record_wait;
		default:
			return null;
		}
	}
}
