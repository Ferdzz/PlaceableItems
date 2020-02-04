package me.ferdz.placeableitems.client.model;

import java.util.IdentityHashMap;
import java.util.Map;

import me.ferdz.placeableitems.block.component.impl.FluidHolderBlockComponent;
import me.ferdz.placeableitems.client.model.complex.FluidUVType;
import me.ferdz.placeableitems.client.model.complex.ModelRenderDefinition;
import me.ferdz.placeableitems.client.renderer.tileentity.FluidHolderRenderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Represents a model definition for a fluid renderable in a {@link FluidHolderBlockComponent}.
 * Models should cache model data where possible (including rotation points and render elements)
 * and return them where necessary.
 * <p>
 * All states will have their render model definition cached and may be
 * recalculated at any time such that {@link #shouldRecalculate(BlockState)} returns true. Please
 * be cautious when returning true in the aforementioned method such that any computationally-expensive
 * calculations will be re-run (however {@link #calculateModelDefinition(BlockState)} is implemented).
 * <p>
 * FluidModel implementations should be registered in a {@link FMLClientSetupEvent} with the
 * {@link FluidHolderRenderer#bind(me.ferdz.placeableitems.block.PlaceableItemsBlock, FluidModel)}
 * method. If a {@link FluidHolderBlockComponent#shouldRenderFluid()} returns true but has no registered
 * FluidModel, an exception will be thrown an the client will crash.
 *
 * @see FluidHolderRenderer#bind(me.ferdz.placeableitems.block.PlaceableItemsBlock, FluidModel)
 *
 * @author Parker Hawke - Choco
 */
public abstract class FluidModel {

    private final Map<BlockState, ModelRenderDefinition> renderCache = new IdentityHashMap<>();

    /**
     * Get the definition for this model from the render cache (if present). If a model is not
     * present, it will be calculated and cached. Additionally, the model will be recalculated if the
     * result of {@link #shouldRecalculate(BlockState)} is true.
     *
     * @param state the state whose model to retrieve
     *
     * @return the model definition
     */
    public final ModelRenderDefinition getModelDefinition(BlockState state) {
        return shouldRecalculate(state) ? renderCache.compute(state, (s, existing) -> calculateModelDefinition(s)) : renderCache.computeIfAbsent(state, this::calculateModelDefinition);
    }

    /**
     * Calculate the definition for this model. This method does not account for cached models and
     * should be used sparingly. Method callers should use {@link #getModelDefinition(BlockState)}
     * instead to ensure a cached value is retrieved instead.
     * <p>
     * Implementations should consider the state's rotation and other states the block may have. Each
     * unique state will be cached in the model's render cache. This method will only be called such that
     * no model has yet been cached for the provided state, or {@link #shouldRecalculate(BlockState)}
     * returns true.
     * <p>
     * These definitions support negative values and will be relative to {@link #getOrigin(BlockState)}.
     *
     * @param state the state whose model definition to calculate
     *
     * @return the model definition
     */
    public abstract ModelRenderDefinition calculateModelDefinition(BlockState state);

    /**
     * Check whether or not the provided state has been cached.
     *
     * @param state the state to check
     *
     * @return true if cached, false otherwise
     */
    public boolean isCached(BlockState state) {
        return renderCache.containsKey(state);
    }

    /**
     * Clear the render cache for this model. Models for each unique state will be lazily recalculated when
     * required.
     */
    public void clearRenderCache() {
        this.renderCache.clear();
    }

    /**
     * Check whether or not a quad should be rendered according to the specified direction
     *
     * @param state the state to check
     * @param direction the direction to check
     *
     * @return true if the direction should be rendered, false otherwise
     */
    public boolean shouldRender(BlockState state, Direction direction) {
        return true;
    }

    /**
     * Get a pixel-scaled vector (values ranging from 0 - 16) for which this model may be rotated and positioned.
     * {@link #calculateModelDefinition(BlockState)} will be rendered relative to this point. (0, 0, 0) is defined
     * as the pixel positioned at the origin of the block relative to the floored block coordinates.
     * <p>
     * The point of origin will offset the rendering position and determine the point of rotation for this model.
     *
     * @param state the state whose origin point to get
     *
     * @return the rotation point
     */
    public abstract Vector3d getOrigin(BlockState state);

    /**
     * Check whether or not the model definition for the specified state should be recalculated. The
     * result of this method is considered by the {@link FluidHolderRenderer} when fetching a cached
     * model.
     * <p>
     * Caution should be had when overiding this method. Such that this method returns true, the model
     * definition will be calculated for this state and {@link #calculateModelDefinition(BlockState)} will be
     * invoked.
     *
     * @param state the state to check
     *
     * @return true if the model should be recalculated
     */
    public boolean shouldRecalculate(BlockState state) {
        return false;
    }

    /**
     * Get the type of UV mapping to be used for this fluid model.
     *
     * @return the fluid UV mapping
     */
    public FluidUVType getFluidUVType() {
        return FluidUVType.ELEMENT;
    }

    /**
     * Get the light values to be used when rendering the fluid. It is recommended that this method
     * only be overridden when a certain direction is improperly calculating light from its offset.
     * For instance, the fluid at the top of a glass bottle still has access to light from the sides
     * of the bottle even when a block is positioned above.
     *
     * @param world the world in which this model is being rendered
     * @param pos the position at which this model is being renderer
     * @param direction the direction from which the light should be calculated
     *
     * @return the light map values
     *
     * @see World#getCombinedLight(BlockPos, int)
     */
    public int getLight(World world, BlockPos pos, Direction direction) {
        return world.getCombinedLight(pos.offset(direction), 0);
    }

}
