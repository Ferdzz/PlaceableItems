package me.ferdz.placeableitems.block.component.impl;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.loot.context.LootContext;

import java.util.Collections;
import java.util.List;

public class MusicDiscBlockComponent extends AbstractBlockComponent {

    public enum MusicDiscType implements StringIdentifiable {
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

        MusicDiscType(String p_i49342_3_, Item item) {
            this.name = p_i49342_3_;
            this.item = item;
        }

        public String asString() {
            return this.name;
        }

        public Item getItem() {
            return item;
        }
    }

    private static final EnumProperty<MusicDiscType> DISC_TYPE = EnumProperty.of("disc_type", MusicDiscType.class);

    @Override
    public void register(PlaceableItemsBlock block, String name) {
        for (MusicDiscType musicDiscType : MusicDiscType.values()) {
            PlaceableItemsMap.instance().put(musicDiscType.item, block);
        }
    }

    @Override
    public void fillStateContainer(StateFactory.Builder<Block, BlockState> builder) {
        builder.add(DISC_TYPE);
    }

    @Override
    public BlockState getStateForPlacement(ItemPlacementContext context, BlockState blockState) {
        Item item = context.getStack().getItem();
        for (MusicDiscType musicDiscType : MusicDiscType.values()) {
            if (musicDiscType.item == item) {
                return blockState.with(DISC_TYPE, musicDiscType);
            }
        }
        return blockState;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(state.get(DISC_TYPE).item));
    }
}
