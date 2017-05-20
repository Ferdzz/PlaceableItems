package me.ferdz.placeableitems;

import org.apache.logging.log4j.Logger;

import me.ferdz.placeableitems.event.BlockBreakHandler;
import me.ferdz.placeableitems.event.EntityJoinHandler;
import me.ferdz.placeableitems.event.RightClickHandler;
import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.init.ModRecipes;
import me.ferdz.placeableitems.proxy.CommonProxy;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Main class of the mod, this loads blocks, items, recipes and events into memory as well as set some basic information such as name, version and modid
 * @author Ferdz
 */
@Mod(modid = PlaceableItems.MODID, name = PlaceableItems.NAME, version = PlaceableItems.VERSION, updateJSON = "https://raw.githubusercontent.com/Ferdzz/PlaceableItems/master/update.json")
public class PlaceableItems {
	
	@Mod.Instance("placeableitems")
	public static PlaceableItems instance;
	@SidedProxy(clientSide = "me.ferdz.placeableitems.proxy.ClientProxy", serverSide = "me.ferdz.placeableitems.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static BlockBreakHandler blockBreakHandler;	
	
	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "3.0.4.1";
	
	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		
		logger.info("Started loading " + MODID + " version " + VERSION);
		
		ModItems.init();
		ModBlocks.init();
		ModRecipes.init();
		
		MinecraftForge.EVENT_BUS.register(new RightClickHandler());
		MinecraftForge.EVENT_BUS.register(blockBreakHandler = new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new EntityJoinHandler());
		
		proxy.registerRenderers();

		logger.info("Loaded " + MODID + " version " + VERSION + " correctly");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerItemRenderers();
		proxy.registerTESR();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if(Loader.isModLoaded("vanillamagic")) {
			// unregister the blaze rod
			ModBlocks.blockMap.remove(Items.BLAZE_ROD);
		}
	}
}