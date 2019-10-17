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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackHolderBlockComponent extends AbstractBlockComponent {

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (worldIn.isRemote) {
            return;
        }

        StackHolderTileEntity tileEntity = (StackHolderTileEntity) worldIn.getTileEntity(pos);
        if (tileEntity == null) {
            return;
        }
        tileEntity.setItemStack(stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        StackHolderTileEntity tileEntity = (StackHolderTileEntity) builder.get(LootParameters.BLOCK_ENTITY);
        if (tileEntity == null) {
            return new ArrayList<>();
        }
        return Collections.singletonList(tileEntity.getItemStack());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StackHolderTileEntity();
    }
}
