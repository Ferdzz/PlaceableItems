package me.ferdz.placeableitems.block.component.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.blockentities.FluidHolderBlockEntity;
import me.ferdz.placeableitems.utils.FluidStack;

import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * A block component capable of holding {@link FluidStack}s.
 *
 * @author Parker Hawke - Choco
 */
public class FluidHolderBlockComponent extends AbstractBlockComponent {

    private static final int BUCKET_FLUID_AMOUNT = 1000;

    private final int maxFluidAmount;
    private final boolean renderFluid;
    private final Predicate<Fluid> fluidPredicate;

    /**
     * Create a new FluidHolderBlockComponent with a maximum fluid amount, whether or not
     * the fluid should be rendered and a fluid predicate.
     *
     * @param maxFluidAmount the maximum fluid amount (in millibuckets. 1,000 = 1 bucket).
     * Must be >= 0.
     * @param renderFluid whether a fluid should be rendered for the client
     * @param fluidPredicate a fluid validation predicate. Can hold fluids that test true.
     * If null, an always true predicate will be used.
     */
    public FluidHolderBlockComponent(int maxFluidAmount, boolean renderFluid, Predicate<Fluid> fluidPredicate) {
        Preconditions.checkArgument(maxFluidAmount >= 0, "Max fluid amount must be >= 0");
        this.maxFluidAmount = maxFluidAmount;
        this.renderFluid = renderFluid;
        this.fluidPredicate = (fluidPredicate != null) ? fluidPredicate : Predicates.alwaysTrue();
    }

    /**
     * Create a new FluidHolderBlockComponent with a maximum fluid amount and whether or
     * not the fluid should be rendered. All fluids can be held in this holder.
     *
     * @param maxFluidAmount the maximum fluid amount (in millibuckets. 1,000 = 1 bucket).
     * Must be >= 0.
     * @param renderFluid whether a fluid should be rendered for the client
     */
    public FluidHolderBlockComponent(int maxFluidAmount, boolean renderFluid) {
        this(maxFluidAmount, renderFluid, null);
    }

    /**
     * Create a new FluidHolderBlockComponent with a maximum fluid amount and a fluid
     * predicate. The fluid will be rendered in the world for the client.
     *
     * @param maxFluidAmount the maximum fluid amount (in millibuckets. 1,000 = 1 bucket).
     * Must be >= 0.
     * @param fluidPredicate a fluid validation predicate. Can hold fluids that test true.
     * If null, an always true predicate will be used.
     */
    public FluidHolderBlockComponent(int maxFluidAmount, Predicate<Fluid> fluidPredicate) {
        this(maxFluidAmount, true, fluidPredicate);
    }

    /**
     * Create a new FluidHolderBlockComponent with a maximum fluid amount. The fluid will
     * be rendered in the world for the client. All fluids can be held in this holder.
     *
     * @param maxFluidAmount the maximum fluid amount (in millibuckets. 1,000 = 1 bucket).
     * Must be >= 0.
     */
    public FluidHolderBlockComponent(int maxFluidAmount) {
        this(maxFluidAmount, true, null);
    }

    /**
     * Get the maximum amount of fluid this component can hold.
     *
     * @return the maximum amount of fluid in millibuckets (1,000 = 1 bucket)
     */
    public int getMaxFluidAmount() {
        return maxFluidAmount;
    }

    /**
     * Check whether or not this component should render the fluid in the world.
     *
     * @return true if rendered in the world, false otherwise
     */
    public boolean shouldRenderFluid() {
        return renderFluid;
    }

    /**
     * Get the fluid predicate. If this predicate tests true, the fluid can be held in
     * this holder.
     *
     * @return the fluid predicate
     */
    public Predicate<Fluid> getFluidPredicate() {
        return fluidPredicate;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) throws NotImplementedException {
        ItemStack item = player.getStackInHand(handIn);
        if (!(item.getItem() instanceof BucketItem)) {
            return false;
        }

        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (!(tile instanceof FluidHolderBlockEntity)) {
            return false;
        }

        BucketItem bucket = (BucketItem) item.getItem();
        FluidHolderBlockEntity fluidTile = (FluidHolderBlockEntity) tile;

        if (bucket == Items.BUCKET) { // Empty bucket
            if (fluidTile.getFluidAmount() < BUCKET_FLUID_AMOUNT) {
                return false;
            }

            FluidStack fluidStack = fluidTile.getFluid();
            Fluid fluid = fluidStack.getFluid();

            SoundEvent sound = fluid.matches(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL;
            player.playSound(sound, 1.0F, 1.0F);

            if (!worldIn.isClient()) {
                ItemStack filledBucket = new ItemStack(fluid.getBucketItem());

                if (!player.abilities.creativeMode) {
                    item.decrement(1);
                    if (!player.inventory.insertStack(filledBucket)) {
                        player.dropItem(filledBucket, false);
                    }
                }

                Criterions.FILLED_BUCKET.handle((ServerPlayerEntity) player, filledBucket);
                fluidTile.setFluidAmount(fluidTile.getFluidAmount() - BUCKET_FLUID_AMOUNT);
                fluidTile.sync();
            }

            return true;
        }
        else { // Bucket with a fluid
            Fluid bucketFluid = getFluidFromItem(bucket);
            if (bucketFluid == Fluids.EMPTY) { // Invalid case, can't handle
                return false;
            }

            if (!fluidPredicate.test(bucketFluid)) {
                return !player.isSneaking();
            }

            FluidStack fluidStack = fluidTile.getFluid();
            Fluid fluid = fluidStack.getFluid();
            if (fluidTile.getFluidAmount() + BUCKET_FLUID_AMOUNT > maxFluidAmount || (!fluidStack.isEmpty() && bucketFluid != fluid)) {
                return !player.isSneaking();
            }

            SoundEvent sound = fluid.matches(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;

            player.playSound(sound, 1.0F, 1.0F);
            player.incrementStat(Stats.USED.getOrCreateStat(bucket));

            if (!worldIn.isClient()) {
                if (!player.abilities.creativeMode) {
                    item.decrement(1);
                    player.inventory.insertStack(new ItemStack(Items.BUCKET));
                }

                if (fluidStack.isEmpty()) {
                    fluidTile.setFluid(new FluidStack(bucketFluid, BUCKET_FLUID_AMOUNT));
                } else {
                    fluidTile.setFluidAmount(fluidTile.getFluidAmount() + BUCKET_FLUID_AMOUNT);
                }

                fluidTile.sync();
            }
        }

        // We return !player.isSneaking() to ensure fluids are not placed accidentally if a fluid is not accepted (i.e. lava. That stuff hurts)
        return !player.isSneaking();
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    @Override
    public BlockEntity createTileEntity(BlockView world) {
        return new FluidHolderBlockEntity();
    }

    // Really Fabric?
    private Fluid getFluidFromItem(Item item) {
        if (item == null) {
            return Fluids.EMPTY;
        }

        for (Iterator<Fluid> it = Registry.FLUID.iterator(); it.hasNext();) {
            Fluid fluid = it.next();
            if (fluid.getBucketItem() == item && fluid.isStill(fluid.getDefaultState())) {
                return fluid;
            }
        }

        return Fluids.EMPTY;
    }

}
