package me.ferdz.placeableitems.client.model.complex;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

/**
 * Represents a collection of {@link ModelRenderElement}s. This is an over-glorified collection
 * wrapper with a few utility methods to collect and ease the rendering of multiple elements.
 *
 * @author Parker Hawke - Choco
 */
public class ModelRenderDefinition {

    private final List<ModelRenderElement> elements;

    private ModelRenderDefinition(List<ModelRenderElement> elements) {
        this.elements = ImmutableList.copyOf(elements);
    }

    /**
     * Create a ModelRenderDefinition with a set of render elements. These elements will be filtered
     * to remove any null or duplicate elements.
     *
     * @param elements the elements to collect
     *
     * @return the model definition
     */
    public static ModelRenderDefinition withElements(ModelRenderElement... elements) {
        return new ModelRenderDefinition(Arrays.stream(elements).filter(Objects::nonNull).distinct().collect(Collectors.toList()));
    }

    /**
     * Get a list of all the elements in this definition. The returned collection is immutable.
     *
     * @return the elements
     */
    public List<ModelRenderElement> getElements() {
        return elements;
    }

    /**
     * Rotate this render definition clockwise around the y axis.
     *
     * @param radians the amount (in radians) by which to rotate this bounding box.
     *
     * @return a new {@link ModelRenderDefinition} rotated clockwise by the specified radians.
     * All contained elements will also be new (therefore will not be == to one another)
     *
     * @see ModelRenderElement#rotateAroundY(double)
     */
    public ModelRenderDefinition rotateAroundY(double radians) {
        List<ModelRenderElement> rotatedElements = elements.stream().map(b -> b.rotateAroundY(radians)).collect(Collectors.toList());
        return new ModelRenderDefinition(rotatedElements);
    }

}
