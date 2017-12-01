package me.ferdz.placeableitems.event;

import java.util.Arrays;
import java.util.List;

import me.ferdz.placeableitems.ai.EntityAIEat;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityJoinHandler{

	public static final Block[]
			CHICKEN_BLOCKS = new Block[] {ModBlocks.blockBeetrootSeeds},
			RABBIT_BLOCKS = new Block[]{ModBlocks.blockCarrot},
			VILLAGER_BLOCKS = new Block[]{ModBlocks.blockEmerald},
			WOLF_BLOCKS = new Block[]{ModBlocks.blockBone},
			HORSE_BLOCKS = new Block[] {ModBlocks.blockApple, ModBlocks.blockGoldenApple, ModBlocks.blockCarrot, ModBlocks.blockWheat},
			COW_BLOCKS = new Block[] {ModBlocks.blockWheat},
			PIG_BLOCKS = new Block[] {ModBlocks.blockWheat, ModBlocks.blockCarrotOnStick, ModBlocks.blockCarrot},
			MOOSHROOM_BLOCKS = new Block[] {ModBlocks.blockWheat},
			SHEEP_BLOCKS = new Block[] {ModBlocks.blockWheat},
			OCELOT_BLOCKS = new Block[] {ModBlocks.blockFish, ModBlocks.blockSalmon, ModBlocks.blockClownfish, ModBlocks.blockPufferfish};
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		if(!(e.getEntity() instanceof EntityCreature))
			return;
		
		EntityCreature entity = (EntityCreature) e.getEntity();
		if(entity instanceof EntityChicken) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.0D,  CHICKEN_BLOCKS));
		} else if (entity instanceof EntityRabbit) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.0D, RABBIT_BLOCKS));
		} else if (entity instanceof EntityVillager) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 0.6D, VILLAGER_BLOCKS));
		} else if (entity instanceof EntityWolf) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.0D, WOLF_BLOCKS));
		} else if (entity instanceof EntityHorse) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.25D, HORSE_BLOCKS));
		} else if (entity instanceof EntityMooshroom) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.25D, MOOSHROOM_BLOCKS));
		} else if (entity instanceof EntityCow) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.25D, COW_BLOCKS));
		} else if (entity instanceof EntityPig) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.2D, PIG_BLOCKS));
		} else if (entity instanceof EntitySheep) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 1.1D, SHEEP_BLOCKS));
		} else if (entity instanceof EntityOcelot) {
			entity.tasks.addTask(3, new EntityAIEat(entity, 0.6D, OCELOT_BLOCKS));
		}
	}
}
