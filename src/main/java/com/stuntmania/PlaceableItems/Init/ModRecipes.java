package com.stuntmania.PlaceableItems.Init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.blackBowl), Items.bowl, new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.redBowl), Items.bowl, new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.greenBowl), Items.bowl, new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.brownBowl), Items.bowl, new ItemStack(Items.dye, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.blueBowl), Items.bowl, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.purpleBowl), Items.bowl, new ItemStack(Items.dye, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cyanBowl), Items.bowl, new ItemStack(Items.dye, 1, 6));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lightGrayBowl), Items.bowl, new ItemStack(Items.dye, 1, 7));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.grayBowl), Items.bowl, new ItemStack(Items.dye, 1, 8));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pinkBowl), Items.bowl, new ItemStack(Items.dye, 1, 9));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.limeBowl), Items.bowl, new ItemStack(Items.dye, 1, 10));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.yellowBowl), Items.bowl, new ItemStack(Items.dye, 1, 11));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lightBlueBowl), Items.bowl, new ItemStack(Items.dye, 1, 12));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.magentaBowl), Items.bowl, new ItemStack(Items.dye, 1, 13));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.orangeBowl), Items.bowl, new ItemStack(Items.dye, 1, 14));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.whiteBowl), Items.bowl, new ItemStack(Items.dye, 1, 15));
	}
}
