package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEEgg;

public class BlockEgg extends BlockPlaceableItems {
	
	//TODO: Add breaking sound when walking on block
	//TODO: Fix bug where TE is still registered after walking on egg
	
	public BlockEgg() {
		super(Material.wood);
		this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.34375F, 0.625F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/" + "egg");
		this.setHardness(0.25F);
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
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (!(entity instanceof EntityItem)) { 
			world.removeTileEntity(x, y, z);
			world.setBlockToAir(x, y, z);
			Random rand = new Random();
			if(rand.nextInt(10) <= 1) {
				EntityChicken chicken = new EntityChicken(world);
				chicken.setPosition(x, y, z);
				world.spawnEntityInWorld(chicken);
			}
		}
	}
}
