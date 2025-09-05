package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ItemPlaceHandler {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickBlock event) {
        // TODO: Check for keybind
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();
        // TODO: Map proper block
//        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        PlaceableItemsBlock block = PlaceableItemsBlockRegistry.COAL_BLOCK.get();

        if (player.level().isClientSide()) {
            return;
        }

        InteractionResult result = ((BlockItem) block.asItem()).place(
                new BlockPlaceContext(
                        new UseOnContext(player, event.getHand(),
                                new BlockHitResult(player.getLookAngle(), event.getFace(), event.getPos(), false))));
        if (result == InteractionResult.SUCCESS) {
            event.setCanceled(true);
        }
        event.setCancellationResult(result);
    }

}
