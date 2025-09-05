package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;

public class PlaceableItemsBlockBuilder {

    public static PlaceableItemsBlockBuilder of() {
        return new PlaceableItemsBlockBuilder();
    }

    private VoxelShape shape;

    public PlaceableItemsBlockBuilder setShape(VoxelShape shape) {
        this.shape = shape;
        return this;
    }

    /**
     * @param name The name for the block, should be the same as the blockstate JSON
     * @param item The item associated with the block
     */
    public DeferredBlock<PlaceableItemsBlock> register(String name, Item item) {
        return PlaceableItemsBlockRegistry.BLOCKS.register(name, () ->
                {
                    PlaceableItemsBlock block = new PlaceableItemsBlock(
                            BlockBehaviour.Properties
                                    .of() // starts with a blank slate
                                    .noOcclusion()
                                    .isViewBlocking((state, worlds, pos) -> false)
                                    .isSuffocating((state, world, pos) -> false)
                                    .isRedstoneConductor((state, world, pos) -> false)
                    );

                    if (shape != null) {
                        block.setShape(shape);
                    }

                    PlaceableItemsMap.instance().put(item, block);
                    return block;
                }
        );
    }

}
