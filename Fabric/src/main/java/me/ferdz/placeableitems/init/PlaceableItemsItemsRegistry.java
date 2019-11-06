package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsItemsRegistry {

    public static Item SADDLE_STAND;

    // @SubscribeEvent
    public static void onItemsRegistry() {
        // Keep this for debugging purposes to use an ItemBlock
        SADDLE_STAND = new BlockItem(PlaceableItemsBlockRegistry.SADDLE_STAND,
                new Item.Settings().group(ItemGroup.TRANSPORTATION));
        // .setRegistryName("saddle_stand_item");
        Registry.register(Registry.ITEM, new Identifier(PlaceableItems.MODID, "saddle_stand_item"), SADDLE_STAND);
    }

}
