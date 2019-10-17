package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class StackHolderTileEntity extends TileEntity {

    private static final String ITEM_STACK_KEY = "PlaceableItems-Stack";

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderTileEntity() {
        super(PlaceableItemsBlockRegistry.WRITABLE_BOOK_TILE_ENTITY);
    }

    @Override
    public CompoundNBT write(CompoundNBT compoundIn) {
        CompoundNBT compound = super.write(compoundIn);
        compound.put(ITEM_STACK_KEY, this.itemStack.serializeNBT());
        return compound;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.itemStack = ItemStack.read(compound.getCompound(ITEM_STACK_KEY));
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack.copy();
        this.itemStack.setCount(1);
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
