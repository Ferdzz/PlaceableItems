package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.network.PlaceKeyPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(modid = "placeableitems", value = Dist.CLIENT)
public class ClientKeyHandler {

    private static boolean wasHolding = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        // Avoid null pointers before game is ready
        if (mc.player == null || mc.getConnection() == null) return;

        boolean nowHolding = KeyBindings.KEY_BINDING_PLACE_ITEM.isDown();

        // Only send packet if state changed
        if (nowHolding != wasHolding) {
            wasHolding = nowHolding;
            // Send packet to server
            mc.getConnection().send(new PlaceKeyPayload(nowHolding));
        }
    }
}