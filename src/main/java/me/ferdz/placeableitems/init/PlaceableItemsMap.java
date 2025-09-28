package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;

public class PlaceableItemsMap extends LinkedHashMap<Item, PlaceableItemsBlock> {

    private static final Object lock = new Object();
    private static PlaceableItemsMap instance;

    /**
     * Private constructor to ensure singleton pattern
     */
    private PlaceableItemsMap() {
        super();
    }

    /**
     * Gets the singleton instance with a lock to ensure thread safety.
     * @return the only available instance of the map
     */
    public static PlaceableItemsMap instance() {
        synchronized (lock) {
            if(instance == null) instance = new PlaceableItemsMap();
            return instance;
        }
    }
}
