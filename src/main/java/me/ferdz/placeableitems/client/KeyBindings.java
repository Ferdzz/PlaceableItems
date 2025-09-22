package me.ferdz.placeableitems.client;

import com.mojang.blaze3d.platform.InputConstants;
import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@Mod(value = PlaceableItems.MODID, dist = Dist.CLIENT)
public class KeyBindings {
    public static KeyMapping KEY_BINDING_PLACE_ITEM = new KeyMapping(
            "key." + PlaceableItems.MODID + ".place_item",
            // using InputConstants.Type.KEYSYM (keyboard key) and no modifier
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            "key.categories." + PlaceableItems.MODID
    );

    @EventBusSubscriber(modid = "placeableitems", value = Dist.CLIENT)
    public static class Registration {
        @SubscribeEvent
        public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(KEY_BINDING_PLACE_ITEM);
        }
    }
}
