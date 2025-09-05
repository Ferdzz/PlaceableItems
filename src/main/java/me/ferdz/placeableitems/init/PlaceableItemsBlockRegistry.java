package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PlaceableItemsBlockRegistry {
    // Create a Deferred Register to hold Blocks which will all be registered under the "placeableitems" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlaceableItems.MODID);

    // Creates a new Block with the id "placeableitems:example_block", combining the namespace and path
    public static final DeferredBlock<PlaceableItemsBlock> COAL_BLOCK =
            BLOCKS.register("coal_block", () ->
                    new PlaceableItemsBlock(
                            BlockBehaviour.Properties
                                    .of() // starts with a blank slate
                                    // TODO: Check for properties in survival
//                                    .strength(0.5f)

                                    .noOcclusion()
//                                    .isViewBlocking((state, worlds, pos) -> false)
//                                    .isSuffocating((state, world, pos) -> false)
//                                    .isRedstoneConductor((state, world, pos) -> false)
//                                    .sound(SoundType.WOOD)
                    )
            );

}
