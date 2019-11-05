package me.ferdz.placeableitems.block.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.blockentities.StackHolderBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextParameters;

public class StackHolderBlockComponent extends AbstractBlockComponent {

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (worldIn.isClient()) {
            return;
        }

        StackHolderBlockEntity tileEntity = (StackHolderBlockEntity) worldIn.getBlockEntity(pos);
        if (tileEntity == null) {
            return;
        }
        tileEntity.setItemStack(stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        StackHolderBlockEntity tileEntity = (StackHolderBlockEntity) builder.get(LootContextParameters.BLOCK_ENTITY);
        if (tileEntity == null) {
            return new ArrayList<>();
        }
        return Collections.singletonList(tileEntity.getItemStack());
    }

    @Override
    public boolean hasTileEntity() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public BlockEntity createTileEntity(BlockView world) {
        return new StackHolderBlockEntity();
    }
}
