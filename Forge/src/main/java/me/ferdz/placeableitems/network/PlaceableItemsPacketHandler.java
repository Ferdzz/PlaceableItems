package me.ferdz.placeableitems.network;

import me.ferdz.placeableitems.PlaceableItems;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * A packet handler for the PlaceableItems mod.
 *
 * @author Parker Hawke - Choco
 */
public final class PlaceableItemsPacketHandler {

    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(PlaceableItems.MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    private static int id = 0;

    private PlaceableItemsPacketHandler() { }

    /**
     * Initialize the packet handler.
     */
    public static void init() {
        INSTANCE.registerMessage(id++, SUpdateFluidHolderPacket.class, SUpdateFluidHolderPacket::encode, SUpdateFluidHolderPacket::new, SUpdateFluidHolderPacket::handle);
    }

}
