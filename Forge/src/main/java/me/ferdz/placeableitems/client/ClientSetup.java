package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.rendering.PlaceableItemsModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class ClientSetup {

    public static void setup() {
        ClientSetup clientSetup = new ClientSetup();
        // Register ourselves for server and other game events we are interested in
        FMLJavaModLoadingContext.get().getModEventBus().addListener(clientSetup::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(clientSetup::onModelRegistry);
    }

    private void onClientSetup(FMLClientSetupEvent event) {

    }

    private void onModelRegistry(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(PlaceableItems.MODID, "rotation"), new PlaceableItemsModelLoader());
    }

}
