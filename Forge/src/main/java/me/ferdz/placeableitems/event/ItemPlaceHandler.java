package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemPlaceHandler {

    private boolean holdingKey = false;

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        if (!holdingKey) { // Abort if the user is not holding the keybind
            return;
        }

        ItemStack itemStack = e.getPlayer().getHeldItem(e.getHand());
        Item item = itemStack.getItem();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        // TODO: I got recommended to cache the BlockItems, as the creation may be expensive
        ActionResultType result = block.getBlockItem().tryPlace(
                new BlockItemUseContext(
                        new ItemUseContext(e.getPlayer(), e.getHand(),
                                new BlockRayTraceResult(e.getPlayer().getLookVec(), e.getFace(), e.getPos(), false))));
        if (result == ActionResultType.SUCCESS) {
            if (e.getPlayer().isCreative()) {
                itemStack.grow(1);
            }
            e.setCanceled(true);
            e.setCancellationResult(ActionResultType.SUCCESS);
        }
    }

    public void setHoldingKey(boolean isHoldingKey) {
        this.holdingKey = isHoldingKey;
    }

    public boolean isHoldingKey() {
        return holdingKey;
    }

}
