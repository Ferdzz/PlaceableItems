package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsTileEntityTypeRegistry;
import me.ferdz.placeableitems.tileentity.StackHolderBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * A BlockComponent that holds the ItemStack that was placed, then handles drops.
 * Used to make sure that enchanted items do not lose their metadatas upon breaking
 */
public class StackHolderBlockComponent extends AbstractBlockComponent {

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        this.setItemStack(worldIn, pos, stack);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        BlockEntity tileEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        return Collections.singletonList(this.getItemStack(tileEntity));
    }

    @Override
    public Class<? extends BlockEntity> getTileEntityClass(BlockState state) {
        return StackHolderBlockEntity.class;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return PlaceableItemsTileEntityTypeRegistry.STACK_HOLDER.create(pos, state);
    }

    public void setItemStack(Level worldIn, BlockPos pos, ItemStack itemStack) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof StackHolderBlockEntity)) {
            return;
        }

        ((StackHolderBlockEntity) tileEntity).setItemStack(itemStack);
    }

    public ItemStack getItemStack(BlockEntity tileEntity) {
        if (!(tileEntity instanceof StackHolderBlockEntity)) {
            return null;
        }

        return ((StackHolderBlockEntity) tileEntity).getItemStack();
    }
}
