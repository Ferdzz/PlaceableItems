package me.ferdz.placeableitems.network;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.util.UUID;
import java.util.function.Supplier;

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
        // Client packets
        INSTANCE.registerMessage(id++, CNotifyItemPlaceKeyPacket.class, CNotifyItemPlaceKeyPacket::encode, CNotifyItemPlaceKeyPacket::new, PlaceableItemsPacketHandler::handleNotifyItemPlaceKey);
    }

    private static void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        if (context.getSender() != null) {
            UUID playerId = context.getSender().getUUID();
            context.enqueueWork(() -> PlaceableItems.getInstance().getPlaceHandler().setHoldingKey(playerId, packet.isPressed()));
        }
        context.setPacketHandled(true);
    }
}
