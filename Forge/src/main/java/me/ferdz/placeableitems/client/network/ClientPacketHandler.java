package me.ferdz.placeableitems.client.network;

import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.handler.AnonymousPacketHandler;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Client-sided implementation of {@link AnonymousPacketHandler}
 *
 * @author Parker Hawke - Choco
 */
public final class ClientPacketHandler implements AnonymousPacketHandler {

    private static final AnonymousPacketHandler INSTANCE = new ClientPacketHandler();

    private ClientPacketHandler() { }

    @Override
    public void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
    }

    public static AnonymousPacketHandler get() {
        return INSTANCE;
    }

}
