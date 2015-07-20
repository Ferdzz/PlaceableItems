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
import com.stuntmania.placeableitems.tileentity.TEMelon;

public class BlockMelon extends BlockPlaceableItems {
	
	public BlockMelon() {
		super(Material.sponge);
		this.setBlockBounds(0.2F, 0, 0.2F, 0.8F, 0.6F, 0.8F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/melon");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return ((TEMelon)world.getTileEntity(x, y, z)).bite(player, world, x, y, z);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEMelon();
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.melon;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.melon);
	}
	
}
