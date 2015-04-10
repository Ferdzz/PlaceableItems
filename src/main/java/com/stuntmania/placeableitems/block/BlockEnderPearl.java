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
import com.stuntmania.placeableitems.tileentity.TEEnderPearl;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEnderPearl extends BlockPlaceableItems {

	public BlockEnderPearl() {
		super(Material.glass);
		this.setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.4F, 0.3F + 0.4F);
		this.setBlockName("placeableEnderPearlBlock");
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/ender_pearl");
		GameRegistry.registerBlock(this, "placeableEnderPearlBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEEnderPearl();
	}

	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		return Items.ender_pearl;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.ender_pearl);
	}
}
