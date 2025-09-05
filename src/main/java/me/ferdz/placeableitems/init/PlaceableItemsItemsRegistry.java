package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PlaceableItemsItemsRegistry {
    // Create a Deferred Register to hold Items which will all be registered under the "placeableitems" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PlaceableItems.MODID);
}
