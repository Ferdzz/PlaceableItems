package me.ferdz.placeableitems.client.network;

import java.util.function.Supplier;

import me.ferdz.placeableitems.network.CNotifyItemPlaceKeyPacket;
import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;
import me.ferdz.placeableitems.network.handler.AnonymousPacketHandler;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Client-sided implementation of {@link AnonymousPacketHandler}
 *
 * @author Parker Hawke - Choco
 */
public final class ClientPacketHandler implements AnonymousPacketHandler {

    private static final AnonymousPacketHandler INSTANCE = new ClientPacketHandler();

    private ClientPacketHandler() { }

    @Override
    public void handleUpdateFluidHolder(SUpdateFluidHolderPacket packet, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        context.enqueueWork(() -> {
            World world = Minecraft.getInstance().world;
            if (!world.getChunkProvider().isChunkLoaded(new ChunkPos(packet.getTilePos()))) {
                return;
            }

            TileEntity tile = world.getTileEntity(packet.getTilePos());
            if (!(tile instanceof FluidHolderTileEntity)) {
                return;
            }

            ((FluidHolderTileEntity) tile).setFluid(packet.getFluidStack());
        });

        context.setPacketHandled(true);
    }

    @Override
    public void handleNotifyItemPlaceKey(CNotifyItemPlaceKeyPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().setPacketHandled(true);
    }

    public static AnonymousPacketHandler get() {
        return INSTANCE;
    }

}
