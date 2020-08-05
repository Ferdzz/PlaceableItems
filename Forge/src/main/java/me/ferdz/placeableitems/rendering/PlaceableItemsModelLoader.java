package me.ferdz.placeableitems.rendering;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.renderer.model.BlockPart;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.geometry.IModelGeometry;

import java.util.List;

/**
 * Custom ModelLoader implementation to allow for a custom BakedModel
 */
public class PlaceableItemsModelLoader implements IModelLoader {
    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        // Nothing to do
    }

    /**
     * Same implementation as VanillaProxy, but returns a custom ModelGeometry instead
     */
    @Override
    public IModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        List<BlockPart> list = this.getModelElements(deserializationContext, modelContents);
        return new PlaceableItemsModelGeometry(list);
    }

    private List<BlockPart> getModelElements(JsonDeserializationContext deserializationContext, JsonObject object) {
        List<BlockPart> list = Lists.newArrayList();
        if (object.has("elements")) {
            for(JsonElement jsonelement : JSONUtils.getJsonArray(object, "elements")) {
                list.add(deserializationContext.deserialize(jsonelement, BlockPart.class));
            }
        }

        return list;
    }
}
