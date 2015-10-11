package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEFish;

public class BlockFish extends BlockPlaceableItems {
	
	/*
	 * Meta => 0 - normal uncooked 1 - normal cooked 2 - salmon uncooked 3 - salmon uncooked
	 */
	//TODO: Add destroy particles
	public BlockFish() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/fish");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ((TEFish) world.getTileEntity(x, y, z)).bite(player, world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
		case 0:
			return new TEFish(2, 0.4F);
		case 1:
			return new TEFish(5, 6F);
		case 3:
			return new TEFish(2, 0.4F);
		case 4:
			return new TEFish(6, 9.6F);
		default:
			return null;
		}
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		switch (meta) {
		case 0:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()).getItem();
		case 1:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.COD.func_150976_a()).getItem();
		case 2:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()).getItem();
		case 3:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.SALMON.func_150976_a()).getItem();
		default:
			return null;
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a());
		case 1:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.COD.func_150976_a());
		case 2:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a());
		case 3:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.SALMON.func_150976_a());
		default:
			return null;
		}
	}
}
