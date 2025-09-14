package me.ferdz.placeableitems.datagen;

import com.mojang.datafixers.kinds.IdF;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.block.component.impl.MultiModelBlockComponent;
import me.ferdz.placeableitems.block.component.impl.MusicDiscBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Optional;

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

            Optional<BiPositionBlockComponent> biPosition = block.getComponents().stream()
                    .filter(component -> component instanceof BiPositionBlockComponent)
                    .map(component -> (BiPositionBlockComponent) component)
                    .findFirst();

            Optional<MultiModelBlockComponent> multiModel = block.getComponents().stream()
                    .filter(component -> component instanceof MultiModelBlockComponent)
                    .map(component -> (MultiModelBlockComponent) component)
                    .findFirst();

            Optional<MusicDiscBlockComponent> musicDisc = block.getComponents().stream()
                    .filter(component -> component instanceof MusicDiscBlockComponent)
                    .map(component -> (MusicDiscBlockComponent) component)
                    .findFirst();

            if (biPosition.isPresent()) {
                buildBiPositionVariant(strippedItemId, block);
            } else if (multiModel.isPresent()) {
                buildMultiModelVariant(strippedItemId, block, multiModel.get());
            } else if (musicDisc.isPresent()) {
                return;
               // buildMusicDiscModelVariant(strippedItemId, block, musicDisc.get());
            } else {
                buildSimpleVariant(strippedItemId, block);
            }
        });

        // Build variants for disc outside of main loop as it's one block associated with multiple items
        buildMusicDiscModelVariant(PlaceableItemsBlockRegistry.MUSIC_DISC.get());
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

    /// Builds a blockstate with an indeterminate amount of variant models. Models are held in a subfolder with the same
    /// name as the item
    private void buildMultiModelVariant(String itemId, Block block, MultiModelBlockComponent component) {
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        int modelCount = component.model.getPossibleValues().stream().mapToInt(Integer::intValue).max().getAsInt();
        // Build BlockState JSON

        for (int i = 0; i <= modelCount; i++) {
            variantBuilder.partialState()
                    .with(component.model, i)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/" + itemId + "/" + itemId + "_" + i)))
                    .addModel();
        }
    }

    /// Builds a blockstate for music discs. Models are held in a subfolder "disc"
    private void buildMusicDiscModelVariant(Block block) {
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        for (MusicDiscBlockComponent.MusicDiscType discType : MusicDiscBlockComponent.MusicDiscType.values()) {
            variantBuilder.partialState()
                    .with(MusicDiscBlockComponent.DISC_TYPE, discType)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/disc/" + discType.getSerializedName())))
                    .addModel();
        }
    }
}
