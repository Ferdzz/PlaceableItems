package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockEntityTypeRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class PotionBlockComponent extends AbstractBlockComponent {

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        BlockEntity blockEntity = worldIn.getBlockEntity(pos);
        if (!(blockEntity instanceof StackHolderBlockEntity)) {
            return InteractionResult.PASS;
        }

        ItemStack itemStack = ((StackHolderBlockEntity) blockEntity).getTheItem().copy();
        itemStack.finishUsingItem(worldIn, player);
        Block glassBottleBlock = PlaceableItemsBlockRegistry.GLASS_BOTTLE.get();
        worldIn.setBlockAndUpdate(pos, glassBottleBlock
                .defaultBlockState()
                .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION)));
        // Ensure placed item is registered in the TE for drops
        glassBottleBlock.setPlacedBy(worldIn, pos, state, player, new ItemStack(Items.GLASS_BOTTLE));

        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }
}
