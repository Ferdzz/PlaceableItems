package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.init.PlaceableItemsItemRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.StringRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// TODO: Maybe this is out of scope of StackHolderBlockComponent, and should just use the TE directly instead
public class HorseArmorStandBlockComponent extends StackHolderBlockComponent {

    public enum HorseArmorType implements StringRepresentable {
        EMPTY("empty"),
        DIAMOND("diamond"),
        GOLD("gold"),
        IRON("iron"),
        LEATHER("leather");

        private final String name;

        HorseArmorType(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }
    }

    private static final EnumProperty<HorseArmorType> HORSE_ARMOR_TYPE = EnumProperty.create("horse_armor_type", HorseArmorType.class);

    @Override
    public Item asItem() {
        return PlaceableItemsItemRegistry.HORSE_ARMOR_STAND;
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORSE_ARMOR_TYPE);
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        // Override OnBlockPlacedBy to avoid having the armor stand set as the held stack
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> itemStacks = new ArrayList<>(super.getDrops(state, builder));
        itemStacks.add(new ItemStack(PlaceableItemsItemRegistry.HORSE_ARMOR_STAND));
        return itemStacks;
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockState newState;
        if (state.getValue(HORSE_ARMOR_TYPE) == HorseArmorType.EMPTY) {
            ItemStack heldItemStack = player.getItemInHand(handIn);
            Item heldItem = heldItemStack.getItem();
            if (heldItem.equals(Items.LEATHER_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.LEATHER);
            } else if (heldItem.equals(Items.IRON_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.IRON);
            } else if (heldItem.equals(Items.GOLDEN_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.GOLD);
            } else if (heldItem.equals(Items.DIAMOND_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.DIAMOND);
            } else {
                // If holding any other item
                return false;
            }
            this.setItemStack(worldIn, pos, heldItemStack);
            if (!player.isCreative()) {
                heldItemStack.shrink(1);
            }
        } else {
            // Drop the item held
            BlockEntity blockEntity = worldIn.getBlockEntity(pos);
            Block.popResource(worldIn, pos, this.getItemStack(blockEntity));
            newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.EMPTY);
            this.setItemStack(worldIn, pos, ItemStack.EMPTY);
        }

        worldIn.setBlockAndUpdate(pos, newState);
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        return blockState.setValue(HORSE_ARMOR_TYPE, HorseArmorType.EMPTY);
    }
}
