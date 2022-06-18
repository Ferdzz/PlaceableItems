package me.ferdz.placeableitems.network;

import net.minecraft.network.FriendlyByteBuf;

/**
 * A client -> server packet to inform it of an item placement attempt
 *
 * @author Parker Hawke - Choco
 */
public class CNotifyItemPlaceKeyPacket {

    private boolean pressed;

    /**
     * Create a new CNotifySpecialItemPlaceKeyPacket with the new key pressed state.
     *
     * @param pressed whether or not it has been pressed
     */
    public CNotifyItemPlaceKeyPacket(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Create a new CNotifySpecialItemPlaceKeyPacket from a PacketBuffer instance.
     *
     * @param buffer the packet buffer
     */
    public CNotifyItemPlaceKeyPacket(FriendlyByteBuf buffer) {
        this(buffer.readBoolean());
    }

    /**
     * Check whether or not the key should be declared as pressed.
     *
     * @return the new pressed state
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Write this packet's data into the supplied packet buffer.
     *
     * @param buffer the buffer to which data should be written
     */
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBoolean(pressed);
    }
}
