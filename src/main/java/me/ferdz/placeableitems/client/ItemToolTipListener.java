package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PlaceableItems.MODID, value = Dist.CLIENT)
public class ItemToolTipListener {

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        if (!Screen.hasShiftDown()) {
            return;
        }

        Item item = event.getItemStack().getItem();
        String itemId = BuiltInRegistries.ITEM.getKey(item).getPath();
//        if (!Config.PLACEABLE_ITEMS.isPlaceableEnabled(itemId)) {
//            return;
//        }

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
