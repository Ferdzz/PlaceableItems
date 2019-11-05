package me.ferdz.placeableitems;

import java.util.logging.Logger;

import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

//@Mod(PlaceableItems.MODID)
public class PlaceableItems implements ModInitializer, ClientModInitializer {

    public static final String MODID = "placeableitems";
    
    @Override
    public void onInitialize() {
        PlaceableItemsBlockRegistry.onBlocksRegistry();
        PlaceableItemsItemsRegistry.onItemsRegistry();
        PlaceableItemBlockEntityRegistry.registerTE();
    }
    @Override
    public void onInitializeClient() 
    {
        PlaceableItemBlockEntityRegistry.registerTERenderer();
        //ServerPlayerEntity
    }
    

    // Directly reference a log4j logger.
    public static Logger logger = Logger.getLogger("placeablesItems");

    public PlaceableItems() {
    }


}
