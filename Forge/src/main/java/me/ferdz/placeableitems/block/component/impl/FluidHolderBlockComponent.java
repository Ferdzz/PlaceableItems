package me.ferdz.placeableitems.block.component.impl;

import java.util.function.Predicate;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;

import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.network.PlaceableItemsPacketHandler;
import me.ferdz.placeableitems.network.SUpdateFluidHolderPacket;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * A block component capable of holding {@link FluidStack}s.
 *
 * @author Parker Hawke - Choco
 */
@WikiBlockComponentDefinition(description = "This block can hold fluids. Right click with a bucket to fill or empty fluids.")
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
     * @param fluidPredicate a fluid validation predicate. Can hold fluids that test true
     */
    public FluidHolderBlockComponent(int maxFluidAmount, boolean renderFluid, Predicate<Fluid> fluidPredicate) {
        Preconditions.checkArgument(maxFluidAmount >= 0, "Max fluid amount must be >= 0");
        Preconditions.checkArgument(fluidPredicate != null, "Fluid predicate must not be null");

        this.maxFluidAmount = maxFluidAmount;
        this.renderFluid = renderFluid;
        this.fluidPredicate = fluidPredicate;
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
        this(maxFluidAmount, renderFluid, Predicates.alwaysTrue());
    }

    /**
     * Create a new FluidHolderBlockComponent with a maximum fluid amount and a fluid
     * predicate. The fluid will be rendered in the world for the client.
     *
     * @param maxFluidAmount the maximum fluid amount (in millibuckets. 1,000 = 1 bucket).
     * Must be >= 0.
     * @param fluidPredicate a fluid validation predicate. Can hold fluids that test true
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
        this(maxFluidAmount, true, Predicates.alwaysTrue());
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
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) throws NotImplementedException {
        ItemStack item = player.getHeldItem(handIn);
        if (!(item.getItem() instanceof BucketItem)) {
            return false;
        }

        TileEntity tile = worldIn.getTileEntity(pos);
        if (!(tile instanceof FluidHolderTileEntity)) {
            return false;
        }

        BucketItem bucket = (BucketItem) item.getItem();
        FluidHolderTileEntity fluidTile = (FluidHolderTileEntity) tile;

        if (bucket == Items.BUCKET) { // Empty bucket
            FluidStack fluidStack = fluidTile.getFluid();
            if (fluidStack.getAmount() < BUCKET_FLUID_AMOUNT) {
                return false;
            }

            Fluid fluid = fluidStack.getFluid();

            SoundEvent sound = fluid.getAttributes().getEmptySound(fluidStack);
            if (sound == null) {
                sound = fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL;
            }

            player.playSound(sound, 1.0F, 1.0F);

            if (!worldIn.isRemote) {
                ItemStack filledBucket = new ItemStack(fluid.getFilledBucket());

                if (!player.abilities.isCreativeMode) {
                    item.shrink(1);
                    if (!player.inventory.addItemStackToInventory(filledBucket)) {
                        player.dropItem(filledBucket, false); // Drop the bucket in the world if the inventory is already full
                    }
                }

                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) player, filledBucket);
                fluidStack.shrink(BUCKET_FLUID_AMOUNT);
                fluidTile.setFluid(fluidStack);

                IChunk chunk = worldIn.getChunk(pos);
                if (chunk instanceof Chunk) {
                    PlaceableItemsPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> (Chunk) chunk), new SUpdateFluidHolderPacket(fluidTile));
                }
            }

            return true;
        }
        else if (fluidPredicate.test(bucket.getFluid())) { // Bucket with a fluid
            FluidStack fluidStack = fluidTile.getFluid();
            Fluid fluid = fluidStack.getFluid();
            if (fluidStack.getAmount() + BUCKET_FLUID_AMOUNT > maxFluidAmount || (!fluidStack.isEmpty() && bucket.getFluid() != fluid)) {
                return !player.isSneaking();
            }

            SoundEvent sound = fluid.getAttributes().getEmptySound(fluidStack);
            if (sound == null) {
                sound = bucket.getFluid().isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
            }

            player.playSound(sound, 1.0F, 1.0F);
            player.addStat(Stats.ITEM_USED.get(bucket));

            if (!worldIn.isRemote) {
                if (!player.abilities.isCreativeMode) {
                    item.shrink(1);
                    player.addItemStackToInventory(new ItemStack(Items.BUCKET));
                }

                if (fluidStack.isEmpty()) {
                    fluidTile.setFluid(new FluidStack(bucket.getFluid(), BUCKET_FLUID_AMOUNT));
                } else {
                    fluidStack.grow(BUCKET_FLUID_AMOUNT);
                    fluidTile.setFluid(fluidStack);
                }

                IChunk chunk = worldIn.getChunk(pos);
                if (chunk instanceof Chunk) {
                    PlaceableItemsPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> (Chunk) chunk), new SUpdateFluidHolderPacket(fluidTile));
                }
            }
        }

        // We return !player.isSneaking() to ensure fluids are not placed accidentally if a fluid is not accepted (i.e. lava. That stuff hurts)
        return !player.isSneaking();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FluidHolderTileEntity();
    }

}
