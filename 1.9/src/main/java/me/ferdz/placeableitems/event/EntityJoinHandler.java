package me.ferdz.placeableitems.event;

import java.util.Arrays;
import java.util.List;

import me.ferdz.placeableitems.ai.EntityAIAttractBlock;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
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

public class EntityJoinHandler {

	public static final List<Block> 
			CHICKEN_BLOCKS = Arrays.asList(ModBlocks.blockBeetrootSeeds),
			RABBIT_BLOCKS = Arrays.asList(ModBlocks.blockCarrot),
			VILLAGER_BLOCKS = Arrays.asList(ModBlocks.blockEmerald),
			WOLF_BLOCKS = Arrays.asList(ModBlocks.blockBone),
			HORSE_BLOCKS = Arrays.asList(ModBlocks.blockApple, ModBlocks.blockGoldenApple, ModBlocks.blockCarrot, ModBlocks.blockWheat),
			COW_BLOCKS = Arrays.asList(ModBlocks.blockWheat),
			PIG_BLOCKS = Arrays.asList(ModBlocks.blockWheat, ModBlocks.blockCarrotOnStick, ModBlocks.blockCarrot),
			MOOSHROOM_BLOCKS = Arrays.asList(ModBlocks.blockWheat),
			SHEEP_BLOCKS = Arrays.asList(ModBlocks.blockWheat),
			OCELOT_BLOCKS = Arrays.asList((Block)ModBlocks.blockFish, ModBlocks.blockSalmon, ModBlocks.blockClownfish, ModBlocks.blockPufferfish);
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		if(e.getWorld().isRemote)
			return;
		
		// Commented out while waiting for a configurable way of deactivating this message
//		if(e.getEntity() instanceof EntityPlayerMP) { // message people about the patreon every 10 loggin
//			EntityPlayerMP entity = (EntityPlayerMP) e.getEntity();
//			int amountLeft = entity.getStatFile().readStat(StatList.LEAVE_GAME);
//			System.out.println(amountLeft);
//			boolean shouldMessage = amountLeft != 0 && amountLeft % 10 == 0;
//			if(shouldMessage) {
//				ClickEvent clickEvent = new ClickEvent(Action.OPEN_URL, "https://www.patreon.com/ferdz");
//				TextComponentString text = new TextComponentString(
//						"Enjoying " + TextFormatting.GOLD + "Placeable Items" + TextFormatting.RESET + "? Please consider supporting the development at " +
//						TextFormatting.LIGHT_PURPLE + TextFormatting.UNDERLINE + "https://www.patreon.com/ferdz");
//				text.getStyle().setClickEvent(clickEvent);
//				entity.addChatMessage(text);
//			}
//		}
		
		if(!(e.getEntity() instanceof EntityLiving))
			return;
		
		EntityLiving entity = (EntityLiving) e.getEntity();
		if(entity instanceof EntityChicken) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, CHICKEN_BLOCKS));
		} else if (entity instanceof EntityRabbit) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, RABBIT_BLOCKS));
		} else if (entity instanceof EntityVillager) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, VILLAGER_BLOCKS));
		} else if (entity instanceof EntityWolf) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, WOLF_BLOCKS));
		} else if (entity instanceof EntityHorse) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, HORSE_BLOCKS));
		} else if (entity instanceof EntityCow) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, COW_BLOCKS));
		} else if (entity instanceof EntityPig) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, PIG_BLOCKS));
		} else if (entity instanceof EntityMooshroom) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, MOOSHROOM_BLOCKS));
		} else if (entity instanceof EntitySheep) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, SHEEP_BLOCKS));
		} else if (entity instanceof EntityOcelot) {
			entity.tasks.addTask(3, new EntityAIAttractBlock(entity, OCELOT_BLOCKS));
		}
	}
}
