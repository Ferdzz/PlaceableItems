package me.ferdz.placeableitems.block.blockentity;

import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class StackHolderBlockEntity extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem {

    private ItemStack itemStack;

    public StackHolderBlockEntity(BlockPos pos, BlockState blockState) {
        super(PlaceableItemsBlockEntityTypeRegistry.STACK_HOLDER.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (itemStack != null && !itemStack.isEmpty()) {
            tag.put("PlaceableItems-Stack", itemStack.save(registries, new CompoundTag()));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ItemStack.parse(registries, tag.getCompound("PlaceableItems-Stack")).ifPresent(
                (itemStack) -> this.itemStack = itemStack
        );
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        // Tag sent to client when block is loaded or chunk is sent
        return this.saveWithoutMetadata(registries);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        this.loadAdditional(tag, lookupProvider);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    @Override
    public ItemStack getTheItem() {
        if (itemStack == null) {
            return ItemStack.EMPTY;
        }
        return itemStack;
    }

    @Override
    public void setTheItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.setChanged();
    }
}
