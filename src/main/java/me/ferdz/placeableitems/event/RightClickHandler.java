package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RightClickHandler {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onItemRightClick(RightClickBlock e) {
		if(e.getEntityPlayer().isSneaking() && e.getFace() != null && e.getHand() == EnumHand.MAIN_HAND && e.getItemStack() != null) {
			for (Item item : ModBlocks.blockMap.keySet()) {
				if(e.getItemStack().getItem().equals(item)) {
					e.getWorld().setBlockState(e.getPos().offset(e.getFace()), ModBlocks.blockMap.get(item).onBlockPlaced(e.getWorld(), null, e.getFace(), 0, 0, 0, 0, e.getEntityLiving()));
					e.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onBucketRightClick(FillBucketEvent e) {
		if (e.getEntityPlayer().isSneaking()) {
			if (e.getTarget().typeOfHit == Type.BLOCK)
				e.setCanceled(true);
		}
	}
}
