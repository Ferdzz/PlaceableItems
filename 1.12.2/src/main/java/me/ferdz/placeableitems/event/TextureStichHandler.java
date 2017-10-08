package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.PlaceableItems;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStichHandler {
	
	public static TextureAtlasSprite blazePowder1;
	public static TextureAtlasSprite blazePowder2;
	public static TextureAtlasSprite blazePowder3;
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre e) {
		blazePowder1 = e.getMap().registerSprite(new ResourceLocation(PlaceableItems.MODID, "blocks/particle/blaze_powder_particle"));
		blazePowder2 = e.getMap().registerSprite(new ResourceLocation(PlaceableItems.MODID, "blocks/particle/blaze_powder_particle2"));
		blazePowder3 = e.getMap().registerSprite(new ResourceLocation(PlaceableItems.MODID, "blocks/particle/blaze_powder_particle3"));
	}
}
