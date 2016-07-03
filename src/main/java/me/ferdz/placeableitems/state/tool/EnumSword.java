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
    SIDE_LEAN(10, "side_lean"),
    SIDE_IN0(11, "side_in0"),
    SIDE_IN45(12, "side_in45"),
    SIDE_IN90(13, "side_in90"),
    SIDE_IN135(14, "side_in135"),
    SIDE_IN180(15, "side_in180"),
    SIDE_IN225(16, "side_in225"),
    SIDE_IN270(17, "side_in270"),
    SIDE_IN315(18, "side_in315"),
    SIDE_OUT0(19, "side_out0"),
    SIDE_OUT45(20, "side_out45"),
    SIDE_OUT90(21, "side_out90"),
    SIDE_OUT135(22, "side_out135"),
    SIDE_OUT180(23, "side_out180"),
    SIDE_OUT225(24, "side_out225"),
    SIDE_OUT270(25, "side_out270"),
    SIDE_OUT315(26, "side_out315", 2);
    
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
