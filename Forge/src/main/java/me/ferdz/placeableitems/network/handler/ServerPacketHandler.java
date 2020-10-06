package me.ferdz.placeableitems.network.handler;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Server-sided implementation of {@link AnonymousPacketHandler}.
 *
 * @author Parker Hawke - Choco
 */
public final class ServerPacketHandler implements AnonymousPacketHandler {

    private static final AnonymousPacketHandler INSTANCE = new ServerPacketHandler();

    private ServerPacketHandler() { }

    @Override
    public void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> PlaceableItems.getInstance().getPlaceHandler().setHoldingKey(packet.isPressed()));
        context.setPacketHandled(true);
    }

    public static AnonymousPacketHandler get() {
        return INSTANCE;
    }

}
