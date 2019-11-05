package me.ferdz.placeableitems.blockentities;

import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class StackHolderBlockEntity extends BlockEntity {
    private static final String ITEM_STACK_KEY = "PlaceableItems-Stack";

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderBlockEntity() {
        super(PlaceableItemBlockEntityRegistry.WRITABLE_BOOK_TILE_ENTITY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.put(ITEM_STACK_KEY, this.itemStack.toTag(new CompoundTag()));
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.itemStack = ItemStack.fromTag(tag.getCompound(ITEM_STACK_KEY));
        super.fromTag(tag);
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack.copy();
        this.itemStack.setCount(1);
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
        }
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }
}
