//package me.ferdz.placeableitems.client;
//
//import com.mojang.blaze3d.platform.InputConstants;
//import me.ferdz.placeableitems.PlaceableItems;
//import net.minecraft.client.KeyMapping;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import org.lwjgl.glfw.GLFW;
//
//@Mod(value = PlaceableItems.MODID)
//public class KeyBindings {
//    public static KeyMapping KEY_BINDING_PLACE_ITEM = new KeyMapping(
//            "key." + PlaceableItems.MODID + ".place_item",
//            // using InputConstants.Type.KEYSYM (keyboard key) and no modifier
//            InputConstants.Type.KEYSYM,
//            GLFW.GLFW_KEY_LEFT_SHIFT,
//            "key.categories." + PlaceableItems.MODID
//    );
//
//    @Mod.EventBusSubscriber(modid = "placeableitems", value = Dist.CLIENT)
//    public static class Registration {
//        @SubscribeEvent
//        public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
//            event.register(KEY_BINDING_PLACE_ITEM);
//        }
//    }
//}
