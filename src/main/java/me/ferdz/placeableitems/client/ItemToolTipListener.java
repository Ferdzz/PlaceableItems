package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.Config;
import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@Mod(value = PlaceableItems.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = PlaceableItems.MODID, value = Dist.CLIENT)
public class ItemToolTipListener {

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (!Screen.hasShiftDown()) {
            return;
        }

        Item item = event.getItemStack().getItem();
        String itemId = BuiltInRegistries.ITEM.getKey(item).getPath();
        if (!Config.PLACEABLE_ITEMS.isPlaceableEnabled(itemId)) {
            return;
        }

        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        // Add tooltip for placeable info
        event.getToolTip().add(Component.translatable("key.placeableitems.placeable")
                .withStyle(ChatFormatting.DARK_GRAY));

        // Add tooltips from all components
        for (IBlockComponent blockComponent : block.getComponents()) {
            Component description = blockComponent.getDescription(event.getItemStack());
            if (description != null) {
                event.getToolTip().add(
                        Component.literal(" ").append(description).withStyle(ChatFormatting.DARK_GRAY)
                );
            }
        }
    }
}
