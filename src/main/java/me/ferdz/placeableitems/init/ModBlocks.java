package me.ferdz.placeableitems.init;

import java.util.HashMap;

import me.ferdz.placeableitems.block.BlockApple;
import me.ferdz.placeableitems.block.BlockCarrot;
import me.ferdz.placeableitems.block.BlockEmptyBucket;
import me.ferdz.placeableitems.block.BlockFilledBucket;
import me.ferdz.placeableitems.block.BlockGoldenApple;
import me.ferdz.placeableitems.block.BlockPlaceableItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModBlocks {

	public static HashMap<Item, Block> blockMap;
	
	public static Block blockLavaBucket, blockWaterBucket, blockMilkBucket, blockEmptyBucket;
	public static Block blockBook;
	public static Block blockBowl;
	public static Block blockBread;
	public static Block blockCarrot;
	public static Block blockApple, blockGoldenApple;
	
	public static void init() {
		blockMap = new HashMap<Item, Block>();
		
		blockLavaBucket = new BlockFilledBucket("block_lava_bucket").setBucketItem(Items.LAVA_BUCKET).setItem(Items.LAVA_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockWaterBucket = new BlockFilledBucket("block_water_bucket").setBucketItem(Items.WATER_BUCKET).setItem(Items.WATER_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockMilkBucket = new BlockFilledBucket("block_milk_bucket").setBucketItem(Items.MILK_BUCKET).setItem(Items.MILK_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockEmptyBucket = new BlockEmptyBucket("block_empty_bucket").setItem(Items.BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockBook = new BlockPlaceableItems(Material.WOOD, "block_book").setItem(Items.BOOK).setBoundingBox(2, 0, 2, 14, 3, 14);
		blockBowl = new BlockPlaceableItems(Material.WOOD, "block_bowl").setItem(Items.BOWL).setBoundingBox(4, 0, 4, 12, 3, 12);
		blockBread = new BlockPlaceableItems(Material.WOOD, "block_bread").setItem(Items.BREAD).setBoundingBox(2, 0, 2, 14, 5, 14);
		blockCarrot = new BlockCarrot(Material.WOOD, "block_carrot").setItem(Items.CARROT).setItem(Items.GOLDEN_CARROT).setBoundingBox(2, 0, 2, 14, 5, 14);
		blockApple = new BlockApple("block_apple").setItem(Items.APPLE);
		blockGoldenApple = new BlockGoldenApple("block_apple_golden").setItem(Items.GOLDEN_APPLE);
	}
}
