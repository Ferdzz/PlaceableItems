package me.ferdz.placeableitems.wiki;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsBlockRegistry;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WikiGenerator {

    private static final String WIKI_PATH = "../../wiki/";
    private static final String WIKI_FILE_NAME = "data.json";

    private Class<PlaceableItemsBlockRegistry> registryClass;

    public WikiGenerator() {
        this.registryClass = PlaceableItemsBlockRegistry.class;
    }

    public void generate() {
        try {
            List<WikiItem> items = new ArrayList<>();
            List<Field> fields = Stream.of(this.registryClass.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(WikiDefinition.class))
                    .collect(Collectors.toList());
            for (Field field : fields) {
                field.setAccessible(true);
                WikiDefinition wikiAnnotation = field.getAnnotation(WikiDefinition.class);
                PlaceableItemsBlock block = (PlaceableItemsBlock) field.get(null);
                items.add(new WikiItem(block, wikiAnnotation));
            }
            // Create the folders if needed
            new File(WIKI_PATH).mkdirs();

            // Generate the JSON
            Gson gson = new Gson();
            Files.write(Paths.get(WIKI_PATH, WIKI_FILE_NAME), gson.toJson(items).getBytes(Charsets.UTF_8));
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
            // Should never happen
        }
    }
}
