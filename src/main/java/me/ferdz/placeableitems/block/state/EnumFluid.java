package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumFluid implements IStringSerializable {
    LAVA(0, "lava"),
    WATER(1, "water"),
    MILK(2, "milk");

    private int ID;
    private String name;
    
    private EnumFluid(int ID, String name) {
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