package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumPotionType implements IStringSerializable {
    FIRE_RESISTANCE(0, "fire_resistance"),
    HARMING(1, "harming"),
    HEALING(2, "healing"),
    INVISIBILITY(3, "invisibility"),
    LEAPING(4, "leaping"),
    LUCK(5, "luck"),
    NIGHT_VISION(6, "night_vision"),
    POISON(7, "poison"),
    REGENERATION(8, "regeneration"),
    SLOWNESS(9, "slowness"),
    STRENGTH(10, "strength"),
    SWIFTNESS(11, "swiftness"),
    WATER_BREATHING(12, "water_breathing"),
    WEAKNESS(13, "weakness"),
    NORMAL(14, "normal");
	
    private int ID;
    private String name;
    
    private EnumPotionType(int ID, String name) {
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