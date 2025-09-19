package me.ferdz.placeableitems.datagen;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PlaceableItemsRecipeProvider extends RecipeProvider {

    public PlaceableItemsRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.HORSE_ARMOR_STAND.get())
                .pattern("  w")
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, "horse_armor_stand");

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.SADDLE_STAND.get())
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput, "saddle_stand");
    }

}
