package me.ferdz.placeableitems;

import me.ferdz.placeableitems.client.PlaceableItemsClientHandler;
import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

import net.minecraft.util.Identifier;

import java.util.logging.Logger;

public class PlaceableItems implements ModInitializer, ClientModInitializer {

    public static final String MODID = "placeableitems";

    public static final Identifier PACKET_ID_NOTIFY_ITEM_PLACE_KEY = new Identifier(PlaceableItems.MODID, "notify_item_place_key");

    // Directly reference a log4j logger.
    public static Logger logger = Logger.getLogger("placeableitems");

    private static boolean keyPressed = false;

    public PlaceableItems() {
    }

    @Override
    public void onInitialize() {
        PlaceableItemsBlockRegistry.onBlocksRegistry();
        PlaceableItemsItemsRegistry.onItemsRegistry();
        PlaceableItemBlockEntityRegistry.registerTE();

        ServerSidePacketRegistry.INSTANCE.register(PACKET_ID_NOTIFY_ITEM_PLACE_KEY, (context, data) -> {
            boolean pressed = data.readBoolean();
            context.getTaskQueue().execute(() -> {
                PlaceableItems.setKeyPressed(pressed);
            });
        });
    }

    @Override
    public void onInitializeClient() {
        PlaceableItemsClientHandler.registerPackets();
        PlaceableItemsClientHandler.registerKeybindings();
        PlaceableItemBlockEntityRegistry.registerTERenderer();
    }

    public static void setKeyPressed(boolean keyPressed) {
        PlaceableItems.keyPressed = keyPressed;
    }

    public static boolean isKeyPressed() {
        return keyPressed;
    }

}
