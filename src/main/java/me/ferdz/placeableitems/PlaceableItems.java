package me.ferdz.placeableitems;

import com.mojang.logging.LogUtils;
import me.ferdz.placeableitems.datagen.PlaceableItemsBlockStateProvider;
import me.ferdz.placeableitems.datagen.PlaceableItemsRecipeProvider;
import me.ferdz.placeableitems.event.ItemPlaceHandler;
import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PlaceableItems.MODID)
public class PlaceableItems {
    public static final String MODID = "placeableitems";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PlaceableItems() {

        // Forge's MOD event bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register lifecycle listeners
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

        // Register registries
        PlaceableItemsBlockRegistry.BLOCKS.register(modEventBus);
        PlaceableItemsItemsRegistry.ITEMS.register(modEventBus);
        PlaceableItemsBlockEntityTypeRegistry.BLOCK_ENTITY_TYPES.register(modEventBus);

        // Register normal (non-lifecycle) game events
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ItemPlaceHandler.class);

        // Register config
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Common init code
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Server-only logic
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();

        // Blockstates / models
        if (event.includeClient()) {
            generator.addProvider(true,
                    new PlaceableItemsBlockStateProvider(output, MODID, existingFileHelper));
        }

        // Recipes
        if (event.includeServer()) {
            generator.addProvider(true,
                    new PlaceableItemsRecipeProvider(output));
        }
    }
}
