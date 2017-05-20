package me.ferdz.placeableitems.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		GameRegistry.addRecipe(new ItemStack(ModItems.itemPlate), new Object[] {
				"S S",
				" S ",
				'S', new ItemStack(Blocks.STONE_SLAB, 1, 7)
		});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.itemSaddleStand), new Object[] {
				" W ",
				"WSW",
				"W W",
				'W', Blocks.PLANKS,
				'S', Items.STICK
		});
		
		GameRegistry.addRecipe(new ItemStack(ModItems.itemHorseArmorStand), new Object[] {
				"IWI",
				"WSW",
				"W W",
				'W', Blocks.PLANKS,
				'S', Items.STICK,
				'I', Items.IRON_INGOT
		});
	}
}
