package me.ferdz.placeableitems.client.model.complex;

import me.ferdz.placeableitems.client.model.FluidModel;

/**
 * Represents a type of UV mapping for fluid model elements defined in a {@link FluidModel}
 * or a {@link ModelRenderElement}.
 *
 * @author Parker Hawke - Choco
 */
public enum FluidUVType {

    /**
     * UV mappings will be relative to the model. For instance, if an element is defined from
     * points (1, 4, 3) {@literal ->} (3, 6, 5), it will use the fluid texture from those same
     * coordinates.
     */
    MODEL,

    /**
     * UV mappings will be stretched across the entire element. No matter where the element is
     * defined relative to the model, the entire fluid texture will be rendered on the model and
     * stretched to whatever size necessary.
     */
    ELEMENT;

}
