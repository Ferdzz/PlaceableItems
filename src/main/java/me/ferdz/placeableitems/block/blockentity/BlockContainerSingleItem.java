package me.ferdz.placeableitems.block.blockentity;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.ticks.ContainerSingleItem;

public interface BlockContainerSingleItem extends ContainerSingleItem {
    BlockEntity getContainerBlockEntity();

    ItemStack getTheItem();

    default boolean stillValid(Player p_324363_) {
        return Container.stillValidBlockEntity(this.getContainerBlockEntity(), p_324363_);
    }

    default ItemStack removeTheItem() {
        return this.splitTheItem(this.getMaxStackSize());
    }

    default ItemStack removeItem(int slot, int amount) {
        return slot != 0 ? ItemStack.EMPTY : this.splitTheItem(amount);
    }

    default ItemStack splitTheItem(int amount) {
        return this.getTheItem().split(amount);
    }

    default void setItem(int slot, ItemStack stack) {
        if (slot == 0) {
            this.setTheItem(stack);
        }
    }

    void setTheItem(ItemStack var1);

    default ItemStack getItem(int slot) {
        return slot == 0 ? this.getTheItem() : ItemStack.EMPTY;
    }
}
