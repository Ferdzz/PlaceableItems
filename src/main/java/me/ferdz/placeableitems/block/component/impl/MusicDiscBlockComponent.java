package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.HitResult;

import java.util.Collections;
import java.util.List;

public class MusicDiscBlockComponent extends AbstractBlockComponent {

    public enum MusicDiscType implements StringRepresentable {
        MUSIC_DISC_11("11", Items.MUSIC_DISC_11),
        MUSIC_DISC_13("13", Items.MUSIC_DISC_13),
        MUSIC_DISC_BLOCKS("blocks", Items.MUSIC_DISC_BLOCKS),
        MUSIC_DISC_CAT("cat", Items.MUSIC_DISC_CAT),
        MUSIC_DISC_CHIRP("chirp", Items.MUSIC_DISC_CHIRP),
        MUSIC_DISC_FAR("far", Items.MUSIC_DISC_FAR),
        MUSIC_DISC_MALL("mall", Items.MUSIC_DISC_MALL),
        MUSIC_DISC_MELLOHI("mellohi", Items.MUSIC_DISC_MELLOHI),
        MUSIC_DISC_STAL("stal", Items.MUSIC_DISC_STAL),
        MUSIC_DISC_STRAD("strad", Items.MUSIC_DISC_STRAD),
        MUSIC_DISC_WARD("ward", Items.MUSIC_DISC_WARD),
        MUSIC_DISC_WAIT("wait", Items.MUSIC_DISC_WAIT);

        private final String name;
        private final Item item;

        MusicDiscType(String name, Item item) {
            this.name = name;
            this.item = item;
        }

        public String toString() {
            return this.name;
        }

        public String getSerializedName() {
            return this.name;
        }

        public Item getItem() {
            return item;
        }
    }

    public static final EnumProperty<MusicDiscType> DISC_TYPE = EnumProperty.create("disc_type", MusicDiscType.class);

    // TODO:
//    @Override
//    public void register(PlaceableItemsBlock block, String name) {
//        for (MusicDiscType musicDiscType : MusicDiscType.values()) {
//            PlaceableItemsMap.instance().put(musicDiscType.item, block);
//        }
//    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISC_TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context, BlockState blockState) {
        Item item = context.getItemInHand().getItem();
        for (MusicDiscType musicDiscType : MusicDiscType.values()) {
            if (musicDiscType.item == item) {
                return blockState.setValue(DISC_TYPE, musicDiscType);
            }
        }
        return blockState;
    }

//    @Override
//    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
//        return Collections.singletonList(new ItemStack(state.getValue(DISC_TYPE).item));
//    }

//    @Override
//    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
//        return new ItemStack(state.getValue(DISC_TYPE).item);
//    }
}
