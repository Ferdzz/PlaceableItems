package me.ferdz.placeableitems.network;

import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.fluids.FluidStack;
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

}
