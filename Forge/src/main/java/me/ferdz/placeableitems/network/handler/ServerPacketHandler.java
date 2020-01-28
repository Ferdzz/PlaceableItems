package me.ferdz.placeableitems.network.handler;

import java.util.function.Supplier;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.network.CNotifySpecialItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;

import net.minecraft.util.LazyLoadBase;

import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Server-sided implementation of {@link AnonymousPacketHandler}.
 *
 * @author Parker Hawke - Choco
 */
public final class ServerPacketHandler implements AnonymousPacketHandler {

    private static final LazyLoadBase<AnonymousPacketHandler> INSTANCE  = new LazyLoadBase<>(() -> new ServerPacketHandler());

    private ServerPacketHandler() { }

    @Override
    public void handleUpdateFluidHolder(SUpdateFluidHolderPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
    }

    @Override
    public void handleNotifySpecialItemPlaceKey(CNotifySpecialItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> PlaceableItems.getInstance().getPlaceHandler().setHoldingKey(packet.isPressed()));
        context.setPacketHandled(true);
    }

    public static AnonymousPacketHandler get() {
        return INSTANCE.getValue();
    }

}
