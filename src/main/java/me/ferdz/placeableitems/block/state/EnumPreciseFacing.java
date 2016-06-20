package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumPreciseFacing implements IStringSerializable {
	D270(6, "d270"),
    D45(1, "d45"),
    D0(0, "d0"),
    D315(7, "d315"),
    D90(2, "d90"),
    D225(5, "d225"),
    D180(4, "d180"),
    D135(3, "d135");
	
    private int ID;
    private String name;
    
    private EnumPreciseFacing(int ID, String name) {
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