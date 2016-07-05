package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.ai.EntityAIAttractBlock;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityJoinHandler {

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		if(!(e.getEntity() instanceof EntityLiving))
			return;
		
		EntityLiving entity = (EntityLiving) e.getEntity();
		if(entity instanceof EntityChicken) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockBeetrootSeeds));
		} else if (entity instanceof EntityPig || entity instanceof EntityRabbit) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockCarrot));
		} else if (entity instanceof EntityVillager) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockEmerald));
		} else if (entity instanceof EntityWolf) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockBone));
		} else if (entity instanceof EntityHorse) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockApple, ModBlocks.blockGoldenApple, ModBlocks.blockCarrot, ModBlocks.blockWheat));
		} else if (entity instanceof EntityCow) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockWheat));
		} else if (entity instanceof EntityPig) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockWheat));
		} else if (entity instanceof EntityMooshroom) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockWheat));
		} else if (entity instanceof EntitySheep) {
			entity.tasks.addTask(2, new EntityAIAttractBlock(entity, ModBlocks.blockWheat));
		}
	}
}
