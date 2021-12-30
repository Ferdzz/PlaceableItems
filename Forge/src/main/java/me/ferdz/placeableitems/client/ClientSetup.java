package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.impl.PotionBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.rendering.PlaceableItemsModelLoader;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ColorHandlerEvent;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(clientSetup::onColorSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {

    }

    private void onModelRegistry(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(PlaceableItems.MODID, "rotation"), new PlaceableItemsModelLoader());
    }

    private void onColorSetup(ColorHandlerEvent.Block event) {
        event.getBlockColors().register((blockState, blockDisplayReader, pos, index) -> {
            // Setup color registering for all potions
            if (pos != null && blockDisplayReader != null) {
                StackHolderTileEntity tileEntity = (StackHolderTileEntity) blockDisplayReader.getBlockEntity(pos);
                if (tileEntity != null) {
                    ItemStack itemStack = tileEntity.getItemStack();
                    System.out.println(itemStack);
                    return PotionUtils.getColor(itemStack);
                }
            }
            return 0x385DC6; // The default water tint
        }, PlaceableItemsBlockRegistry.POTION);
    }
}
