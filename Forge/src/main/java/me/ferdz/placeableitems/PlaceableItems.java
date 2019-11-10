package me.ferdz.placeableitems;

import me.ferdz.placeableitems.event.ItemPlaceHandler;
import me.ferdz.placeableitems.wiki.WikiGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PlaceableItems.MODID)
public class PlaceableItems {

    public static final String MODID = "placeableitems";
    private static final boolean GENERATE_WIKI = System.getenv().get("GENERATE_WIKI").equals("true");

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PlaceableItems() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new ItemPlaceHandler());

        if(GENERATE_WIKI) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::generateWiki);
        }
    }

    private void generateWiki(final FMLLoadCompleteEvent e) {
        WikiGenerator generator = new WikiGenerator();
        generator.generate();
    }
}
