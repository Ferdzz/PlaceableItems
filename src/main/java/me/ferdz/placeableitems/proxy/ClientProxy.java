package me.ferdz.placeableitems.proxy;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ClientProxy extends CommonProxy {
		
	@Override
	public void registerRenderers() {
		OBJLoader.INSTANCE.addDomain(PlaceableItems.MODID);
	}
	
	@Override
	public void checkUpdate() {
	}
}