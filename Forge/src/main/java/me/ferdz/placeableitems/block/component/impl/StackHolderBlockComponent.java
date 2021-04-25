package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * A BlockComponent that holds the ItemStack that was placed, then handles drops.
 * Used to make sure that enchanted items do not lose their metadatas upon breaking
 */
public class StackHolderBlockComponent extends AbstractBlockComponent {

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        this.setItemStack(worldIn, pos, stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileEntity = builder.getOptionalParameter(LootParameters.BLOCK_ENTITY);
        return Collections.singletonList(this.getItemStack(tileEntity));
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass(BlockState state) {
        return StackHolderTileEntity.class;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StackHolderTileEntity();
    }

    public void setItemStack(World worldIn, BlockPos pos, ItemStack itemStack) {
        if (worldIn.isClientSide) {
            return;
        }

        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof StackHolderTileEntity)) {
            return;
        }

        ((StackHolderTileEntity) tileEntity).setItemStack(itemStack);
    }

    public ItemStack getItemStack(TileEntity tileEntity) {
        if (!(tileEntity instanceof StackHolderTileEntity)) {
            return null;
        }

        return ((StackHolderTileEntity) tileEntity).getItemStack();
    }
}
