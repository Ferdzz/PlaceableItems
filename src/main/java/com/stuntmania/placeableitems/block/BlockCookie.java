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
import com.stuntmania.placeableitems.tileentity.TECookie;

public class BlockCookie extends BlockPlaceableItems {
	
	public BlockCookie() {
		super(Material.sponge);
		this.setBlockBounds(0.3125F, 0, 0.3125F, 0.6875F, 0.0625F, 0.6875F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/cookie");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TECookie();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ((TECookie)world.getTileEntity(x, y, z)).bite(2, 0.4F, player, world, x, y, z);
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.cookie;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.cookie);
	}
}
