package me.ferdz.placeableitems.network.handler;

import java.util.function.Supplier;

import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;

import net.minecraft.util.LazyLoadBase;

import net.minecraftforge.fml.network.NetworkEvent;

public final class ServerPacketHandler implements AnonymousPacketHandler {

    private static final LazyLoadBase<AnonymousPacketHandler> INSTANCE  = new LazyLoadBase<>(() -> new ServerPacketHandler());

    private ServerPacketHandler() { }

    @Override
    public void handleUpdateFluidHolder(SUpdateFluidHolderPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
    }

    public static AnonymousPacketHandler get() {
        return INSTANCE.getValue();
    }

}
