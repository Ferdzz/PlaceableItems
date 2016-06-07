package me.ferdz.placeableitems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION)
public class PlaceableItems {
	
	@Mod.Instance("placeableitems")
	public static PlaceableItems instance;
	
	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "3.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}