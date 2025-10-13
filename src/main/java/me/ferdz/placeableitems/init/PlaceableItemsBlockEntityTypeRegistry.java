package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.RotationBlock;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;
import java.util.function.Supplier;

public class PlaceableItemsBlockEntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PlaceableItems.MODID);

    public static final Supplier<BlockEntityType<StackHolderBlockEntity>> STACK_HOLDER = BLOCK_ENTITY_TYPES.register(
            "stack_holder_block_entity",
            // The block entity type, created using a builder.
            () -> {
                // Add all from the map
                Set<Block> blocks = new HashSet<>(PlaceableItemsMap.instance().values());

                blocks.add(PlaceableItemsBlockRegistry.HORSE_ARMOR_STAND.get());
                blocks.add(PlaceableItemsBlockRegistry.SADDLE_STAND.get());

                return new BlockEntityType(StackHolderBlockEntity::new, blocks.toArray(new Block[0]));
            }
    );

}
