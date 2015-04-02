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
import com.stuntmania.placeableitems.tileentity.TEFish;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockFish extends BlockPlaceableItems {
	
	public BlockFish() {
		super(Material.sponge);
		setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		setBlockTextureName(PlaceableItems.MODID + ":destroy/fish");
		GameRegistry.registerBlock(this, "fishBlock");
		setBlockName("fishBlock");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TEFish.bite(player, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEFish();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.cooked_fished;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.cooked_fished);
	}
}
