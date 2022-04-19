package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsTileEntityTypeRegistry;
import me.ferdz.placeableitems.tileentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.tileentity.SyncedStackHolderBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class PotionBlockComponent extends StackHolderBlockComponent {

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        if (!(tileEntity instanceof StackHolderBlockEntity)) {
            return false;
        }

        ItemStack itemStack = ((StackHolderBlockEntity) tileEntity).getItemStack().copy();
        itemStack.finishUsingItem(worldIn, player);
        worldIn.setBlockAndUpdate(pos, PlaceableItemsBlockRegistry.GLASS_BOTTLE
                .defaultBlockState()
                .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION)));

        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return PlaceableItemsTileEntityTypeRegistry.SYNCED_STACK_HOLDER.create(pos, state);
    }
}
