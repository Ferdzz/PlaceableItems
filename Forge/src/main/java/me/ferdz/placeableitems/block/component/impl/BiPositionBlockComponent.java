package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;

// TODO: Allow for setting a custom UP collision shape

@WikiBlockComponentDefinition(description = "This item can be placed both at the top and bottom of another block")
public class BiPositionBlockComponent extends AbstractBlockComponent {
    public static final BooleanProperty UP = BlockStateProperties.UP;

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        if (context.getClickedFace() == Direction.DOWN) {
            return blockState.setValue(UP, true);
        } else {
            return blockState.setValue(UP, false);
        }
    }

    @Override
    public VoxelShape getShape(VoxelShape shape, BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        // Automatically shift the shape up when the UP state is true
        if (state.getValue(UP)) {
            return shape.move(0,  1 - shape.bounds().getYsize(), 0);
        } else {
            return shape;
        }
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.biposition");
    }
}
