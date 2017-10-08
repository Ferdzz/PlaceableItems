package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumCooked implements IStringSerializable {
    RAW(0, "raw"),
    COOKED(1, "cooked");
	
    private int ID;
    private String name;
    
    private EnumCooked(int ID, String name) {
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