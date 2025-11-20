package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EdibleBlockComponent extends AbstractBlockComponent {
    // TODO: Make some sort of progress when eating, not instantly on right click

    private final RegistryObject<PlaceableItemsBlock> replacesWithBlock;

    public EdibleBlockComponent() {
        this(null);
    }

    /**
     * Creates a new EdibleBlockComponent
     *
     * @param replacesWithBlock if the block should be replaced with a block after consumption
     */
    public EdibleBlockComponent(RegistryObject<PlaceableItemsBlock> replacesWithBlock) {
        this.replacesWithBlock = replacesWithBlock;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) throws NotImplementedException {
        if (!(state.getBlock() instanceof PlaceableItemsBlock)) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        }

        if (worldIn.getBlockEntity(pos) instanceof StackHolderBlockEntity blockEntity) {
            Item item = blockEntity.getTheItem().getItem();
            FoodProperties food = item.getFoodProperties(new ItemStack(item), player);
            if (food == null) {
                return InteractionResult.PASS;
            }

            ItemStack itemStack = new ItemStack(item);
            if (player.canEat(food.canAlwaysEat()) || player.isCreative()) {
                itemStack.finishUsingItem(worldIn, player);
                player.eat(worldIn, itemStack);
                state.onDestroyedByPlayer(worldIn, pos, player, false, worldIn.getFluidState(pos));

                // Replace the block with a Bowl if it was requested
                if (this.replacesWithBlock != null) {
                    PlaceableItemsBlock replacingBlock = this.replacesWithBlock.get();
                    BlockState replacementBlockState = replacingBlock.defaultBlockState()
                            .setValue(PlaceableItemsBlock.ROTATION, state.getValue(PlaceableItemsBlock.ROTATION));
                    worldIn.setBlockAndUpdate(pos, replacementBlockState);
                    // Ensure placed item is registered in the TE for drops
                    replacingBlock.setPlacedBy(worldIn, pos, state, player, new ItemStack(PlaceableItemsMap.instance().getItemForBlock(replacingBlock)));
                }

                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            }
        }

        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public MutableComponent getDescription(ItemStack itemStack) {
        return Component.translatable("key.placeableitems.component.edible");
    }
}
