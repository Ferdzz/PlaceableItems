package com.stuntmania.placeableitems.handler;

import net.minecraft.block.Block;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.stuntmania.placeableitems.block.BlockPlaceableItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {
	
	@SubscribeEvent
	public void breakBlock(BreakEvent event) {
		Block block = event.world.getBlock(event.x, event.y + 1, event.z);
		if (block instanceof BlockPlaceableItems) {
			block.dropBlockAsItem(event.world, event.x, event.y + 1, event.z, event.world.getBlockMetadata(event.x, event.y + 1, event.z), 0);
			event.world.setBlockToAir(event.x, event.y + 1, event.z);
		}
	}
}
