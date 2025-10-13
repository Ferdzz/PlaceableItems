package me.ferdz.placeableitems;

import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final PlaceableItemConfigs PLACEABLE_ITEMS = new PlaceableItemConfigs(BUILDER);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static class PlaceableItemConfigs {

        private final Map<String, ModConfigSpec.BooleanValue> itemToggles = new HashMap<>();

        public PlaceableItemConfigs(ModConfigSpec.Builder builder) {
            builder.push("items_toggle");

            for (String itemId : PlaceableItemsBlockRegistry.ALL_PLACEABLE_ITEM_IDS) {

                // Handles the centralized enable/disable configuration for all music discs
                if (itemId.equals("music_disc")) {
                    String configKey = "enable_placeable_music_disc";

                    ModConfigSpec.BooleanValue configValue = builder
                            .define(configKey, true);
                    itemToggles.put(itemId, configValue);
                    continue;
                }

                String mcItemId = itemId;
                if (itemId.equals("gold_ingo")) {
                    mcItemId = "gold_ingot";
                } else if (itemId.equals("writable_boo")) {
                    mcItemId = "writable_book";
                }


                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(mcItemId)).get().value();

                String itemTranslationKey = item.getDescriptionId();

                String configKey = "enable_placeable_" + itemId.replace("_", "");

                ModConfigSpec.BooleanValue configValue = builder
                        .translation(itemTranslationKey)
                        .define(configKey, true);

                itemToggles.put(itemId, configValue);
            }

            builder.pop();
        }

        public boolean isPlaceableEnabled(String itemId) {
            ModConfigSpec.BooleanValue toggle = itemToggles.get(itemId);

            if (itemId.contains("music_disc")) {
                toggle = itemToggles.get("music_disc");
            }

            if (toggle == null) {
                if (itemId.equals("gold_ingot")) {
                    toggle = itemToggles.get("gold_ingo");
                }
                else if (itemId.equals("writable_book")) {
                    toggle = itemToggles.get("writable_boo");
                }
            }

            return toggle != null ? toggle.get() : true;
        }
    }
}