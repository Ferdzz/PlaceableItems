package me.ferdz.placeableitems.block.component;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public interface IBlockComponent {

    /**
     * {@link net.minecraft.block.Block#onBlockActivated(BlockState, World, BlockPos, PlayerEntity, Hand, BlockRayTraceResult)}  }
     */
    boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws AbstractBlockComponent.NotImplementedException;

    /**
     * {@link net.minecraft.block.Block#fillStateContainer(StateContainer.Builder)}  }
     */
    void fillStateContainer(StateContainer.Builder<Block, BlockState> builder);
}
