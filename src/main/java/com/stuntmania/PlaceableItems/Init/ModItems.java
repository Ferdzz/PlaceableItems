package com.stuntmania.PlaceableItems.Init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.stuntmania.PlaceableItems.PlaceableItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item blackBowl;
	public static Item redBowl;
	public static Item greenBowl;
	public static Item brownBowl;
	public static Item blueBowl;
	public static Item purpleBowl;
	public static Item cyanBowl;
	public static Item lightGrayBowl;
	public static Item grayBowl;
	public static Item pinkBowl;
	public static Item limeBowl;
	public static Item yellowBowl;
	public static Item lightBlueBowl;
	public static Item magentaBowl;
	public static Item orangeBowl;
	public static Item whiteBowl;
	
	public static void init() {
		
		blackBowl = new Item().setUnlocalizedName("blackBowl").setTextureName(PlaceableItems.MODID + ":blackBowl").setCreativeTab(CreativeTabs.tabDecorations);
		redBowl = new Item().setUnlocalizedName("redBowl").setTextureName(PlaceableItems.MODID + ":redBowl").setCreativeTab(CreativeTabs.tabDecorations);
		greenBowl = new Item().setUnlocalizedName("greenBowl").setTextureName(PlaceableItems.MODID + ":greenBowl").setCreativeTab(CreativeTabs.tabDecorations);
		brownBowl = new Item().setUnlocalizedName("brownBowl").setTextureName(PlaceableItems.MODID + ":brownBowl").setCreativeTab(CreativeTabs.tabDecorations);
		blueBowl = new Item().setUnlocalizedName("blueBowl").setTextureName(PlaceableItems.MODID + ":blueBowl").setCreativeTab(CreativeTabs.tabDecorations);
		purpleBowl = new Item().setUnlocalizedName("purpleBowl").setTextureName(PlaceableItems.MODID + ":purpleBowl").setCreativeTab(CreativeTabs.tabDecorations);
		cyanBowl = new Item().setUnlocalizedName("cyanBowl").setTextureName(PlaceableItems.MODID + ":cyanBowl").setCreativeTab(CreativeTabs.tabDecorations);
		lightGrayBowl = new Item().setUnlocalizedName("lightGrayBowl").setTextureName(PlaceableItems.MODID + ":lightGrayBowl").setCreativeTab(CreativeTabs.tabDecorations);
		grayBowl = new Item().setUnlocalizedName("grayBowl").setTextureName(PlaceableItems.MODID + ":grayBowl").setCreativeTab(CreativeTabs.tabDecorations);
		pinkBowl = new Item().setUnlocalizedName("pinkBowl").setTextureName(PlaceableItems.MODID + ":pinkBowl").setCreativeTab(CreativeTabs.tabDecorations);
		limeBowl = new Item().setUnlocalizedName("limeBowl").setTextureName(PlaceableItems.MODID + ":limeBowl").setCreativeTab(CreativeTabs.tabDecorations);
		yellowBowl = new Item().setUnlocalizedName("yellowBowl").setTextureName(PlaceableItems.MODID + ":yellowBowl").setCreativeTab(CreativeTabs.tabDecorations);
		lightBlueBowl = new Item().setUnlocalizedName("lightBlueBowl").setTextureName(PlaceableItems.MODID + ":lightBlueBowl").setCreativeTab(CreativeTabs.tabDecorations);
		magentaBowl = new Item().setUnlocalizedName("magentaBowl").setTextureName(PlaceableItems.MODID + ":magentaBowl").setCreativeTab(CreativeTabs.tabDecorations);
		orangeBowl = new Item().setUnlocalizedName("orangeBowl").setTextureName(PlaceableItems.MODID + ":orangeBowl").setCreativeTab(CreativeTabs.tabDecorations);
		whiteBowl = new Item().setUnlocalizedName("whiteBowl").setTextureName(PlaceableItems.MODID + ":whiteBowl").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerItem(blackBowl, "blackBowl");
		GameRegistry.registerItem(redBowl, "redBowl");
		GameRegistry.registerItem(greenBowl, "greenBowl");
		GameRegistry.registerItem(brownBowl, "brownBowl");
		GameRegistry.registerItem(blueBowl, "blueBowl");
		GameRegistry.registerItem(purpleBowl, "purpleBowl");
		GameRegistry.registerItem(cyanBowl, "cyanBowl");
		GameRegistry.registerItem(lightGrayBowl, "lightGrayBowl");
		GameRegistry.registerItem(grayBowl, "grayBowl");
		GameRegistry.registerItem(pinkBowl, "pinkBowl");
		GameRegistry.registerItem(limeBowl, "limeBowl");
		GameRegistry.registerItem(yellowBowl, "yellowBowl");
		GameRegistry.registerItem(lightBlueBowl, "lightBlueBowl");
		GameRegistry.registerItem(magentaBowl, "magentaBowl");
		GameRegistry.registerItem(orangeBowl, "orangeBowl");
		GameRegistry.registerItem(whiteBowl, "whiteBowl");
	}
}
