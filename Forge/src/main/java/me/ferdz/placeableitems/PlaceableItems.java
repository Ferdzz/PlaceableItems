package me.ferdz.placeableitems;

import me.ferdz.placeableitems.client.ClientListener;
import me.ferdz.placeableitems.event.ItemPlaceHandler;
import me.ferdz.placeableitems.network.PlaceableItemsPacketHandler;
import me.ferdz.placeableitems.rendering.PlaceableItemsModelLoader;
import me.ferdz.placeableitems.wiki.WikiGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PlaceableItems.MODID)
public final class PlaceableItems {

    public static final String MODID = "placeableitems";
    private static final boolean GENERATE_WIKI = "true".equals(System.getenv().get("GENERATE_WIKI"));

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    private static PlaceableItems instance;

    private final ItemPlaceHandler placeHandler;

    public PlaceableItems() {
        instance = this;

        // Register ourselves for server and other game events we are interested in
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onModelRegistry);
        MinecraftForge.EVENT_BUS.register(this.placeHandler = new ItemPlaceHandler());

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientListener::get);

        if(GENERATE_WIKI) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::generateWiki);
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        PlaceableItemsPacketHandler.init();
    }

    @OnlyIn(Dist.CLIENT)
    private void onClientSetup(FMLClientSetupEvent event) {

    }

    @OnlyIn(Dist.CLIENT)
    private void onModelRegistry(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(MODID, "rotation"), new PlaceableItemsModelLoader());
    }

    private void generateWiki(final FMLLoadCompleteEvent e) {
        WikiGenerator generator = new WikiGenerator();
        generator.generate();
    }

    /**
     * Get an instance of the {@link ItemPlaceHandler}.
     *
     * @return the item place handler
     */
    public ItemPlaceHandler getPlaceHandler() {
        return placeHandler;
    }

    /**
     * Get the singleton instance of this mod.
     *
     * @return this mod's instance
     */
    public static PlaceableItems getInstance() {
        return instance;
    }

}
