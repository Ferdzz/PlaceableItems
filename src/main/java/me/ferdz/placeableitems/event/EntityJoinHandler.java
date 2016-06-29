package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.ai.ChickenAIAttractBlock;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityJoinHandler {

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		if(e.getEntity() instanceof EntityChicken) {
			EntityChicken c = (EntityChicken)e.getEntity();
			c.tasks.addTask(1, new ChickenAIAttractBlock(c));
		}
	}
}
