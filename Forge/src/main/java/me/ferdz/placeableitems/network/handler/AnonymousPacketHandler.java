package me.ferdz.placeableitems.network.handler;

import java.util.function.Supplier;

import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;

import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Represents an anonymous packet handler. 
 *
 * @author Parker Hawke - Choco
 */
public interface AnonymousPacketHandler {

    /**
     * Handle the {@link SUpdateFluidHolderPacket}.
     *
     * @param packet the packet to handle
     * @param ctx the network context
     */
    public void handleUpdateFluidHolder(SUpdateFluidHolderPacket packet, Supplier<NetworkEvent.Context> ctx);

    /**
     * Handle the {@link CNotifyItemPlaceKeyPacket}.
     *
     * @param packet the packet to handle
     * @param ctx the network context
     */
    public void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx);

}
