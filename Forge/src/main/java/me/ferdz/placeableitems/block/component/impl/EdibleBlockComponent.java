package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent.NotImplementedException;

@WikiBlockComponentDefinition(description = "Right click to eat")
public class EdibleBlockComponent extends AbstractBlockComponent {
    // TODO: Make some sort of progress when eating, not instantly on right click

    private final PlaceableItemsBlock replacesWithBlock;

    public EdibleBlockComponent() {
        this(null);
    }

    /**
     * Creates a new EdibleBlockComponent
     * @param replacesWithBlock if the block should be replaced with a block after consumption
     */
    public EdibleBlockComponent(PlaceableItemsBlock replacesWithBlock) {
        this.replacesWithBlock = replacesWithBlock;
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        Item item = state.getBlock().asItem();
        FoodProperties food = item.getFoodProperties(new ItemStack(item), player);
        if (food == null) {
            return false;
        }
        ItemStack itemStack = new ItemStack(item);

        if (player.canEat(food.canAlwaysEat()) || player.isCreative()) {
            itemStack.finishUsingItem(worldIn, player);
            player.eat(worldIn, itemStack);
            state.onDestroyedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

            // Replace the block with a Bowl if it was requested
            if (this.replacesWithBlock != null) {
                BlockState replacementBlockState = this.replacesWithBlock.defaultBlockState()
                        .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION));
                worldIn.setBlockAndUpdate(pos, replacementBlockState);
            }

            return true;
        }
        return false;
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return new TranslatableComponent("key.placeableitems.component.edible");
    }
}
