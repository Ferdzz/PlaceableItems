package com.stuntmania.placeableitems.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.black_bowl), Items.bowl, new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.red_bowl), Items.bowl, new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.green_bowl), Items.bowl, new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.brown_bowl), Items.bowl, new ItemStack(Items.dye, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.blue_bowl), Items.bowl, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.purple_bowl), Items.bowl, new ItemStack(Items.dye, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cyan_bowl), Items.bowl, new ItemStack(Items.dye, 1, 6));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.light_gray_bowl), Items.bowl, new ItemStack(Items.dye, 1, 7));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.gray_bowl), Items.bowl, new ItemStack(Items.dye, 1, 8));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pink_bowl), Items.bowl, new ItemStack(Items.dye, 1, 9));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lime_bowl), Items.bowl, new ItemStack(Items.dye, 1, 10));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.yellow_bowl), Items.bowl, new ItemStack(Items.dye, 1, 11));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.light_blue_bowl), Items.bowl, new ItemStack(Items.dye, 1, 12));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.magenta_bowl), Items.bowl, new ItemStack(Items.dye, 1, 13));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.orange_bowl), Items.bowl, new ItemStack(Items.dye, 1, 14));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.white_bowl), Items.bowl, new ItemStack(Items.dye, 1, 15));
	}
}
