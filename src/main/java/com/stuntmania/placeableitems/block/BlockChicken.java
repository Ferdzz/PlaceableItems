package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEChicken;

public class BlockChicken extends BlockPlaceableItems {
		
	//TODO: Add cooked chicken
	//TODO: Make texture look prettier
	
	public BlockChicken() {
		super(Material.sponge);
		this.setBlockBounds(0F, 0.0F, 0F, 1F, 0.34375F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/chicken");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ((TEChicken)world.getTileEntity(x, y, z)).bite(player, world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEChicken();
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Items.chicken;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.chicken);
	}
}
