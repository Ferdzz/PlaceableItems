package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

    /**
     * Generates a name from the item name and put the block into the map.
     * @param item the associated item
     * @param block the resulting block
     * @return the block that was just added
     */
    @Override
    public PlaceableItemsBlock put(Item item, PlaceableItemsBlock block) {
        String name = item.getName().getString().toLowerCase() + "_block";
        return this.put(item, block, name);
    }

    /**
     * Set the registry name of the block and registers it to the Block registry.
     * @param item the associated item
     * @param block the resulting block
     * @param name the registry name for this block
     * @return the block that was just added
     */
    public PlaceableItemsBlock put(Item item, PlaceableItemsBlock block, String name) {
        block.setRegistryName(PlaceableItems.MODID, name);
        GameRegistry.findRegistry(Block.class).register(block);
        return super.put(item, block);
    }
}
