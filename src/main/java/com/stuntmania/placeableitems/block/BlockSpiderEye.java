package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TESpiderEye;

public class BlockSpiderEye extends BlockPlaceableItems {
	
	public BlockSpiderEye() {
		super(Material.sponge);
		this.setBlockBounds(0.3125F, 0, 0.3125F, 0.6875F, 0.125F, 0.6875F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/spider_eye");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TESpiderEye();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		return Items.spider_eye;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.spider_eye);
	}
}
