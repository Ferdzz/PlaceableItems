package me.ferdz.placeableitems.rendering;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.List;

/**
 * Custom ModelLoader implementation to allow for a custom BakedModel
 */
public class PlaceableItemsModelLoader implements IModelLoader {
    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        // Nothing to do
    }

    /**
     * Same implementation as VanillaProxy, but returns a custom ModelGeometry instead
     */
    @Override
    public IModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        List<BlockElement> list = this.getModelElements(deserializationContext, modelContents);
        return new PlaceableItemsModelGeometry(list);
    }

    private List<BlockElement> getModelElements(JsonDeserializationContext deserializationContext, JsonObject object) {
        List<BlockElement> list = Lists.newArrayList();
        if (object.has("elements")) {
            for(JsonElement jsonelement : GsonHelper.getAsJsonArray(object, "elements")) {
                list.add(deserializationContext.deserialize(jsonelement, BlockElement.class));
            }
        }

        return list;
    }
}
