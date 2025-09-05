package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PlaceableItemsBlockRegistry {
    // Create a Deferred Register to hold Blocks which will all be registered under the "placeableitems" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlaceableItems.MODID);

    public static final DeferredBlock<PlaceableItemsBlock> COAL_BLOCK = PlaceableItemsBlockBuilder.of()
            .setShape(VoxelShapesUtil.create(10, 6, 10))
            .register("coal",Items.COAL);
}
