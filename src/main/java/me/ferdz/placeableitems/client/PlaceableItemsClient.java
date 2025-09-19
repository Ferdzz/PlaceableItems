package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import me.ferdz.placeableitems.rendering.PlaceableItemsGeometryLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.List;

@Mod(value = PlaceableItems.MODID, dist = Dist.CLIENT)
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

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(PlaceableItemsGeometryLoader.ID, PlaceableItemsGeometryLoader.INSTANCE);
    }

    @SubscribeEvent
    private static void onRegisterColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(PlaceableItemsClient::getPotionColor,
                PlaceableItemsBlockRegistry.POTION.get(),
                PlaceableItemsBlockRegistry.LINGERING_POTION.get(),
                PlaceableItemsBlockRegistry.SPLASH_POTION.get()
        );
    }


    private static int getPotionColor(BlockState blockState, BlockAndTintGetter blockDisplayReader, BlockPos pos, int index) {
        BlockEntity be = blockDisplayReader.getBlockEntity(pos);
        if (be instanceof StackHolderBlockEntity stackHolder) {
            ItemStack stack = stackHolder.getTheItem();

            PotionContents contents = stack.get(DataComponents.POTION_CONTENTS);
            if (contents != null) {
                // If a custom color/tint is defined
                if (contents.customColor().isPresent()) {
                    return contents.customColor().get();
                }

                // Otherwise fallback to effect color(s)
                if (contents.potion().isPresent()) {
                    // The potion object stores a list of MobEffectInstance
                    List<MobEffectInstance> effects = contents.potion().get().value().getEffects();
                    if (!effects.isEmpty()) {
                        // Use the first effect's MobEffect color, or combine multiple
                        MobEffectInstance inst = effects.get(0);
                        MobEffect mobEffect = inst.getEffect().value();  // get MobEffect from holder
                        return mobEffect.getColor();
                    }
                }
            }
        }

        // Default potion tint, water
        return 0x385DC6;
    }

    @SubscribeEvent
    public static void onBuildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(PlaceableItemsItemsRegistry.HORSE_ARMOR_STAND.get());
            event.accept(PlaceableItemsItemsRegistry.SADDLE_STAND.get());
        }
    }
}
