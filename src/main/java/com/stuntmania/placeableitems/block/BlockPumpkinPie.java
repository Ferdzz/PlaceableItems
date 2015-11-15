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
import com.stuntmania.placeableitems.tileentity.TEPumpkinPie;

public class BlockPumpkinPie extends BlockPlaceableItems {
	
	public BlockPumpkinPie() {
		super(Material.sponge);
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.1875F, 0.875F);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/pumpkin_pie");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		((TEPumpkinPie)world.getTileEntity(x, y, z)).bite(8, 0.3F, player, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEPumpkinPie();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.pumpkin_pie;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.pumpkin_pie);
	}
}
