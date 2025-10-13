package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsBlockBuilder {

    public static PlaceableItemsBlockBuilder of() {
        return new PlaceableItemsBlockBuilder();
    }

    private VoxelShape shape;
    private List<IBlockComponent> components;
    private int lightLevel;

    public PlaceableItemsBlockBuilder() {
        super();
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlockBuilder setShape(VoxelShape shape) {
        this.shape = shape;
        return this;
    }

    public PlaceableItemsBlockBuilder addComponent(IBlockComponent component) {
        this.components.add(component);
        return this;
    }

    public PlaceableItemsBlockBuilder setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
        return this;
    }

    /**
     * @param name The name for the block, should be the same as the blockstate JSON
     * @param items The item associated with the block
     */
    public DeferredBlock<PlaceableItemsBlock> register(String name, Item... items) {
        PlaceableItemsBlockRegistry.ALL_PLACEABLE_ITEM_IDS.add(name);
        return PlaceableItemsBlockRegistry.BLOCKS.register(name, () ->
                {
                    PlaceableItemsBlock block = new PlaceableItemsBlock(
                            BlockBehaviour.Properties
                                    .of() // starts with a blank slate
                                    .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(PlaceableItems.MODID, name)))
                                    .noOcclusion()
                                    .lightLevel((state) -> lightLevel)
                                    .isViewBlocking((state, worlds, pos) -> false)
                                    .isSuffocating((state, world, pos) -> false)
                                    .isRedstoneConductor((state, world, pos) -> false)
                    ) {
                        @Override
                        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                            super.createBlockStateDefinition(builder);
                            // Override the fillStateContainer and refer to the components array to bypass the fact that it's
                            // being called from within super constructor
                            for (IBlockComponent component : components) {
                                component.createBlockStateDefinition(builder);
                            }
                        }
                    };

                    if (shape != null) {
                        block.setShape(shape);
                    }
                    block.addComponents(components);

                    for (Item item : items) {
                        PlaceableItemsMap.instance().put(item, block);
                    }
                    return block;
                }
        );
    }
}
