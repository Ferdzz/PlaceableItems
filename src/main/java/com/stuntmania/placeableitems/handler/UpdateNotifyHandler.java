package com.stuntmania.placeableitems.handler;

import com.stuntmania.placeableitems.PlaceableItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class UpdateNotifyHandler {
	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if (!PlaceableItems.warned && event.player.worldObj.isRemote && !PlaceableItems.updateChecker.upToDate()) {
			ClickEvent updateUrlChatClick = new ClickEvent(Action.OPEN_URL, "http://minecraft.curseforge.com/mc-mods/227951-placeableitems");
			ChatStyle updateUrlChatStyle = new ChatStyle().setChatClickEvent(updateUrlChatClick);
			
			ChatComponentText warn = new ChatComponentText(EnumChatFormatting.AQUA + "Placeable Items Mod is out of date! Click here to update.");
			warn.setChatStyle(updateUrlChatStyle);
			event.player.addChatMessage(warn);
			PlaceableItems.warned = true;
		}
	}
}
