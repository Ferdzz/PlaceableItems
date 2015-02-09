package com.stuntmania.placeableitems;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

import com.stuntmania.placeableitems.handler.RightClickHandler;
import com.stuntmania.placeableitems.init.*;
import com.stuntmania.placeableitems.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION)
public class PlaceableItems {
	
	@SidedProxy(clientSide = "com.stuntmania.placeableitems.proxy.ClientProxy", serverSide = "com.stuntmania.placeableitems.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static PlaceableItems instance;

	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "1.1";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Started loading " + MODID + " version " + VERSION);

		ModBlocks.init();
		ModItems.init();
		//TODO Add crafting recipes for Armor Stand && Saddle Stand
		ModRecipes.init();
		
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new RightClickHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Loaded " + MODID + " version " + VERSION + " correctly");
	}	

	public static void spawnItem(World world, double x, double y, double z, Item itemSpawn) {
		if (!world.isRemote) {
			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(itemSpawn));
			world.spawnEntityInWorld(item);
		}
	}

	public static boolean placeBlockWithMetadata(int x, int y, int z, int face, Block block, int metadata, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
				world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
				world.setBlockMetadataWithNotify(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, metadata, 2);
				block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
				return true;
			}
		return false;
	}

	public static boolean placeBlockWithoutMetadata(int x, int y, int z, int face, Block block, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
				world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
				block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
				return true;
			}
		return false;
	}
}
