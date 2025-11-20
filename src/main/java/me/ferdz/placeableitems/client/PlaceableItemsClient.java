package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import me.ferdz.placeableitems.rendering.PlaceableItemsGeometryLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PlaceableItems.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsClient {
    public PlaceableItemsClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        // container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.APPLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.BOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.DIAMOND.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.DRAGON_BREATH.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.EMERALD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.FIREWORK_ROCKET.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.GLASS_BOTTLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.LINGERING_POTION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.POTION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.SPLASH_POTION.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.WHEAT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.WRITABLE_BOOK.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.NETHERITE_PICKAXE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.DIAMOND_PICKAXE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.GOLDEN_PICKAXE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.IRON_PICKAXE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.STONE_PICKAXE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.WOODEN_PICKAXE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.HORSE_ARMOR_STAND.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(PlaceableItemsBlockRegistry.SADDLE_STAND.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(
                "rotation",
                PlaceableItemsGeometryLoader.INSTANCE
        );
    }

    @SubscribeEvent
    public static void onRegisterColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(PlaceableItemsClient::getPotionColor,
                PlaceableItemsBlockRegistry.POTION.get(),
                PlaceableItemsBlockRegistry.LINGERING_POTION.get(),
                PlaceableItemsBlockRegistry.SPLASH_POTION.get()
        );
    }

    private static int getPotionColor(BlockState blockState, BlockAndTintGetter blockDisplayReader, BlockPos pos, int index) {
        if (pos != null && blockDisplayReader != null) {
            StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) blockDisplayReader.getBlockEntity(pos);
            if (blockEntity != null) {
                ItemStack itemStack = blockEntity.getTheItem();
                return PotionUtils.getColor(itemStack);
            }
        }
        return 0x385DC6; // The default water tint
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(PlaceableItemsItemsRegistry.HORSE_ARMOR_STAND.get());
            event.accept(PlaceableItemsItemsRegistry.SADDLE_STAND.get());
        }
    }
}
