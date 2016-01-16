package com.stuntmania.placeableitems.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldUtils {

	public static void spawnItem(World world, double x, double y, double z, Item itemSpawn) {
		WorldUtils.spawnItem(world, x, y, z, new ItemStack(itemSpawn));
	}

	public static void spawnItem(World world, double x, double y, double z, ItemStack itemSpawn){		
		if (!world.isRemote) {
			EntityItem item = new EntityItem(world, x, y, z, itemSpawn);
			world.spawnEntityInWorld(item);
		}
	}

	public static boolean placeBlockWithMetadata(int x, int y, int z, int face, Block block, int metadata, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (world.canPlaceEntityOnSide(block, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, false, face, (Entity)null, player.getCurrentEquippedItem())) {
				if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
					world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
					world.setBlockMetadataWithNotify(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, metadata, 2);
					block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
					return true;
				}
			}
		return false;
	}

	public static boolean placeBlockWithoutMetadata(int x, int y, int z, int face, Block block, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (world.canPlaceEntityOnSide(block, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, false, face, (Entity)null, player.getCurrentEquippedItem())) {
				if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
					world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
					block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
					return true;
				}
			}
		return false;
	}
	
}
