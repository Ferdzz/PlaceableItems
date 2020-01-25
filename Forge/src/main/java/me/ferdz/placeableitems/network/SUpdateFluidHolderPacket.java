package me.ferdz.placeableitems.network;

import java.util.function.Supplier;

import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * A server -> client packet to update the state of a {@link FluidHolderTileEntity}.
 *
 * @author Parker Hawke - Choco
 */
public class SUpdateFluidHolderPacket {

    private BlockPos tilePos;
    private FluidStack fluidStack;

    /**
     * Create a new SUpdateFluidHolderPacket with the tile's position and fluid stack.
     *
     * @param tilePos the tile's position
     * @param fluidStack the fluid stack
     */
    public SUpdateFluidHolderPacket(BlockPos tilePos, FluidStack fluidStack) {
        this.tilePos = tilePos;
        this.fluidStack = fluidStack;
    }

    /**
     * Create a new SUpdateFluidHolderPacket based on the values supplied by the tile entity.
     *
     * @param tile the tile entity whose values may be used to populate this packet data
     */
    public SUpdateFluidHolderPacket(FluidHolderTileEntity tile) {
        this(tile.getPos(), tile.getFluid());
    }

    /**
     * Create a new SUpdateFluidHolderPacket from a PacketBuffer instance.
     *
     * @param buffer the packet buffer
     */
    public SUpdateFluidHolderPacket(PacketBuffer buffer) {
        this.tilePos = buffer.readBlockPos();
        this.fluidStack = new FluidStack(ForgeRegistries.FLUIDS.getValue(new ResourceLocation(buffer.readString())), buffer.readInt());
    }

    /**
     * Get the tile's position.
     *
     * @return the tile position
     */
    public BlockPos getTilePos() {
        return tilePos;
    }

    /**
     * Get the fluid stack.
     *
     * @return the fluid stack
     */
    public FluidStack getFluidStack() {
        return fluidStack;
    }

    /**
     * Write this packet's data into the supplied packet buffer.
     *
     * @param buffer the buffer to which data should be written
     */
    public void encode(PacketBuffer buffer) {
        buffer.writeBlockPos(tilePos);
        buffer.writeString(fluidStack.getFluid().getRegistryName().toString());
        buffer.writeInt(fluidStack.getAmount());
    }

    /**
     * Handle the receiving of this packet.
     *
     * @param ctx the network context
     */
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        if (context.getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
            return;
        }

        context.enqueueWork(() -> {
            World world = Minecraft.getInstance().world;
            if (!world.getChunkProvider().isChunkLoaded(new ChunkPos(tilePos))) {
                return;
            }

            TileEntity tile = world.getTileEntity(tilePos);
            if (!(tile instanceof FluidHolderTileEntity)) {
                return;
            }

            ((FluidHolderTileEntity) tile).setFluid(fluidStack);
        });

        context.setPacketHandled(true);
    }

}
