package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class PlaceableItemsItemsRegistry {

    // Standard Forge 1.20.1 item registry
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PlaceableItems.MODID);

    public static final RegistryObject<Item> HORSE_ARMOR_STAND =
            ITEMS.register("horse_armor_stand",
                    () -> new BlockItem(
                            PlaceableItemsBlockRegistry.HORSE_ARMOR_STAND.get(),
                            new Item.Properties()
                    )
            );

    public static final RegistryObject<Item> SADDLE_STAND =
            ITEMS.register("saddle_stand",
                    () -> new BlockItem(
                            PlaceableItemsBlockRegistry.SADDLE_STAND.get(),
                            new Item.Properties()
                    )
            );
}
