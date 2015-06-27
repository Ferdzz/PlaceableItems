package com.stuntmania.placeableitems;

import com.stuntmania.placeableitems.handler.BlockBreakHandler;
import com.stuntmania.placeableitems.handler.BlockDropsHandler;
import com.stuntmania.placeableitems.handler.BucketPlaceHandler;
import com.stuntmania.placeableitems.handler.RightClickHandler;
import com.stuntmania.placeableitems.handler.UpdateNotifyHandler;
import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.init.ModItems;
import com.stuntmania.placeableitems.init.ModRecipes;
import com.stuntmania.placeableitems.proxy.CommonProxy;
import com.stuntmania.placeableitems.utils.UpdateChecker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION)
public class PlaceableItems {
	
	@SidedProxy(clientSide = "com.stuntmania.placeableitems.proxy.ClientProxy", serverSide = "com.stuntmania.placeableitems.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("placeableitems")
	public static PlaceableItems instance;
	
	public static UpdateChecker updateChecker;
	public static boolean warned = false;

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
		FMLCommonHandler.instance().bus().register(new UpdateNotifyHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.checkUpdate();
		System.out.println("Loaded " + MODID + " version " + VERSION + " correctly");
	}
}
