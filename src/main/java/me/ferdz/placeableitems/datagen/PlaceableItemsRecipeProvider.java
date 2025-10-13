package me.ferdz.placeableitems.datagen;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.init.PlaceableItemsItemsRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PlaceableItemsRecipeProvider extends RecipeProvider {

    public PlaceableItemsRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        Optional<? extends HolderLookup.RegistryLookup<Item>> lookup = registries.lookup(Registries.ITEM);
        if (lookup.isEmpty()) {
            return;
        }

        // TODO: Test
        ShapedRecipeBuilder.shaped(lookup.get(), RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.HORSE_ARMOR_STAND.get().asItem())
                .pattern("  w")
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output, "horse_armor_stand");

        ShapedRecipeBuilder.shaped(lookup.get(), RecipeCategory.DECORATIONS, PlaceableItemsItemsRegistry.SADDLE_STAND.get())
                .pattern("www")
                .pattern("s s")
                .define('w', ItemTags.PLANKS)
                .define('s', Items.STICK)
                .unlockedBy("has_stick", has(Items.STICK))
                .save(output, "saddle_stand");
    }

    // The runner class, this should be added to the DataGenerator as a DataProvider
    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new PlaceableItemsRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Placeable Items Recipes";
        }
    }
}
