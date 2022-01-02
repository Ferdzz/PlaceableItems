package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.rendering.PlaceableItemsModelLoader;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
        // Setup color registering for all potions
        event.getBlockColors().register(ClientSetup::getPotionColor, PlaceableItemsBlockRegistry.POTION);
        event.getBlockColors().register(ClientSetup::getPotionColor, PlaceableItemsBlockRegistry.LINGERING_POTION);
    }

    private static int getPotionColor(BlockState blockState, IBlockDisplayReader blockDisplayReader, BlockPos pos, int index) {
        if (pos != null && blockDisplayReader != null) {
            StackHolderTileEntity tileEntity = (StackHolderTileEntity) blockDisplayReader.getBlockEntity(pos);
            if (tileEntity != null) {
                ItemStack itemStack = tileEntity.getItemStack();
                return PotionUtils.getColor(itemStack);
            }
        }
        return 0x385DC6; // The default water tint
    }
}
