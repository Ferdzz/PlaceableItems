package me.ferdz.placeableitems;

import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

public class PlaceableItems implements ModInitializer, ClientModInitializer {

    public static final String MODID = "placeableitems";

    // Directly reference a log4j logger.
    public static Logger logger = Logger.getLogger("placeableitems");

    public PlaceableItems() {
    }

    @Override
    public void onInitialize() {
        PlaceableItemsBlockRegistry.onBlocksRegistry();
        PlaceableItemsItemsRegistry.onItemsRegistry();
        PlaceableItemBlockEntityRegistry.registerTE();
    }

    @Override
    public void onInitializeClient() {
        PlaceableItemBlockEntityRegistry.registerTERenderer();
    }
}
