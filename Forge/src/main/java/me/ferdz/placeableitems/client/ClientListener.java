package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.event.ItemPlaceHandler;
import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.PlaceableItemsPacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;

import java.util.UUID;

public final class ClientListener {

    private static final ClientListener INSTANCE = new ClientListener();

    private static final KeyBinding KEY_BINDING_PLACE_ITEM = new KeyBinding("key." + PlaceableItems.MODID + ".place_item", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories." + PlaceableItems.MODID);

    private boolean registeredListeners = false;

    private ClientListener() {
    }

    void onClientSetup(@SuppressWarnings("unused") FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(KEY_BINDING_PLACE_ITEM);
    }

    void onKeyPress(InputEvent.KeyInputEvent event) {
        int action = event.getAction();
        if (action == GLFW.GLFW_REPEAT || event.getKey() != KEY_BINDING_PLACE_ITEM.getKey().getValue()) {
            return;
        }

        // Do not handle key press if we're not connected to a server
        if (Minecraft.getInstance().getConnection() == null || Minecraft.getInstance().player == null) {
            return;
        }

        ItemPlaceHandler placeHandler = PlaceableItems.getInstance().getPlaceHandler();
        boolean pressed = (action == GLFW.GLFW_PRESS);

        UUID playerId =  Minecraft.getInstance().player.getUUID();
        if (placeHandler.isHoldingKey(playerId) == pressed) { // Don't send a packet if it hasn't changed states
            return;
        }

        PlaceableItemsPacketHandler.INSTANCE.sendToServer(new CNotifyItemPlaceKeyPacket(pressed));
        placeHandler.setHoldingKey(playerId, pressed);
    }

    public static ClientListener get() {
        ClientListener instance = INSTANCE;

        // Register client-sided listeners here
        if (!instance.registeredListeners) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(instance::onClientSetup);
            MinecraftForge.EVENT_BUS.addListener(instance::onKeyPress);

            instance.registeredListeners = true;
        }

        return instance;
    }

}
