package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumDisc implements IStringSerializable {
    RECORD_11(0, "record_11"),
    RECORD_13(1, "record_13"),
    RECORD_BLOCKS(2, "record_blocks"),
    RECORD_CAT(3, "record_cat"),
    RECORD_CHIRP(4, "record_chirp"),
    RECORD_FAR(5, "record_far"),
    RECORD_MALL(6, "record_mall"),
    RECORD_MELLOHI(7, "record_mellohi"),
    RECORD_STAL(8, "record_stal"),
    RECORD_STRAD(9, "record_strad"),
    RECORD_WAIT(10, "record_wait"),
    RECORD_WARD(11, "record_ward");
	
    private int ID;
    private String name;
    
    private EnumDisc(int ID, String name) {
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