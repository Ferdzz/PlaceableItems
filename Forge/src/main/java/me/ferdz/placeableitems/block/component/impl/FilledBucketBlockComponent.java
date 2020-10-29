package me.ferdz.placeableitems.block.component.impl;


import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right click with an empty bucket to empty the placed bucket")
public class FilledBucketBlockComponent extends AbstractBlockComponent {

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        ItemStack itemStack = player.getHeldItem(handIn);
        if (itemStack.getItem() != Items.BUCKET) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }

        // Replace the bucket with the filled version
        worldIn.setBlockState(pos,
                PlaceableItemsBlockRegistry.BUCKET.getDefaultState()
                        .with(BiPositionBlockComponent.UP, state.get(BiPositionBlockComponent.UP))
                        .with(PlaceableItemsBlock.ROTATION, state.get(PlaceableItemsBlock.ROTATION))
        );

        if (!player.abilities.isCreativeMode) {
            itemStack.shrink(1);
            player.setHeldItem(handIn, new ItemStack(((PlaceableItemsBlock) state.getBlock()).asItem()));
        }
        return true;
    }
}
