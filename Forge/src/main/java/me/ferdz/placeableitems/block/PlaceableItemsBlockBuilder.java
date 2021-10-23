package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsTileEntityTypeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsBlockBuilder {

    private List<IBlockComponent> components;
    private int lightLevel;

    public PlaceableItemsBlockBuilder() {
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlockBuilder addComponent(IBlockComponent component) {
        this.components.add(component);
        return this;
    }

    public PlaceableItemsBlockBuilder setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
        return this;
    }

    public PlaceableItemsBlock build() {
        PlaceableItemsBlock block = new PlaceableItemsBlock(
                Block.Properties.of(Material.DECORATION)
                        .noOcclusion()
                        .lightLevel((state) -> lightLevel)) {
            @Override
            protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
                super.createBlockStateDefinition(builder);
                // Override the fillStateContainer and refer to the components array to bypass the fact that it's
                // being called from within super constructor
                for (IBlockComponent component : components) {
                    component.createBlockStateDefinition(builder);
                }
            }
        }.addComponents(this.components);

        // Ensure all tile entities created by its components declare it as a valid block
        block.getComponents().forEach(component -> {
            Class<? extends TileEntity> tileEntityClass = component.getTileEntityClass(null);
            if (tileEntityClass != null) {
                PlaceableItemsTileEntityTypeRegistry.assignTo(tileEntityClass, block);
            }
        });

        return block;
    }
}
