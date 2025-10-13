package me.ferdz.placeableitems.block.impl;

import me.ferdz.placeableitems.block.RotationBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class HorseArmorStandBlock extends RotationBlock implements EntityBlock {

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

    public HorseArmorStandBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HORSE_ARMOR_TYPE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockState newState;
        if (state.getValue(HORSE_ARMOR_TYPE) == HorseArmorType.EMPTY) {
            Item item = stack.getItem();
            if (item.equals(Items.LEATHER_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.LEATHER);
            } else if (item.equals(Items.IRON_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.IRON);
            } else if (item.equals(Items.GOLDEN_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.GOLD);
            } else if (item.equals(Items.DIAMOND_HORSE_ARMOR)) {
                newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.DIAMOND);
            } else {
                // If holding any other item
                return InteractionResult.PASS;
            }
            StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) level.getBlockEntity(pos);
            blockEntity.setTheItem(stack.copyWithCount(1));

            if (!player.hasInfiniteMaterials()) {
                stack.shrink(1);
            }
        } else {
            // Drop the item held
            StackHolderBlockEntity blockEntity = (StackHolderBlockEntity) level.getBlockEntity(pos);
            Block.popResource(level, pos, blockEntity.getTheItem());
            newState = state.setValue(HORSE_ARMOR_TYPE, HorseArmorType.EMPTY);
            blockEntity.setTheItem(ItemStack.EMPTY);
        }

        level.setBlockAndUpdate(pos, newState);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StackHolderBlockEntity(blockPos, blockState);
    }
}
