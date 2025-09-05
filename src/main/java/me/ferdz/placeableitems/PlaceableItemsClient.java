package me.ferdz.placeableitems;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import me.ferdz.placeableitems.rendering.PlaceableItemsGeometryLoader;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = PlaceableItems.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = PlaceableItems.MODID, value = Dist.CLIENT)
public class PlaceableItemsClient {
    public PlaceableItemsClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        PlaceableItems.LOGGER.info("HELLO FROM CLIENT SETUP");
        PlaceableItems.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

//        event.register(MyGeometryLoader.ID, MyGeometryLoader.INSTANCE);

//        PlaceableItemsBlockRegistry.BLOCKS.getEntries().forEach((block) -> {
//            // TODO: Check for non deprecated alternatives
////
////            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
//        });
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(PlaceableItemsGeometryLoader.ID, PlaceableItemsGeometryLoader.INSTANCE);
    }


}
