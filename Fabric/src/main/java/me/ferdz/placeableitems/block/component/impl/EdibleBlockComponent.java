package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EdibleBlockComponent extends AbstractBlockComponent {
    // TODO: Make some sort of progress when eating, not instantly on right click

    private final boolean replacesWithBowl;

    public EdibleBlockComponent() {
        this(false);
    }

    /**
     * Creates a new EdibleBlockComponent
     * @param replacesWithBowl if the block should be replaced with a bowl after consumption
     */
    public EdibleBlockComponent(boolean replacesWithBowl) {
        this.replacesWithBowl = replacesWithBowl;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        Item item = state.getBlock().asItem();
        
        FoodComponent food = item.getFoodComponent();
        if (food == null) {
            return false;
        }
        ItemStack itemStack = new ItemStack(item);

        if (player.canConsume(food.isAlwaysEdible()) || player.isCreative()) {
            itemStack.finishUsing(worldIn, player);
            player.eatFood(worldIn, itemStack);
            worldIn.breakBlock(hit.getBlockPos(), false);
            //state.removedByPlayer(worldIn, pos, player, false, null);

            // Replace the block with a Bowl if it was requested
            if (this.replacesWithBowl) {
                BlockState bowlState = PlaceableItemsBlockRegistry.BOWL.getDefaultState()
                        .with(PlaceableItemsBlock.ROTATION, state.get(PlaceableItemsBlock.ROTATION));
                worldIn.setBlockState(pos, bowlState);
            }

            return true;
        }
        return false;
    }
}
