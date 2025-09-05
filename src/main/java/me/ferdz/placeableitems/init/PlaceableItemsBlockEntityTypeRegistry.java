package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.blockentity.StackHolderBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PlaceableItemsBlockEntityTypeRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PlaceableItems.MODID);

    public static final Supplier<BlockEntityType<StackHolderBlockEntity>> STACK_HOLDER = BLOCK_ENTITY_TYPES.register(
            "stack_holder_block_entity",
            // The block entity type, created using a builder.
            () -> {
                return BlockEntityType.Builder.of(
                                // The supplier to use for constructing the block entity instances.
                                StackHolderBlockEntity::new,
                                // A vararg of blocks that can have this block entity.
                                // This assumes the existence of the referenced blocks as DeferredBlock<Block>s.
                                // TODO: Plug all types of blocks
                                PlaceableItemsMap.instance().values().toArray(new Block[0])
                        )
                        // Build using null; vanilla does some datafixer shenanigans with the parameter that we don't need.
                        .build(null);
            }
    );

}
