package me.ferdz.placeableitems.datagen;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class PlaceableItemsRecipeProvider extends RecipeProvider {

    public PlaceableItemsRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.HORSE_ARMOR_STAND.get())
                .pattern("  w")
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer, PlaceableItems.MODID + ":horse_armor_stand");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.SADDLE_STAND.get())
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer, PlaceableItems.MODID + ":saddle_stand");
    }
}
