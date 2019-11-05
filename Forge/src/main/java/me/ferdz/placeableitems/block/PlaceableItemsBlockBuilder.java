package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.block.component.IBlockComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;

import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsBlockBuilder {

    private List<IBlockComponent> components;

    public PlaceableItemsBlockBuilder() {
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlockBuilder addComponent(IBlockComponent component) {
        this.components.add(component);
        return this;
    }

    public PlaceableItemsBlock build() {
        return new PlaceableItemsBlock() {
            @Override
            protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
                super.fillStateContainer(builder);
                // Override the fillStateContainer and refer to the components array to bypass the fact that it's
                // being called from within super constructor
                for (IBlockComponent component : components) {
                    component.fillStateContainer(builder);
                }
            }
        }.addComponents(this.components);
    }
}
