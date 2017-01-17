package me.ferdz.placeableitems.state.tool;

import net.minecraft.util.IStringSerializable;

public enum EnumToolMaterial implements IStringSerializable {
	WOOD(0, "wood"),
	STONE(1, "stone"),
	IRON(2, "iron"),
	GOLD(3, "gold"),
	DIAMOND(4, "diamond");
    
    private int ID;
    private String name;
    
    private EnumToolMaterial(int ID, String name) {
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
