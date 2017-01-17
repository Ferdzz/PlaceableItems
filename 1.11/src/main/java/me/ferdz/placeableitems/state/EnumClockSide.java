package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumClockSide implements IStringSerializable {
    DOWN(0, "down"), 
    EAST(1, "east"), 
    SOUTH(2, "south"), 
    NORTH(3, "north"), 
    WEST(4, "west"), ;
	
    private int ID;
    private String name;
    
    private EnumClockSide(int ID, String name) {
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