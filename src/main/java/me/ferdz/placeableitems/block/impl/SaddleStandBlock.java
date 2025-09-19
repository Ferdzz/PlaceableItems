package me.ferdz.placeableitems.block.impl;

import me.ferdz.placeableitems.block.RotationBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SaddleStandBlock extends RotationBlock implements EntityBlock {

    private static final BooleanProperty FILLED = BooleanProperty.create("filled");

    public SaddleStandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FILLED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FILLED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) level.getBlockEntity(pos);
        if (state.getValue(FILLED)) {
            if (!level.isClientSide()) {
                Block.popResource(level, pos, blockEntity.getTheItem());
                level.setBlockAndUpdate(pos, state.setValue(FILLED, false));
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (stack.getItem().equals(Items.SADDLE)) {
            blockEntity.setTheItem(stack.copyWithCount(1));
            level.setBlockAndUpdate(pos, state.setValue(FILLED, true));
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StackHolderBlockEntity(blockPos, blockState);
    }
}
