package me.ferdz.placeableitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

@Mixin(ItemStack.class)
public abstract class OnItemPlaceMxn
{
    @Inject(at = @At("HEAD"),method="useOnBlock",cancellable = true)
    public void placeableItemInteractBlock(ItemUsageContext iuc, CallbackInfoReturnable<ActionResult> ci)
    {
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(iuc.getStack().getItem());
        if(iuc.getPlayer().isSneaking() && block != null)
        {
            BlockItem placeableBlockitem = new BlockItem(block, new Item.Settings());
            ActionResult result = placeableBlockitem.place(new ItemPlacementContext(iuc));
            ci.setReturnValue(result);
            if(result == ActionResult.SUCCESS && iuc.getPlayer().isCreative())
            {
                iuc.getStack().increment(1);
            }
            ci.cancel();
        }
        
    }

    //PlayerEntity
    //ServerPlayerInteractionManager
}