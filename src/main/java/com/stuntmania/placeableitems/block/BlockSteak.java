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
import com.stuntmania.placeableitems.tileentity.TESteak;

public class BlockSteak extends BlockPlaceableItems {

	//TODO: Remodel to remove the curvy parts on the sides of steaks
	//TODO: Stop model from hovering above ground
	
	public BlockSteak() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":steak");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ((TESteak)world.getTileEntity(x, y, z)).bite(player, world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TESteak();
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.cooked_beef;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.cooked_beef);
	}
}
