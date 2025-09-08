package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PlaceableItemsBlock extends Block implements EntityBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    private VoxelShape shape;
    private List<IBlockComponent> components;

    public PlaceableItemsBlock(Properties properties) {
        super(properties);
        this.components = new ArrayList<>();
    }

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

    // region Blockstate & rotation
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        // Calculates the angle & maps it to the rotation state
        BlockState blockState = this.defaultBlockState().setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15);
        for (IBlockComponent component : components) {
            blockState = component.getStateForPlacement(context, blockState);
        }
        return blockState;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION), 16));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 16));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

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
    protected RenderShape getRenderShape(BlockState state) {
        // Important, otherwise models are rendered invisible due to parent class
        return RenderShape.MODEL;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0f;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        components.forEach((component) -> component.animateTick(state, level, pos, random));
    }

    // endregion
}