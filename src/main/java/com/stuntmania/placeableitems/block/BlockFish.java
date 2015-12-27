package com.stuntmania.placeableitems.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEFish;

public class BlockFish extends BlockPlaceableItems {
	
	/*
	 * Meta => 0 - normal uncooked 1 - normal cooked 3 - salmon uncooked 4 - salmon uncooked 6 - pufferfish
	 */
	//TODO: Add destroy particles relative to the type of fish
	//TODO: Adjust blockbounds depending on the type of fish
	public BlockFish() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/fish");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			return ((TEFish) world.getTileEntity(x, y, z)).bite(2, 0.4F, player, world, x, y, z);			
		case 1:
			return ((TEFish) world.getTileEntity(x, y, z)).bite(5, 6F, player, world, x, y, z);
		case 3:
			return ((TEFish) world.getTileEntity(x, y, z)).bite(2, 0.4F, player, world, x, y, z);		
		case 4:
			return ((TEFish) world.getTileEntity(x, y, z)).bite(6, 9.6F, player, world, x, y, z);
		default:
			return false;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TEFish();
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		
		switch(metadata) {
		case 0:
			list.add(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a()));
			break;
		case 1:
			list.add(new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.COD.func_150976_a()));
			break;
		case 3:
			list.add(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a()));
			break;
		case 4:
			list.add(new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.SALMON.func_150976_a()));
			break;
		case 6:
			list.add(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a()));
			break;
		}
		
		return list;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		switch (world.getBlockMetadata(x, y, z)) {
		case 0:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.func_150976_a());
		case 1:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.COD.func_150976_a());
		case 3:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.func_150976_a());
		case 4:
			return new ItemStack(Items.cooked_fished, 1, ItemFishFood.FishType.SALMON.func_150976_a());
		case 6:
			return new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.func_150976_a());
		default:
			return null;
		}
	}
}
