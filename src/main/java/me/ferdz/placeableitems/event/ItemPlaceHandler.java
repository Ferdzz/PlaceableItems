package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemPlaceHandler {

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock e) {
//        if (e.getWorld().isRemote() || e.getPlayer() == null || e.getFace() == null) {
//            return;
//        }
        if (!e.getPlayer().isSneaking()) { // TODO: #13 Make configurable hotkey for placing items
            return; // Abort if the user is not sneaking
        }

        ItemStack itemStack = e.getPlayer().getHeldItem(e.getHand());
        Item item = itemStack.getItem();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(item);
        if (block == null) {
            return;
        }

        // TODO: I got recommended to cache the BlockItems, as the creation may be expensive
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        ActionResultType result = blockItem.tryPlace(
                new BlockItemUseContext(
                        new ItemUseContext(e.getPlayer(), e.getHand(),
                                new BlockRayTraceResult(e.getPlayer().getLookVec(), e.getFace(), e.getPos(), false))));
        if (result == ActionResultType.SUCCESS) {
            if (e.getPlayer().isCreative()) {
                itemStack.grow(1);
            }
            e.setCanceled(true);
            e.setCancellationResult(ActionResultType.SUCCESS);
        }
    }

//    @SubscribeEvent
//    public void onRightItem(final PlayerInteractEvent.RightClickItem e) {
//        // Cancel the right click action when interacting with placeable items
//        if (!e.getPlayer().isSneaking()) { // TODO: #13 Make configurable hotkey for placing items
//            return; // Abort if the user is not sneaking
//        }
//
//        PlayerEntity player = e.getPlayer();
//        World worldIn = e.getWorld();
//
//        float f = player.rotationPitch;
//        float f1 = player.rotationYaw;
//        Vec3d vec3d = player.getEyePosition(1.0F);
//        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
//        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
//        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
//        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
//        float f6 = f3 * f4;
//        float f7 = f2 * f4;
//        double d0 = player.getAttribute(PlayerEntity.REACH_DISTANCE).getValue();;
//        Vec3d vec3d1 = vec3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
//        BlockRayTraceResult result = worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, player));
//        System.out.println(result);
//
////        e.getPlayer().
//        System.out.println(e.getFace());
//        ItemStack itemStack = e.getPlayer().getHeldItem(e.getHand());
//        Item item = itemStack.getItem();
//        e.getWorld().rayTraceBlocks(new RayTraceContext(e.getPlayer().getEyePosition(1), e, RayTraceContext.BlockMode blockModeIn, RayTraceContext.FluidMode fluidModeIn, Entity entityIn));
//        if (PlaceableItemsMap.instance().containsKey(item)) {
//            e.setCanceled(true);
//        }
//    }
}
