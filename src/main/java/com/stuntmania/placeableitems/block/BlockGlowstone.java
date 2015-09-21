package com.stuntmania.placeableitems.block;

import java.util.Random;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEGlowstone;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockGlowstone extends BlockPlaceableItems {
	
	public BlockGlowstone() {
		super(Material.sand);
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.3125F, 0.875F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/glowstone_dust");
		this.setLightLevel(0.5F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TEGlowstone();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.glowstone_dust;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.glowstone_dust);
	}
}
