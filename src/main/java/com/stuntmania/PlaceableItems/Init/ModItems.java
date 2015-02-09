package com.stuntmania.placeableitems.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.stuntmania.placeableitems.PlaceableItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item black_bowl;
	public static Item red_bowl;
	public static Item green_bowl;
	public static Item brown_bowl;
	public static Item blue_bowl;
	public static Item purple_bowl;
	public static Item cyan_bowl;
	public static Item light_gray_bowl;
	public static Item gray_bowl;
	public static Item pink_bowl;
	public static Item lime_bowl;
	public static Item yellow_bowl;
	public static Item light_blue_bowl;
	public static Item magenta_bowl;
	public static Item orange_bowl;
	public static Item white_bowl;
	
	public static void init() {
		
		black_bowl = new Item().setUnlocalizedName("blackBowl").setTextureName(PlaceableItems.MODID + ":black_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		red_bowl = new Item().setUnlocalizedName("redBowl").setTextureName(PlaceableItems.MODID + ":red_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		green_bowl = new Item().setUnlocalizedName("greenBowl").setTextureName(PlaceableItems.MODID + ":green_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		brown_bowl = new Item().setUnlocalizedName("brownBowl").setTextureName(PlaceableItems.MODID + ":brown_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		blue_bowl = new Item().setUnlocalizedName("blueBowl").setTextureName(PlaceableItems.MODID + ":blue_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		purple_bowl = new Item().setUnlocalizedName("purpleBowl").setTextureName(PlaceableItems.MODID + ":purple_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		cyan_bowl = new Item().setUnlocalizedName("cyanBowl").setTextureName(PlaceableItems.MODID + ":cyan_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		light_gray_bowl = new Item().setUnlocalizedName("lightGrayBowl").setTextureName(PlaceableItems.MODID + ":light_gray_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		gray_bowl = new Item().setUnlocalizedName("grayBowl").setTextureName(PlaceableItems.MODID + ":gray_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		pink_bowl = new Item().setUnlocalizedName("pinkBowl").setTextureName(PlaceableItems.MODID + ":pink_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		lime_bowl = new Item().setUnlocalizedName("limeBowl").setTextureName(PlaceableItems.MODID + ":lime_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		yellow_bowl = new Item().setUnlocalizedName("yellowBowl").setTextureName(PlaceableItems.MODID + ":yellow_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		light_blue_bowl = new Item().setUnlocalizedName("lightBlueBowl").setTextureName(PlaceableItems.MODID + ":light_blue_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		magenta_bowl = new Item().setUnlocalizedName("magentaBowl").setTextureName(PlaceableItems.MODID + ":magenta_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		orange_bowl = new Item().setUnlocalizedName("orangeBowl").setTextureName(PlaceableItems.MODID + ":orange_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		white_bowl = new Item().setUnlocalizedName("whiteBowl").setTextureName(PlaceableItems.MODID + ":white_bowl").setCreativeTab(CreativeTabs.tabDecorations);
		
		GameRegistry.registerItem(black_bowl, "blackBowl");
		GameRegistry.registerItem(red_bowl, "redBowl");
		GameRegistry.registerItem(green_bowl, "greenBowl");
		GameRegistry.registerItem(brown_bowl, "brownBowl");
		GameRegistry.registerItem(blue_bowl, "blueBowl");
		GameRegistry.registerItem(purple_bowl, "purpleBowl");
		GameRegistry.registerItem(cyan_bowl, "cyanBowl");
		GameRegistry.registerItem(light_gray_bowl, "lightGrayBowl");
		GameRegistry.registerItem(gray_bowl, "grayBowl");
		GameRegistry.registerItem(pink_bowl, "pinkBowl");
		GameRegistry.registerItem(lime_bowl, "limeBowl");
		GameRegistry.registerItem(yellow_bowl, "yellowBowl");
		GameRegistry.registerItem(light_blue_bowl, "lightBlueBowl");
		GameRegistry.registerItem(magenta_bowl, "magentaBowl");
		GameRegistry.registerItem(orange_bowl, "orangeBowl");
		GameRegistry.registerItem(white_bowl, "whiteBowl");
	}
}
