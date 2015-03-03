package com.stuntmania.placeableitems.handler;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.stuntmania.placeableitems.init.ModBlocks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BlockDropsHandler
{
	@SubscribeEvent
	public void onBlockBreak(BreakEvent event) {
		if (event.block == ModBlocks.ingot) {
			switch (event.blockMetadata) {
			default:
			{
				break;
			}
			case 0:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 1)));
				break;
			}
			case 1:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 1)));
				break;
			}
			case 2:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 2)));
				break;
			}
			case 3:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 2)));
				break;
			}
			case 4:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 3)));
				break;
			}
			case 5:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 3)));
				break;
			}
			case 6:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 4)));
				break;
			}
			case 7:
			{
				event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 4)));
				break;
			}
			}
		}
	}
}
