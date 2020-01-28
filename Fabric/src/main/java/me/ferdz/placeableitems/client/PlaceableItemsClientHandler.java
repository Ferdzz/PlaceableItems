package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import org.lwjgl.glfw.GLFW;

import io.netty.buffer.Unpooled;

public class PlaceableItemsClientHandler {

    private static final FabricKeyBinding KEY_BINDING_PLACE_ITEM = FabricKeyBinding.Builder.create(
            new Identifier(PlaceableItems.MODID, "place_item"),
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            "key.categories." + PlaceableItems.MODID
    ).build();

    public static void registerPackets() {
        ClientSidePacketRegistry.INSTANCE.register(PlaceableItems.PACKET_ID_NOTIFY_ITEM_PLACE_KEY, (context, data) -> { /* server bound packet. this can be ignored */ });
    }

    public static void registerKeybindings() {
        KeyBindingRegistry.INSTANCE.register(KEY_BINDING_PLACE_ITEM);

        ClientTickCallback.EVENT.register(e -> {
            // For whatever reason, FabricKeyBinding.isPressed() does NOT work for conflicting keybinds, despite having the same code...
            // So we fall back to polling directly from GLFW's getKey() function.
            boolean pressed = GLFW.glfwGetKey(MinecraftClient.getInstance().window.getHandle(), KEY_BINDING_PLACE_ITEM.getBoundKey().getKeyCode()) == 1;

            if (PlaceableItems.isKeyPressed() != pressed) {
                PlaceableItems.setKeyPressed(pressed);

                PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());
                buffer.writeBoolean(pressed);
                ClientSidePacketRegistry.INSTANCE.sendToServer(PlaceableItems.PACKET_ID_NOTIFY_ITEM_PLACE_KEY, buffer);
            }
        });
    }

}
