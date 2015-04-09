package com.stuntmania.placeableitems.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketPlaceHandler
{	
	@SubscribeEvent
	public void onSpillBucket(FillBucketEvent event)
	{
		if (!event.entityPlayer.isSneaking()) return;
		
		int slot = event.entityPlayer.inventory.currentItem;
		ItemStack held = event.entityPlayer.inventory.getStackInSlot(slot);
		if (held.getItem().equals(Items.water_bucket) || held.getItem().equals(Items.lava_bucket) || held.getItem().equals(Items.bucket))
			event.setCanceled(true);
	}
}
