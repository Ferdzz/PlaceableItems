package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumHorseArmor implements IStringSerializable {
    GOLD(0, "gold"),
    IRON(1, "iron"),
    DIAMOND(2, "diamond"),
    EMPTY(4, "empty");
	
    private int ID;
    private String name;
    
    private EnumHorseArmor(int ID, String name) {
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