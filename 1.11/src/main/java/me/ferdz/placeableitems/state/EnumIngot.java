package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumIngot implements IStringSerializable {
    GOLD(0, "gold"),
    IRON(1, "iron");
	
    private int ID;
    private String name;
    
    private EnumIngot(int ID, String name) {
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