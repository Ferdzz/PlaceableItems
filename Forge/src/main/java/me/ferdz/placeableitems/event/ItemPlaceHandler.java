package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashSet;
import java.util.UUID;

public class ItemPlaceHandler {

    /**
     *  Array of players currently holding the placement hotkey
      */
    private HashSet<UUID> holdingKey = new HashSet<>();

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        // Abort if the user is not holding the keybind
        if (!holdingKey.contains(e.getPlayer().getUUID()) || !e.getPlayer().abilities.mayBuild) {
            return;
        }

        // TODO: Check if a `canPlayerEdit` check is needed for grief prevention

        ItemStack itemStack = e.getPlayer().getItemInHand(e.getHand());
        Item item = itemStack.getItem();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        ActionResultType result = block.getBlockItem().place(
                new BlockItemUseContext(
                        new ItemUseContext(e.getPlayer(), e.getHand(),
                                new BlockRayTraceResult(e.getPlayer().getLookAngle(), e.getFace(), e.getPos(), false))));
        if (result == ActionResultType.SUCCESS) {
            e.setCanceled(true);
        }
        e.setCancellationResult(result);
    }

    public void setHoldingKey(UUID playerId, boolean isHoldingKey) {
        if (isHoldingKey) {
            this.holdingKey.add(playerId);
        } else {
            this.holdingKey.remove(playerId);
        }
    }

    public boolean isHoldingKey(UUID playerId) {
        return this.holdingKey.contains(playerId);
    }
}
