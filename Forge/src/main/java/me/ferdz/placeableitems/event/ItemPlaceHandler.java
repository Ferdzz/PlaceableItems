package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import me.ferdz.placeableitems.utils.PlaceableItemsFluidUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.wrappers.BucketPickupHandlerWrapper;
import net.minecraftforge.fluids.capability.wrappers.FluidBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

public class ItemPlaceHandler {

    /**
     *  Array of players currently holding the placement hotkey
      */
    private HashSet<UUID> holdingKey = new HashSet<>();

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        // Abort if the user is not holding the keybind
        if (!holdingKey.contains(e.getPlayer().getUUID()) || !e.getPlayer().abilities.mayBuild) {
            return;
        }

        // TODO: Check if a `canPlayerEdit` check is needed for grief prevention

        ItemStack itemStack = e.getPlayer().getItemInHand(e.getHand());
        Item item = itemStack.getItem();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        if (item == Items.BUCKET) {
            // If we're holding an empty bucket, check if the user tried to fill a liquid
            FluidActionResult fluidActionResult = PlaceableItemsFluidUtil.tryVirtualPickUpFluid(e.getItemStack(), e.getPlayer(), e.getWorld(), e.getPos().relative(e.getFace()), e.getFace());
            if (fluidActionResult.isSuccess() && !itemStack.equals(fluidActionResult.result)) {
                e.setResult(Event.Result.DEFAULT);
                return; // Defer to default bucket behavior
            }
        }

        ActionResultType result = block.getBlockItem().place(
                new BlockItemUseContext(
                        new ItemUseContext(e.getPlayer(), e.getHand(),
                                new BlockRayTraceResult(e.getPlayer().getLookAngle(), e.getFace(), e.getPos(), false))));
        if (result == ActionResultType.SUCCESS) {
            e.setCanceled(true);
        }
        e.setCancellationResult(result);
    }

    public void setHoldingKey(UUID playerId, boolean isHoldingKey) {
        if (isHoldingKey) {
            this.holdingKey.add(playerId);
        } else {
            this.holdingKey.remove(playerId);
        }
    }

    public boolean isHoldingKey(UUID playerId) {
        return this.holdingKey.contains(playerId);
    }
}
