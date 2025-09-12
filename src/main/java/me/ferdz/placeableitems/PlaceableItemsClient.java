package me.ferdz.placeableitems;

import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.rendering.PlaceableItemsGeometryLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

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

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.APPLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.BOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.DIAMOND.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.EMERALD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.FIREWORK_ROCKET.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.GLASS_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.WHEAT.get(), RenderType.cutout());
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(PlaceableItemsGeometryLoader.ID, PlaceableItemsGeometryLoader.INSTANCE);
    }


}
