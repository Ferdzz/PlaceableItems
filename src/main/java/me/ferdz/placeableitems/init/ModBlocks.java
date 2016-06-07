package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.BlockStick;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {

	public static Block blockStick;
	
	public static void init() {
		blockStick = new BlockStick("blockstick").setCreativeTab(CreativeTabs.COMBAT);
	}
}
