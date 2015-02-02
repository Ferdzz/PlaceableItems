package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.MelonBlockTileEntity;

public class MelonBlock extends PlaceableItemsBlock {
	
	public MelonBlock(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new MelonBlockTileEntity();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.melon;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.melon);
	}
	
}
