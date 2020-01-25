package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.client.model.GlassBottleFluidModel;
import me.ferdz.placeableitems.client.renderer.tileentity.FluidHolderRenderer;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class ClientListener {

    private static ClientListener instance;

    private ClientListener() { }

    @SubscribeEvent // Fluid models should be binded here:
    void onClientSetup(@SuppressWarnings("unused") FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(FluidHolderTileEntity.class, new FluidHolderRenderer()
                .bind(PlaceableItemsBlockRegistry.GLASS_BOTTLE, new GlassBottleFluidModel())
        );
    }

    public static ClientListener get() {
        return (instance != null) ? instance : (instance = new ClientListener());
    }

}
