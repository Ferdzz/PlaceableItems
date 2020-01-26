package me.ferdz.placeableitems.client.model;

import java.util.IdentityHashMap;
import java.util.Map;

import me.ferdz.placeableitems.block.component.impl.FluidHolderBlockComponent;
import me.ferdz.placeableitems.blockentities.renderers.FluidHolderBlockEntityRenderer;
import me.ferdz.placeableitems.client.AllVertexBoundingBox;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.Vec3d;
import net.minecraft.util.math.Direction;

/**
 * Represents a model definition for a fluid renderable in a {@link FluidHolderBlockComponent}.
 * Models should cache model data where possible (including rotation points and render bounds)
 * and return them where necessary.
 * <p>
 * All states will have their render bounds cached and may be
 * recalculated at any time such that {@link #shouldRecalculate(BlockState)} returns true. Please
 * be cautious when returning true in the aforementioned method such that any computationally-expensive
 * calculations will be re-run (however {@link #calculateRenderBounds(BlockState)} is implemented).
 * <p>
 * FluidModel implementations should be registered during client setup with the
 * {@link FluidHolderBlockEntityRenderer#bind(me.ferdz.placeableitems.block.PlaceableItemsBlock, FluidModel)}
 * method. If a {@link FluidHolderBlockComponent#shouldRenderFluid()} returns true but has no registered
 * FluidModel, an exception will be thrown an the client will crash.
 *
 * @see FluidHolderBlockEntityRenderer#bind(me.ferdz.placeableitems.block.PlaceableItemsBlock, FluidModel)
 *
 * @author Parker Hawke - Choco
 */
public abstract class FluidModel {

    private static final Vec3d ZEROED_OFFSET = new Vec3d(0.0D, 0.0D, 0.0D);

    private final Map<BlockState, AllVertexBoundingBox> renderCache = new IdentityHashMap<>();

    /**
     * Get the rendering bounds for this model from the render cache (if present) and optionally
     * recalculate the cached model. If a model is not present, it will be calculated and cached.
     *
     * @param state the state whose model to retrieve
     * @param recalculate whether or not to recalculate the model
     *
     * @return the rendering bounds
     */
    public final AllVertexBoundingBox getRenderBounds(BlockState state, boolean recalculate) {
        return recalculate ? renderCache.compute(state, (s, existing) -> calculateRenderBounds(s)) : renderCache.computeIfAbsent(state, this::calculateRenderBounds);
    }

    /**
     * Calculate the rendering bounds for this model. This method does not account for cached models
     * and should be used sparingly. Method callers should use {@link #getRenderBounds(BlockState, boolean)}
     * instead to ensure a cached value is retrieved instead.
     * <p>
     * Implementations should consider the state's rotation and other states the block may have. Each
     * unique state will be cached in the model's render cache. This method will only be called such that
     * no model has yet been cached for the provided state, or {@link #shouldRecalculate(BlockState)}
     * returns true.
     * <p>
     * These bounds support negative values and will be relative to {@link #getRotationPoint(BlockState)}.
     *
     * @param state the state whose render bounds to calculate
     *
     * @return the rendering bounds
     */
    public abstract AllVertexBoundingBox calculateRenderBounds(BlockState state);

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
     * Get a pixel-scaled vector (values ranging from 0 - 16) for which this model may be rotated.
     * {@link #calculateRenderBounds(BlockState)} will be rendered relative to this point. This value defaults
     * to (0, 0, 0), the pixel relative to the origin of the block
     *
     * @param state the state whose rotation point to get
     *
     * @return the rotation point
     */
    public Vec3d getRotationPoint(BlockState state) {
        return ZEROED_OFFSET;
    }

    /**
     * Check whether or not the render bounds for the specified state should be recalculated. The
     * result of this method is considered by the {@link FluidHolderBlockEntityRenderer} when fetching a cached
     * model.
     * <p>
     * Caution should be had when overiding this method. Such that this method returns true, rendering
     * bounds will be calculated for this state and {@link #calculateRenderBounds(BlockState)} will be
     * invoked.
     *
     * @param state the state to check
     *
     * @return true if the model should be recalculated
     */
    public boolean shouldRecalculate(BlockState state) {
        return false;
    }

}
