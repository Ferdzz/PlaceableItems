package me.ferdz.placeableitems.init;

import java.util.HashMap;

import me.ferdz.placeableitems.block.BlockEmptyBucket;
import me.ferdz.placeableitems.block.BlockFilledBucket;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModBlocks {

	public static HashMap<Item, Block> blockMap;
	
	public static Block blockLavaBucket, blockEmptyBucket;
	
	public static void init() {
		blockMap = new HashMap<Item, Block>();
		
		blockLavaBucket = new BlockFilledBucket("block_filled_bucket").setItem(Items.LAVA_BUCKET).setItem(Items.WATER_BUCKET).setItem(Items.MILK_BUCKET);
		blockEmptyBucket = new BlockEmptyBucket("block_empty_bucket").setItem(Items.BUCKET);
	}
}
