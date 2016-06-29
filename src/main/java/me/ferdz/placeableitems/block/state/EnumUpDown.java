package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumUpDown implements IStringSerializable {
    UP(0, "up"),
    DOWN(1, "down");
    
    private int ID;
    private String name;
    
    private EnumUpDown(int ID, String name) {
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