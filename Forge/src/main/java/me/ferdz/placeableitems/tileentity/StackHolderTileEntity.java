package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class StackHolderTileEntity extends BlockEntity {

    @ObjectHolder(PlaceableItems.MODID + ":placeableitems_synced_stackholder")
    public static final BlockEntityType<StackHolderTileEntity> TYPE = null;

    private static final String ITEM_STACK_KEY = "PlaceableItems-Stack";

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderTileEntity() {
        super(TYPE);
    }

    public StackHolderTileEntity(BlockEntityType type) {
        super(type);
    }

    @Override
    public CompoundTag save(CompoundTag compoundIn) {
        CompoundTag compound = super.save(compoundIn);
        compound.put(ITEM_STACK_KEY, this.itemStack.serializeNBT());
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundTag compound) {
        super.load(state, compound);
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
