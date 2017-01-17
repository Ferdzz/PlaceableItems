package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumCompass implements IStringSerializable {
    COMPASS_00(0, "compass_00"),
    COMPASS_01(1, "compass_01"),
    COMPASS_02(2, "compass_02"),
    COMPASS_03(3, "compass_03"),
    COMPASS_04(4, "compass_04"),
    COMPASS_05(5, "compass_05"),
    COMPASS_06(6, "compass_06"),
    COMPASS_07(7, "compass_07"),
    COMPASS_08(8, "compass_08"),
    COMPASS_09(9, "compass_09"),
    COMPASS_10(10, "compass_10"),
    COMPASS_11(11, "compass_11"),
    COMPASS_12(12, "compass_12"),
    COMPASS_13(13, "compass_13"),
    COMPASS_14(14, "compass_14"),
    COMPASS_15(15, "compass_15"),
    COMPASS_16(16, "compass_16"),
    COMPASS_17(17, "compass_17"),
    COMPASS_18(18, "compass_18"),
    COMPASS_19(19, "compass_19"),
    COMPASS_20(20, "compass_20"),
    COMPASS_21(21, "compass_21"),
    COMPASS_22(22, "compass_22"),
    COMPASS_23(23, "compass_23"),
    COMPASS_24(24, "compass_24"),
    COMPASS_25(25, "compass_25"),
    COMPASS_26(26, "compass_26"),
    COMPASS_27(27, "compass_27"),
    COMPASS_28(28, "compass_28"),
    COMPASS_29(29, "compass_29"),
    COMPASS_30(30, "compass_30"),
    COMPASS_31(31, "compass_31");

    private int ID;
    private String name;
    
    private EnumCompass(int ID, String name) {
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