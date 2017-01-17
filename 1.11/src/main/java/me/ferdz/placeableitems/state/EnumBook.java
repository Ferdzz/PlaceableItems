package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumBook implements IStringSerializable {
    EMPTY(0, "empty"),
    WRITTEN(1, "written");
	
    private int ID;
    private String name;
    
    private EnumBook(int ID, String name) {
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