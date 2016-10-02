package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static Item plate;
	
	public static void init() {
		plate = new Item().setRegistryName(PlaceableItems.MODID, "item_plate").setUnlocalizedName("item_plate").setCreativeTab(CreativeTabs.DECORATIONS);
		
		GameRegistry.register(plate);
	}
}
