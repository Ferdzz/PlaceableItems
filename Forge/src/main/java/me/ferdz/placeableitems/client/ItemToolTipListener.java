package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.item.Item;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PlaceableItems.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ItemToolTipListener {

    @SubscribeEvent
    public static void tooltip(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        event.getToolTip().add(new TranslationTextComponent("key.placeableitems.placeable")
                .withStyle(TextFormatting.DARK_GRAY));
        // Aggregate the description of all components
        for (IBlockComponent blockComponent : block.getComponents()) {
            IFormattableTextComponent textComponent = blockComponent.getDescription(event.getItemStack());
            if (textComponent != null) {
                event.getToolTip().add(new StringTextComponent(" ")
                        .append(textComponent)
                        .withStyle(TextFormatting.DARK_GRAY));
            }
        }
    }
}
