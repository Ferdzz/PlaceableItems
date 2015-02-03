package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.FishBlockTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public class FishBlock extends PlaceableItemsBlock {
	
	public FishBlock() {
		super(Material.sponge);
		setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		GameRegistry.registerBlock(this, "fishBlock");
		setBlockName("fishBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new FishBlockTileEntity();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.fish;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.fish);
	}
}
