package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumArrowType implements IStringSerializable {
    FIRE_RESISTANCE(0, "fire_resistance"),
    HEALING(1, "healing"),
    INVISIBILITY(2, "invisibility"),
    LEAPING(3, "leaping"),
    NIGHT_VISION(4, "night_vision"),
    POISON(5, "poison"),
    REGENERATION(6, "regeneration"),
    SLOWNESS(7, "slowness"),
    STRENGTH(8, "strength"),
    SWIFTNESS(9, "swiftness"),
    WATER_BREATHING(10, "water_breathing"),
    WEAKNESS(11, "weakness"),
    NORMAL(12, "normal"),
    SPECTRAL(13, "spectral"),
    HARMING(14, "harming"),
    TIPPED(15, "tipped");

    private int ID;
    private String name;
    
    private EnumArrowType(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}