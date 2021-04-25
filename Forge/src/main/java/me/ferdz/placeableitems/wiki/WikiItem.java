package me.ferdz.placeableitems.wiki;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import org.apache.commons.lang3.StringUtils;

public class WikiItem {
    public class Texture {
        public final String name;
        public final String texture;

        public Texture(String name, String texture) {
            this.name = name;
            this.texture = texture;
        }
    }

    public final String itemName;
    public final String description;
    public final String modelPath;
    public final Texture[] textures;

    public WikiItem(PlaceableItemsBlock block, WikiDefinition wiki) {
        // Set the item name
        if (wiki.name().isEmpty()) {
            this.itemName = block.asItem().getDescription().getColoredString();
        } else {
            this.itemName = StringUtils.stripToNull(wiki.name());
        }

        // Set the description
        String description = wiki.description();
        StringBuilder componentsDescription = new StringBuilder();
        for (IBlockComponent component : block.getComponents()) {
            WikiBlockComponentDefinition annotation = component.getClass().getAnnotation(WikiBlockComponentDefinition.class);
            if (annotation == null) {
                continue;
            }
            componentsDescription.append(annotation.description()).append("\n");
        }
        if (componentsDescription.length() > 0) {
            description += "\n" + componentsDescription.toString();
        }
        this.description = StringUtils.stripToNull(description);

        // Set the model path
        if (wiki.model().isEmpty()) {
            this.modelPath = "models/block/" + block.asItem().getRegistryName().getPath() + ".json";
        } else {
            this.modelPath = "models/block/" + wiki.model() + ".json";
        }

        // Set the textures arrays
        if (wiki.textures().length > 0) {
            this.textures = new Texture[wiki.textures().length];
            WikiDefinition.Texture[] wikiTextures = wiki.textures();
            for (int i = 0; i < wikiTextures.length; i++) {
                WikiDefinition.Texture wikiTexture = wikiTextures[i];
                this.textures[i] = new Texture(wikiTexture.name(), "textures/block/" +  wikiTexture.texture() + ".png");
            }
        } else {
            this.textures = new Texture[] {
                    new Texture(block.asItem().getRegistryName().getPath(),"textures/block/" + block.asItem().getRegistryName().getPath() + ".png")
            };
        }
    }
}