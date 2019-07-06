package me.ferdz.placeableitems.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class PlaceableItemsBlock extends Block {

    public PlaceableItemsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    //    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
//        return Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
//    }

    //    @Override
//    public boolean isVariableOpacity() {
//        return true;
//    }

    //    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
//
//    @Override
//    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
//        switch(direction) {
//            case COUNTERCLOCKWISE_90:
//            case CLOCKWISE_90:
//                switch(state.get(AXIS)) {
//                    case X:
//                        return state.with(AXIS, Direction.Axis.Z);
//                    case Z:
//                        return state.with(AXIS, Direction.Axis.X);
//                    default:
//                        return state;
//                }
//            default:
//                return state;
//        }
//    }
//
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        builder.add(AXIS);
//    }
//
//    @Override
//    public BlockState getStateForPlacement(BlockState state, Direction facing, BlockState state2, IWorld world, BlockPos pos1, BlockPos pos2, Hand hand) {
//        return this.getDefaultState().with(AXIS, facing.getAxis());
//    }
}
