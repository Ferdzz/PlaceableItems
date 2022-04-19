package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class StackHolderBlockEntity extends BlockEntity {

    @ObjectHolder(PlaceableItems.MODID + ":stack_holder_entity")
    public static final BlockEntityType<StackHolderBlockEntity> TYPE = null;

    private static final String ITEM_STACK_KEY = "PlaceableItems-Stack";

    private ItemStack itemStack = ItemStack.EMPTY;

    public StackHolderBlockEntity(BlockPos pos, BlockState state) {
        super(TYPE, pos, state);
    }

    public StackHolderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public CompoundTag save(CompoundTag compoundIn) {
        CompoundTag compound = super.save(compoundIn);
        compound.put(ITEM_STACK_KEY, this.itemStack.serializeNBT());
        return compound;
    }

    @Override
    public void load(CompoundTag compound) {
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
