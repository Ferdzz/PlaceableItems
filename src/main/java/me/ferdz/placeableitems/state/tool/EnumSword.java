package me.ferdz.placeableitems.state.tool;

import net.minecraft.util.IStringSerializable;

/*
 * https://gyazo.com/4808de49bc25fc5507fe812874eb906a
 */
public enum EnumSword implements IStringSerializable {
	TOP(0, "top"),
    TOP_VERTICAL(1, "top_vertical", 0),
    SIDE0(2, "side0"),
    SIDE45(3, "side45"),
    SIDE90(4, "side90"),
    SIDE135(5, "side135"),
    SIDE180(6, "side180"),
    SIDE225(7, "side225"),
    SIDE270(8, "side270"),
    SIDE315(9, "side315"),
    SIDE_LEAN(10, "side_lean", 2);
    
    private int ID;
    private String name;
    private int nextID;
    
    private EnumSword(int ID, String name) {
    	this(ID, name, ID + 1);
    }
    
    private EnumSword(int ID, String name, int next) {
    	this.ID = ID;
        this.name = name;
        this.nextID = next;
   }
    
    @Override
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
    
    public EnumSword next() {
    	for (EnumSword sword : EnumSword.values()) {
			if(sword.getID() == nextID)
				return sword;
		}
    	return null;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
