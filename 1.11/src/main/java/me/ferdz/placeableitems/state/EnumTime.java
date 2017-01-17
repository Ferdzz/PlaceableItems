package me.ferdz.placeableitems.state;

import net.minecraft.util.IStringSerializable;

public enum EnumTime implements IStringSerializable {
    T00(0, "t00"),
	T01(1, "t01"),
	T02(2, "t02"),
	T03(3, "t03"),
	T04(4, "t04"),
	T05(5, "t05"),
	T06(6, "t06"),
	T07(7, "t07"),
	T08(8, "t08"),
	T09(9, "t09"),
	T10(10, "t10"),
	T11(11, "t11"),
	T12(12, "t12"),
	T13(13, "t13"),
	T14(14, "t14"),
	T15(15, "t15"),
	T16(16, "t16"),
	T17(17, "t17"),
	T18(18, "t18"),
	T19(19, "t19"),
	T20(20, "t20"),
	T21(21, "t21"),
	T22(22, "t22"),
	T23(23, "t23"),
	T24(24, "t24"),
	T25(25, "t25"),
	T26(26, "t26"),
	T27(27, "t27"),
	T28(28, "t28"),
	T29(29, "t29"),
	T30(30, "t30"),
	T31(31, "t31"),
	T32(32, "t32"),
	T33(33, "t33"),
	T34(34, "t34"),
	T35(35, "t35"),
	T36(36, "t36"),
	T37(37, "t37"),
	T38(38, "t38"),
	T39(39, "t39"),
	T40(40, "t40"),
	T41(41, "t41"),
	T42(42, "t42"),
	T43(43, "t43"),
	T44(44, "t44"),
	T45(45, "t45"),
	T46(46, "t46"),
	T47(47, "t47"),
	T48(48, "t48"),
	T49(49, "t49"),
	T50(50, "t50"),
	T51(51, "t51"),
	T52(52, "t52"),
	T53(53, "t53"),
	T54(54, "t54"),
	T55(55, "t55"),
	T56(56, "t56"),
	T57(57, "t57"),
	T58(58, "t58"),
	T59(59, "t59"),
	T60(60, "t60"),
	T61(61, "t61"),
	T62(62, "t62"),
	T63(63, "t63");
	
    private int ID;
    private String name;
    
    private EnumTime(int ID, String name) {
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