package me.ferdz.placeableitems.blockentities;

import me.ferdz.placeableitems.init.PlaceableItemBlockEntityRegistry;
import net.minecraft.block.entity.BlockEntity;

public class PlaceableItemBlockEntity extends BlockEntity {

    public PlaceableItemBlockEntity() {
        super(PlaceableItemBlockEntityRegistry.PLACEABLE_ITEM_BLOCK_ENTITY);
        
    }
}