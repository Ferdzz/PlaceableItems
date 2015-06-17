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

import com.stuntmania.placeableitems.tileentity.TECarrot;

public class BlockCarrot extends BlockPlaceableItems {
	
	// TODO add golden carrot effect
	
	public BlockCarrot() {
		super(Material.sponge);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		((TECarrot)world.getTileEntity(x, y, z)).bite(player, world, x, y, z);
		return true;
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int par2) {
		switch (meta) {
		case 0:
			return Items.carrot;
		case 1:
			return Items.golden_carrot;
		default:
			return null;
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.carrot);
		case 1:
			return new ItemStack(Items.golden_carrot);
		default:
			return null;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TECarrot();
	}
}
