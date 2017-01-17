package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumBlazeRod implements IStringSerializable {
    ON(0, "on"),
    OFF(1, "off");
	
    private int ID;
    private String name;
    
    private EnumBlazeRod(int ID, String name) {
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