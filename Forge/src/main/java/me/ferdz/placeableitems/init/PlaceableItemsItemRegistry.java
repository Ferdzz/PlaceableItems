package me.ferdz.placeableitems.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsItemRegistry {

    public static Item HORSE_ARMOR_STAND;
    public static Item SADDLE_STAND;
    /** Holds the list of blocks that need to get a BlockItem linked & registered. Cleared after registry */
    public static ArrayList<Block> blocksRegistry = new ArrayList<>();

    private PlaceableItemsItemRegistry() { }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // Keep this for debugging purposes to use an ItemBlock
        HORSE_ARMOR_STAND = new BlockItem(PlaceableItemsBlockRegistry.HORSE_ARMOR_STAND, new Item.Properties()
                .tab(CreativeModeTab.TAB_DECORATIONS))
                .setRegistryName("horse_armor_stand_item");
        event.getRegistry().register(HORSE_ARMOR_STAND);
        SADDLE_STAND = new BlockItem(PlaceableItemsBlockRegistry.SADDLE_STAND, new Item.Properties()
                .tab(CreativeModeTab.TAB_DECORATIONS))
                .setRegistryName("saddle_stand_item");
        event.getRegistry().register(SADDLE_STAND);

        for (Block block : blocksRegistry) {
            BlockItem blockItem = new BlockItem(block, new Item.Properties());
            blockItem.setRegistryName(block.getRegistryName());
            event.getRegistry().register(blockItem);
        }
    }
}
