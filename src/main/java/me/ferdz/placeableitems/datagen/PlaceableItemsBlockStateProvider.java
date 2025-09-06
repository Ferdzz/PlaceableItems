package me.ferdz.placeableitems.datagen;

import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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

            if (block.getComponents().stream().anyMatch(component -> component instanceof BiPositionBlockComponent)) {
                buildBiPositionVariant(strippedItemId, block);
            } else {
                buildSimpleVariant(strippedItemId, block);
            }
        });
    }

    /// Builds a simple blockstate variant, containing a single model and no states
    private void buildSimpleVariant(String itemId, Block block) {
        ModelFile model = models().getExistingFile(modLoc(itemId));
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        // Build BlockState JSON
        VariantBlockStateBuilder.PartialBlockstate partialState = variantBuilder.partialState();
        variantBuilder.addModels(partialState,
                partialState.modelForState()
                        .modelFile(model)
                        .build()
        );
    }

    /// Builds a blockstate with two variant models, one for each position (up/down)
    private void buildBiPositionVariant(String itemId, Block block) {
        ModelFile downModel = models().getExistingFile(modLoc(itemId + "_down"));
        ModelFile upModel = models().getExistingFile(modLoc(itemId + "_up"));

        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        // Build BlockState JSON
        variantBuilder.partialState()
                .with(BlockStateProperties.UP, false)
                .modelForState()
                .modelFile(downModel)
                .addModel();
        variantBuilder.partialState()
                .with(BlockStateProperties.UP, true)
                .modelForState()
                .modelFile(upModel)
                .addModel();
    }
}
