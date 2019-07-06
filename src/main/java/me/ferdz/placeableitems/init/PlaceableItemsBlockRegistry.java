package me.ferdz.placeableitems.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class PlaceableItemsBlockRegistry {

    public static Block testBlock;

    public static void initialize() {
        testBlock = new Block(Block.Properties.create(Material.ROCK)).setRegistryName("test");
    }
}
