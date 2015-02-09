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
import com.stuntmania.PlaceableItems.TileEntities.TEEgg;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEgg extends BlockPlaceableItems {
	
	public BlockEgg() {
		super(Material.wood);
		GameRegistry.registerBlock(this, "eggBlock");
		setBlockName("eggBlock");
		setBlockTextureName(PlaceableItems.MODID + ":destroy/" + "eggBlock");
		setHardness(0.25F);
		setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.34375F, 0.625F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEEgg();
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Items.egg;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.egg);
	}
}
