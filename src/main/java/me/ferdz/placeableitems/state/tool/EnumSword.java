package me.ferdz.placeableitems.state.tool;

import java.util.ArrayList;
import java.util.LinkedList;

import net.minecraft.util.IStringSerializable;

/*
 * https://gyazo.com/4808de49bc25fc5507fe812874eb906a
 */
public enum EnumSword implements IStringSerializable {
	TOP(0, "top", 0),
    TOP_VERTICAL(1, "top_vertical", 0),
    
    SIDE0(2, "side0", 1),
    SIDE45(3, "side45", 1),
    SIDE90(4, "side90", 1),
    SIDE135(5, "side135", 1),
    SIDE180(6, "side180", 1),
    SIDE225(7, "side225", 1),
    SIDE270(8, "side270", 1),
    SIDE315(9, "side315", 1),
    SIDE_LEAN(10, "side_lean", 1),
    SIDE_IN0(11, "side_in0", 1),
    SIDE_IN45(12, "side_in45", 1),
    SIDE_IN90(13, "side_in90", 1),
    SIDE_IN135(14, "side_in135", 1),
    SIDE_IN180(15, "side_in180", 1),
    SIDE_IN225(16, "side_in225", 1),
    SIDE_IN270(17, "side_in270", 1),
    SIDE_IN315(18, "side_in315", 1),
    SIDE_OUT0(19, "side_out0", 1),
    SIDE_OUT45(20, "side_out45", 1),
    SIDE_OUT90(21, "side_out90", 1),
    SIDE_OUT135(22, "side_out135", 1),
    SIDE_OUT180(23, "side_out180", 1),
    SIDE_OUT225(24, "side_out225", 1),
    SIDE_OUT270(25, "side_out270", 1),
    SIDE_OUT315(26, "side_out315", 1);
    
    private int ID;
    private String name;
    private int groupID;
    private static ArrayList<ArrayList<EnumSword>> groups = new ArrayList<ArrayList<EnumSword>>();
    
    private EnumSword(int ID, String name, int group) {
    	this.ID = ID;
		this.name = name;
		this.groupID = group;
	}

    static {
    	for (EnumSword enumSword : values()) {
    		if(groups.size() <= enumSword.groupID) {
    			ArrayList<EnumSword> list = new ArrayList<EnumSword>();
    			list.add(enumSword);
    			groups.add(list);
    		} else {
    			ArrayList<EnumSword> list = groups.get(enumSword.groupID);
    			list.add(enumSword);
    		}
		}
    }
    
	@Override
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

	public EnumSword next() {
		ArrayList<EnumSword> list = groups.get(groupID);
		int index = list.indexOf(this);
		
		if(index == list.size() - 1) {
			index = 0;
		} else {
			index++;
		}
		
		return list.get(index);
    }
	
	public EnumSword previous() {
		ArrayList<EnumSword> list = groups.get(groupID);
		int index = list.indexOf(this);
		
		if(index == 0) {
			index = list.size() - 1;
		} else {
			index--;
		}
		
		return list.get(index);
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
