package com.stuntmania.placeableitems;

import net.minecraftforge.common.MinecraftForge;

import com.stuntmania.placeableitems.handler.BlockBreakHandler;
import com.stuntmania.placeableitems.handler.BlockDropsHandler;
import com.stuntmania.placeableitems.handler.BucketPlaceHandler;
import com.stuntmania.placeableitems.handler.RightClickHandler;
import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.init.ModItems;
import com.stuntmania.placeableitems.init.ModRecipes;
import com.stuntmania.placeableitems.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION)
public class PlaceableItems {
	
	@SidedProxy(clientSide = "com.stuntmania.placeableitems.proxy.ClientProxy", serverSide = "com.stuntmania.placeableitems.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("placeableitems")
	public static PlaceableItems instance;

	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "1.4.3";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Started loading " + MODID + " version " + VERSION);

		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();
		
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new BucketPlaceHandler());
		MinecraftForge.EVENT_BUS.register(new RightClickHandler());
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new BlockDropsHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Loaded " + MODID + " version " + VERSION + " correctly");
	}
}
