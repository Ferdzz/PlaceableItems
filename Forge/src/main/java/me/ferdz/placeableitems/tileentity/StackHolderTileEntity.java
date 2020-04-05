package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class StackHolderTileEntity extends TileEntity {

    @ObjectHolder(PlaceableItems.MODID + ":writable_book_block")
    public static final TileEntityType<StackHolderTileEntity> TYPE = null;

    private static final String ITEM_STACK_KEY = "PlaceableItems-Stack";

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderTileEntity() {
        super(TYPE);
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
