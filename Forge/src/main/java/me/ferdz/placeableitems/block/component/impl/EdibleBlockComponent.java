package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right click to eat")
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
    public boolean use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        Item item = state.getBlock().asItem();
        Food food = item.getFoodProperties();
        if (food == null) {
            return false;
        }
        ItemStack itemStack = new ItemStack(item);

        if (player.canEat(food.canAlwaysEat()) || player.isCreative()) {
            itemStack.finishUsingItem(worldIn, player);
            player.eat(worldIn, itemStack);
            state.removedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

            // Replace the block with a Bowl if it was requested
            if (this.replacesWithBowl) {
                BlockState bowlState = PlaceableItemsBlockRegistry.BOWL.defaultBlockState()
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION));
                worldIn.setBlockAndUpdate(pos, bowlState);
            }

            return true;
        }
        return false;
    }

    @Override
    public IFormattableTextComponent getDescription(ItemStack itemStack) {
        return new TranslationTextComponent("key.placeableitems.component.edible");
    }
}
