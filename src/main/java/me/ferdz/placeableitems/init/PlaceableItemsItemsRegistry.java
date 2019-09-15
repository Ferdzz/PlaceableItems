package me.ferdz.placeableitems.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsItemsRegistry {

    public static Item SADDLE_STAND;

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // Keep this for debugging purposes to use an ItemBlock
        SADDLE_STAND = new BlockItem(PlaceableItemsBlockRegistry.SADDLE_STAND, new Item.Properties()
                .group(ItemGroup.TRANSPORTATION))
                .setRegistryName("saddle_stand_item");
        event.getRegistry().register(SADDLE_STAND);
    }
}
