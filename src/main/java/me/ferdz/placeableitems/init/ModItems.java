package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static Item itemPlate;
	public static Item itemSaddleStand;
	public static Item itemHorseArmorStand;
	
	public static void init() {
		itemPlate = new Item().setRegistryName(PlaceableItems.MODID, "item_plate").setUnlocalizedName("item_plate").setCreativeTab(CreativeTabs.DECORATIONS);
		itemSaddleStand = new ItemBlock(ModBlocks.blockSaddleStand).setRegistryName(PlaceableItems.MODID, "item_saddle_stand").setUnlocalizedName("item_saddle_stand").setCreativeTab(CreativeTabs.DECORATIONS);
		itemHorseArmorStand = new ItemBlock(ModBlocks.blockHorseArmorStand).setRegistryName(PlaceableItems.MODID, "item_horse_armor_stand").setUnlocalizedName("item_horse_armor_stand").setCreativeTab(CreativeTabs.DECORATIONS);
		
		GameRegistry.register(itemPlate);
		GameRegistry.register(itemSaddleStand);
		GameRegistry.register(itemHorseArmorStand);
	}
}
