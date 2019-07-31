package me.ferdz.placeableitems.material;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public class PlaceableItemsMaterials {
    public static final Material PLACEABLE_ITEMS_MATERIAL =
            new Material(MaterialColor.CYAN,
                    false,
                    false,
                    false,
                    false,
                    true,
                    false,
                    false,
                    PushReaction.DESTROY);
}
