package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import me.ferdz.placeableitems.utils.PlaceableItemsFluidUtil;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidActionResult;

import java.util.HashSet;
import java.util.UUID;

public class ItemPlaceHandler {

    /**
     *  Array of players currently holding the placement hotkey
      */
    private HashSet<UUID> holdingKey = new HashSet<>();

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
        // Abort if the user is not holding the keybind
        if (!holdingKey.contains(e.getPlayer().getUUID()) || !e.getPlayer().getAbilities().mayBuild) {
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

        InteractionResult result = block.getBlockItem().place(
                new BlockPlaceContext(
                        new UseOnContext(e.getPlayer(), e.getHand(),
                                new BlockHitResult(e.getPlayer().getLookAngle(), e.getFace(), e.getPos(), false))));
        if (result == InteractionResult.SUCCESS) {
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
