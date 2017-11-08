package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumCookieStackSize implements IStringSerializable {
    _1(0, "_1"),
    _2(1, "_2"),
    _3(2, "_3"),
    _4(3, "_4");

    private int ID;
    private String name;

    private EnumCookieStackSize(int ID, String name) {
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