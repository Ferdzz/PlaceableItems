package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PlaceableItemsBlock extends RotationBlock implements EntityBlock {
    private VoxelShape shape;
    private List<IBlockComponent> components;

    public PlaceableItemsBlock(Properties properties) {
        super(properties);
        this.components = new ArrayList<>();
    }

    // region Interaction
    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        InteractionResult result = null;
        for (IBlockComponent component : this.components) {
            try {
                result = component.useItemOn(stack, state, level, pos, player, hand, hitResult);
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }

        if (result == null) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            return result;
        }
    }

    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter worldIn, Entity entityIn) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.updateEntityMovementAfterFallOn(worldIn, entityIn);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.updateEntityMovementAfterFallOn(worldIn, entityIn);
        }
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.fallOn(worldIn, state, pos, entityIn, fallDistance);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.fallOn(worldIn, state, pos, entityIn, fallDistance);
        }
    }
    // endregion

    // region Hitbox shape handling
    public void setShape(VoxelShape shape) {
        this.shape = shape;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        for (IBlockComponent component : this.components) {
            VoxelShape shape = component.getShape(this.shape, state, level, pos, context);
            if (shape != null) {
                return shape;
            }
        }
        return this.shape;
    }
    // endregion

    // region Components
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = super.getStateForPlacement(context);
        for (IBlockComponent component : components) {
            blockState = component.getStateForPlacement(context, blockState);
        }
        return blockState;
    }

    PlaceableItemsBlock addComponents(Iterable<IBlockComponent> components) {
        for (IBlockComponent component : components) {
            this.components.add(component);
        }
        return this;
    }

    public List<IBlockComponent> getComponents() {
        return components;
    }
    // endregion

    // region BlockEntity
    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StackHolderBlockEntity(blockPos, blockState);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        // Should always be true as all placeable items now store their associated stack
        if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            blockEntity.setTheItem(stack.copyWithCount(1));
        }
    }
    //endregion

    //region Item drops
    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        // Should always be true as all placeable items now store their associated stack
        if (params.getOptionalParameter(LootContextParams.BLOCK_ENTITY) instanceof StackHolderBlockEntity blockEntity) {
            return List.of(blockEntity.getTheItem());
        }
        return List.of();
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        if (level.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            return blockEntity.getTheItem();
        }
        return ItemStack.EMPTY;
    }
    //endregion

    // region Render
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        components.forEach((component) -> component.animateTick(state, level, pos, random));
    }
    // endregion
}