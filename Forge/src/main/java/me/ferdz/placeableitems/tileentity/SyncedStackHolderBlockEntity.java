package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A StackHolderTileEntity that updates data to the client on change. Only use this TE if absolutely necessary
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SyncedStackHolderBlockEntity extends StackHolderBlockEntity {

    @ObjectHolder(PlaceableItems.MODID + ":synced_stack_holder_entity")
    public static final BlockEntityType<StackHolderBlockEntity> TYPE = null;

    public SyncedStackHolderBlockEntity(BlockPos pos, BlockState state) {
        super(TYPE, pos, state);
    }
    @Override
    public CompoundTag getUpdateTag() {
        // Prepare a tag with all of this TE's data for client sync
        CompoundTag tag = super.getUpdateTag();
        save(tag);
        return tag;
    }
}
