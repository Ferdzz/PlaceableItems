package me.ferdz.placeableitems.client;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.client.model.GlassBottleFluidModel;
import me.ferdz.placeableitems.client.renderer.tileentity.FluidHolderRenderer;
import me.ferdz.placeableitems.event.ItemPlaceHandler;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.network.CNotifySpecialItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.PlaceableItemsPacketHandler;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.LazyLoadBase;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.lwjgl.glfw.GLFW;

public final class ClientListener {

    private static final LazyLoadBase<ClientListener> INSTANCE = new LazyLoadBase<>(ClientListener::new);

    private static final KeyBinding KEY_BINDING_PLACE_ITEM = new KeyBinding("key." + PlaceableItems.MODID + ".place_item", GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories." + PlaceableItems.MODID);

    private boolean registeredListeners = false;

    private ClientListener() { }

    void onClientSetup(@SuppressWarnings("unused") FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(FluidHolderTileEntity.class, new FluidHolderRenderer()
                .bind(PlaceableItemsBlockRegistry.GLASS_BOTTLE, new GlassBottleFluidModel())
        );

        ClientRegistry.registerKeyBinding(KEY_BINDING_PLACE_ITEM);
    }

    void onKeyPress(InputEvent.KeyInputEvent event) {
        int action = event.getAction();
        if (action == GLFW.GLFW_REPEAT || event.getKey() != KEY_BINDING_PLACE_ITEM.getKey().getKeyCode()) {
            return;
        }

        ItemPlaceHandler placeHandler = PlaceableItems.getInstance().getPlaceHandler();
        boolean pressed = (action == GLFW.GLFW_PRESS);

        if (placeHandler.isHoldingKey() == pressed) { // Don't send a packet if it hasn't changed states
            return;
        }

        PlaceableItemsPacketHandler.INSTANCE.sendToServer(new CNotifySpecialItemPlaceKeyPacket(pressed));
        placeHandler.setHoldingKey(pressed);
    }

    public static ClientListener get() {
        ClientListener instance = INSTANCE.getValue();

        // Register client-sided listeners here
        if (!instance.registeredListeners) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(instance::onClientSetup);
            MinecraftForge.EVENT_BUS.addListener(instance::onKeyPress);

            instance.registeredListeners = true;
        }

        return instance;
    }

}
