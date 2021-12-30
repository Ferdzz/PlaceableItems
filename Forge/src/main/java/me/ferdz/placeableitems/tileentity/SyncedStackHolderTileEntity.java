package me.ferdz.placeableitems.tileentity;

import mcp.MethodsReturnNonnullByDefault;
import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A StackHolderTileEntity that updates data to the client on change. Only use this TE if absolutely necessary
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SyncedStackHolderTileEntity extends StackHolderTileEntity {

    @ObjectHolder(PlaceableItems.MODID + ":placeableitems_synced_stackholder")
    public static final TileEntityType<StackHolderTileEntity> TYPE = null;

    public SyncedStackHolderTileEntity() {
        super(TYPE);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        // Prepare a tag with all of this TE's data for client sync
        CompoundNBT tag = super.getUpdateTag();
        save(tag);
        return tag;
    }
}
