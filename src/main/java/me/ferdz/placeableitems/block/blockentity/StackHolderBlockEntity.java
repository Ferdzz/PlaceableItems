package me.ferdz.placeableitems.block.blockentity;

import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StackHolderBlockEntity extends BlockEntity implements BlockContainerSingleItem {

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderBlockEntity(BlockPos pos, BlockState state) {
        super(PlaceableItemsBlockEntityTypeRegistry.STACK_HOLDER.get(), pos, state);
    }

    // -------------------------
    // NBT SAVE / LOAD (1.20.1)
    // -------------------------

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        if (!itemStack.isEmpty()) {
            CompoundTag stackTag = new CompoundTag();
            itemStack.save(stackTag);
            tag.put("PlaceableItems-Stack", stackTag);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        if (tag.contains("PlaceableItems-Stack")) {
            this.itemStack = ItemStack.of(tag.getCompound("PlaceableItems-Stack"));
        } else {
            this.itemStack = ItemStack.EMPTY;
        }
    }

    // -------------------------
    // SYNC TO CLIENT
    // -------------------------

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
    }

    // -------------------------
    // ContainerSingleItem API
    // -------------------------

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        return itemStack.isEmpty() ? ItemStack.EMPTY : itemStack;
    }

    @Override
    public void setTheItem(ItemStack stack) {
        this.itemStack = stack;
        this.setChanged();
    }
}
