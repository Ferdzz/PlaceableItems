package me.ferdz.placeableitems.integration;

import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class IntegrationHarvestcraft { // This is an experimental class for pam's harvestcraft compat
	public static void registerBlocks() { 
		ModBlocks.blockMap.remove(Items.APPLE);
		ModBlocks.blockMap.remove(Items.WHEAT);
		ModBlocks.blockMap.remove(Items.POTATO);
		ModBlocks.blockMap.remove(Items.CARROT);
		ModBlocks.blockMap.remove(Items.BEETROOT);
		ModBlocks.blockMap.remove(Items.MELON);
		ModBlocks.blockMap.remove(Items.BEEF);
		ModBlocks.blockMap.remove(Items.CHICKEN);
		ModBlocks.blockMap.remove(Items.PORKCHOP);
		ModBlocks.blockMap.remove(Items.RABBIT);
		ModBlocks.blockMap.remove(Items.MUTTON);
		ModBlocks.blockMap.remove(Items.FISH);
		ModBlocks.blockMap.remove(Items.MUSHROOM_STEW);
		ModBlocks.blockMap.remove(Items.BREAD);
		ModBlocks.blockMap.remove(Items.COOKIE);
		ModBlocks.blockMap.remove(Items.PUMPKIN_PIE);
		ModBlocks.blockMap.remove(Items.RABBIT_STEW);
		ModBlocks.blockMap.remove(Items.BEETROOT_SOUP);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:apple"), (BlockPlaceableItems) ModBlocks.blockApple);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:wheat"), (BlockPlaceableItems) ModBlocks.blockWheat);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:potato"), (BlockPlaceableItems) ModBlocks.blockPotato);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:carrot"), (BlockPlaceableItems) ModBlocks.blockCarrot);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:beetroot"), (BlockPlaceableItems) ModBlocks.blockBeetroot);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:melon"), (BlockPlaceableItems) ModBlocks.blockMelon);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:beef"), (BlockPlaceableItems) ModBlocks.blockBeef);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:chicken"), (BlockPlaceableItems) ModBlocks.blockChicken);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:porkchop"), (BlockPlaceableItems) ModBlocks.blockPorkchop);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:rabbit"), (BlockPlaceableItems) ModBlocks.blockRabbit);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:mutton"), (BlockPlaceableItems) ModBlocks.blockMutton);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:cod"), (BlockPlaceableItems) ModBlocks.blockFish);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:salmon"), (BlockPlaceableItems) ModBlocks.blockSalmon);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:clownfish"), (BlockPlaceableItems) ModBlocks.blockClownfish);
		//ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:pufferfish"), (BlockPlaceableItems) ModBlocks.blockPufferfish);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:mushroom_stew"), (BlockPlaceableItems) ModBlocks.blockMushroomStew);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:bread"), (BlockPlaceableItems) ModBlocks.blockBread);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:cookie"), (BlockPlaceableItems) ModBlocks.blockCookie);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:pumpkin_pie"), (BlockPlaceableItems) ModBlocks.blockPumkinPie);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:rabbit_stew"), (BlockPlaceableItems) ModBlocks.blockRabbitStew);
		ModBlocks.blockMap.put(Item.getByNameOrId("minecraft:beetroot_soup"), (BlockPlaceableItems) ModBlocks.blockBeetrootSoup);
	}
}
