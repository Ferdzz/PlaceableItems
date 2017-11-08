package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumIngotStackSize implements IStringSerializable {
    _1(0, "_1"),
    _2(1, "_2"),
    _3(2, "_3"),
    _4(3, "_4"),
    _5(4, "_5"),
    _6(5, "_6");
    
    private int ID;
    private String name;
    
    private EnumIngotStackSize(int ID, String name) {
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