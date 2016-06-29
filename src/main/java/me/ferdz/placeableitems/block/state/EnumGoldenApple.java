package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumGoldenApple implements IStringSerializable {
    NORMAL(0, "normal"),
    NOTCH(1, "notch");
    
    private int ID;
    private String name;
    
    private EnumGoldenApple(int ID, String name) {
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