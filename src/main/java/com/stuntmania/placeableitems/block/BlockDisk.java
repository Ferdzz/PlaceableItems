package com.stuntmania.placeableitems.block;

import java.util.Random;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEDisk;
import com.stuntmania.placeableitems.tileentity.TESnowball;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockDisk extends BlockPlaceableItems {
	
	public BlockDisk() {
		super(Material.sponge);
	//	setBlockTextureName(PlaceableItems.MODID + ":disk");
		setBlockBounds(0.25F, 0, 0.25F, 0.75F, 0.5F, 0.75F);
		setBlockName("diskBlock");
		GameRegistry.registerBlock(this, "diskBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEDisk();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.record_13;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.record_13);
	}
}
