package me.ferdz.placeableitems.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void init() {
		GameRegistry.addRecipe(new ItemStack(ModItems.plate), new Object[] {
				"S S",
				" S ",
				'S', new ItemStack(Blocks.STONE_SLAB, 1, 7)
		});
	}
}
