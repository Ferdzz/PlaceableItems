package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.BlockLavaBucket;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {

	public static Block blockLavaBucket;
	
	public static void init() {
		blockLavaBucket = new BlockLavaBucket("block_lava_bucket").setCreativeTab(CreativeTabs.COMBAT);
	}
}
