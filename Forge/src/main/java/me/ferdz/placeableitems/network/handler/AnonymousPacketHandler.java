package me.ferdz.placeableitems.network.handler;

import java.util.function.Supplier;

import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;

import net.minecraftforge.fml.network.NetworkEvent;

public interface AnonymousPacketHandler {

    public void handleUpdateFluidHolder(SUpdateFluidHolderPacket packet, Supplier<NetworkEvent.Context> ctx);

}
