package me.ferdz.placeableitems.network.handler;

import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Represents an anonymous packet handler. 
 *
 * @author Parker Hawke - Choco
 */
public interface AnonymousPacketHandler {

    /**
     * Handle the {@link CNotifyItemPlaceKeyPacket}.
     *
     * @param packet the packet to handle
     * @param ctx the network context
     */
    public void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx);

}
