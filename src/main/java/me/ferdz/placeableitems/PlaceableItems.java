package me.ferdz.placeableitems;

import org.apache.logging.log4j.Logger;

import me.ferdz.placeableitems.ai.ChickenAIAttractBlock;
import me.ferdz.placeableitems.event.BlockBreakHandler;
import me.ferdz.placeableitems.event.EntityJoinHandler;
import me.ferdz.placeableitems.event.RightClickHandler;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.init.ModRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION)
public class PlaceableItems {
	
	@Mod.Instance("placeableitems")
	public static PlaceableItems instance;
//	@SidedProxy(clientSide="me.ferdz.placeableitems.proxy.ClientProxy", serverSide="me.ferdz.placeableitems.proxy.CommonProxy")
//	public static CommonProxy proxy;
	
	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "3.0";
	
	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Started loading " + MODID + " version " + VERSION);
		
		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();
		
		MinecraftForge.EVENT_BUS.register(new RightClickHandler());
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new EntityJoinHandler());

//		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Loaded " + MODID + " version " + VERSION + " correctly");
	}
}