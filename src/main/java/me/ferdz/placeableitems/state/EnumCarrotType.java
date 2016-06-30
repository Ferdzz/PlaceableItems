package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumCarrotType implements IStringSerializable {
    NORMAL(0, "normal"),
    GOLDEN(1, "golden");
    
    private int ID;
    private String name;
    
    private EnumCarrotType(int ID, String name) {
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