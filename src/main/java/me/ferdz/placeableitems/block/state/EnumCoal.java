package me.ferdz.placeableitems.block.state;

import net.minecraft.util.IStringSerializable;

public enum EnumCoal implements IStringSerializable {
    COAL(0, "coal"),
    CHARCOAL(1, "charcoal");
	
    private int ID;
    private String name;
    
    private EnumCoal(int ID, String name) {
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