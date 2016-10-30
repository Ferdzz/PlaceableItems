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
import com.stuntmania.placeableitems.tileentity.TEBucket;

public class BlockBucket extends BlockPlaceableItems {
	
	//TODO: Make milk drinkable
	
	public BlockBucket() {
		super(Material.circuits);
		this.setBlockBounds(0.25F, 0, 0.25F, 0.75F, 0.4375F, 0.75F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/bucket");
		this.setHardness(0.8F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEBucket();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		switch (meta) {
		case 0:
			return Items.bucket;
		case 1:
			return Items.water_bucket;
		case 2:
			return Items.lava_bucket;
		case 3:
			return Items.milk_bucket;
		default:
			return null;
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.bucket);
		case 1:
			return new ItemStack(Items.water_bucket);
		case 2:
			return new ItemStack(Items.lava_bucket);
		case 3:
			return new ItemStack(Items.milk_bucket);
		default:
			return null;
		}
	}
}
