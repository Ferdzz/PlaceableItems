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

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockPumpkinPie extends BlockPlaceableItems {
	
	//TODO: Make smaller and/or remodel and/or retexture
	
	public BlockPumpkinPie() {
		super(Material.sponge);
		setBlockTextureName(PlaceableItems.MODID + ":destroy/pumpkin_pie");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		setBlockName("pumpkinPieBlock");
		GameRegistry.registerBlock(this, "pumpkinPieBlock");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TEPumpkinPie.bite(player, world, x, y, z);
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
