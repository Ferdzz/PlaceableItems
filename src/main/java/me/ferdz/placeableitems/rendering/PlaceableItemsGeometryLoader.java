package me.ferdz.placeableitems.rendering;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.geometry.IGeometryLoader;

public class PlaceableItemsGeometryLoader implements IGeometryLoader<PlaceableItemsGeometry> {
    public static final PlaceableItemsGeometryLoader INSTANCE = new PlaceableItemsGeometryLoader();
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(PlaceableItems.MODID, "rotation");

    private PlaceableItemsGeometryLoader() {}

    @Override
    public PlaceableItemsGeometry read(JsonObject jsonObject, JsonDeserializationContext context) throws JsonParseException {
        // Trick the deserializer into thinking this is a normal model by removing the loader field and then passing it back into the deserializer.
        jsonObject.remove("loader");
        BlockModel base = context.deserialize(jsonObject, BlockModel.class);
        // other stuff here if needed
        return new PlaceableItemsGeometry(base);
    }
}
