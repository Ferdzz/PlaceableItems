package com.stuntmania.placeableitems.tileentity;

public class TEFish extends TEPlaceableFood {
	
	public TEFish() {
		super(0,0);
	}
	
	// TODO: Check if this is needed for all foods, fix it
	public TEFish(int foodLevel, float saturation) {
		super(foodLevel, saturation);
	}
}
