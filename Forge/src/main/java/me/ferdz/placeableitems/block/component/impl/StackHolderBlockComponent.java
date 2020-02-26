package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class StackHolderBlockComponent extends AbstractBlockComponent {

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        this.setItemStack(worldIn, pos, stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileEntity = builder.get(LootParameters.BLOCK_ENTITY);
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
        if (worldIn.isRemote) {
            return;
        }

        TileEntity tileEntity = worldIn.getTileEntity(pos);
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
