package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.material.PlaceableItemsMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBone extends PlaceableItemsBlock {

    public BlockBone() {
        super(Block.Properties.create(PlaceableItemsMaterials.PLACEABLE_ITEMS_MATERIAL));
    }
}
