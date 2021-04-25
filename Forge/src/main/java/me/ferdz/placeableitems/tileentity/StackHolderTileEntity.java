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
    public CompoundNBT save(CompoundNBT compoundIn) {
        CompoundNBT compound = super.save(compoundIn);
        compound.put(ITEM_STACK_KEY, this.itemStack.serializeNBT());
        return compound;
    }

    @Override
    public void load(CompoundNBT compound) {
        super.load(compound);
        this.itemStack = ItemStack.of(compound.getCompound(ITEM_STACK_KEY));
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack.copy();
        this.itemStack.setCount(1);
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
        }
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
