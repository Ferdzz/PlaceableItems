package me.ferdz.placeableitems.datagen;

import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class PlaceableItemsBlockStateProvider extends BlockStateProvider {
    public PlaceableItemsBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        PlaceableItemsMap.instance().forEach((item, block) -> {
            // .toString returns an id string prefixed with "minecraft:", eg "minecraft:coal". Remove the prefix to
            // match our naming scheme
            String strippedItemId = item.toString().split(":")[1];
            ModelFile model = models().getExistingFile(modLoc(strippedItemId));

            VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
            // Build BlockState JSON
            VariantBlockStateBuilder.PartialBlockstate partialState = variantBuilder.partialState();
            variantBuilder.addModels(partialState,
                    partialState.modelForState()
                            .modelFile(model)
                            .build()
            );
        });
    }
}
