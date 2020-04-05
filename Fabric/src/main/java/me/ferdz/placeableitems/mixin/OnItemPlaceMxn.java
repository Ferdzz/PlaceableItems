package me.ferdz.placeableitems.mixin;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class OnItemPlaceMxn {

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void placeableItemInteractBlock(ItemUsageContext itemUsageContext, CallbackInfoReturnable<ActionResult> callback) {
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(itemUsageContext.getStack().getItem());
        if (PlaceableItems.isKeyPressed() && block != null) {
            ActionResult result = block.getBlockItem().place(new ItemPlacementContext(itemUsageContext));
            callback.setReturnValue(result);
            if (result == ActionResult.SUCCESS && itemUsageContext.getPlayer().isCreative()) {
                itemUsageContext.getStack().increment(1);
            }
            callback.cancel();
        }
    }
}