package com.stuntmania.placeableitems.tileentity;

public class TEFish extends TEPlaceableFood {
	
	//TODO: Check if this is needed for all foods, fix it
	public TEFish() {
		super(0,0);
	}
	
	public TEFish(int foodLevel, float saturation) {
		super(foodLevel, saturation);
	}
}
