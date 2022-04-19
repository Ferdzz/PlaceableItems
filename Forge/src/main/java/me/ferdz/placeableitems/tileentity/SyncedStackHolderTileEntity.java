package me.ferdz.placeableitems.tileentity;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A StackHolderTileEntity that updates data to the client on change. Only use this TE if absolutely necessary
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SyncedStackHolderTileEntity extends StackHolderTileEntity {

    @ObjectHolder(PlaceableItems.MODID + ":placeableitems_synced_stackholder")
    public static final BlockEntityType<StackHolderTileEntity> TYPE = null;

    public SyncedStackHolderTileEntity() {
        super(TYPE);
    }

    @Override
    public CompoundTag getUpdateTag() {
        // Prepare a tag with all of this TE's data for client sync
        CompoundTag tag = super.getUpdateTag();
        save(tag);
        return tag;
    }
}
