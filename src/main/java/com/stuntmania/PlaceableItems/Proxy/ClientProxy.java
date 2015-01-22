package com.stuntmania.PlaceableItems.Proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Renderers.BowlBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.EnderEyeBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.EnderPearlBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.HorseArmorStandRenderer;
import com.stuntmania.PlaceableItems.Renderers.IngotBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.SaddleStandBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.SteakBlockRenderer;
import com.stuntmania.PlaceableItems.TileEntities.BowlBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.EnderEyeBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.EnderPearlBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.HorseArmorStandTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.SteakTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {

	IngotBlockRenderer ingotBlockRenderer;
	BowlBlockRenderer bowlBlockRenderer;
	SaddleStandBlockRenderer saddleStandBlockRenderer;
	HorseArmorStandRenderer horseArmorStandRenderer;
	SteakBlockRenderer steakBlockRenderer;
	EnderPearlBlockRenderer enderPearlBlockRenderer;
	EnderEyeBlockRenderer enderEyeBlockRenderer;
	
	@Override
	public void registerRenderers() {
		ingotBlockRenderer = new IngotBlockRenderer();
		bowlBlockRenderer = new BowlBlockRenderer();
		saddleStandBlockRenderer = new SaddleStandBlockRenderer();
		steakBlockRenderer = new SteakBlockRenderer();
		horseArmorStandRenderer = new HorseArmorStandRenderer();
		enderPearlBlockRenderer = new EnderPearlBlockRenderer();
		enderEyeBlockRenderer = new EnderEyeBlockRenderer();
		
		ClientRegistry.bindTileEntitySpecialRenderer(IngotBlockTileEntity.class, ingotBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(BowlBlockTileEntity.class, bowlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(SaddleStandTileEntity.class, saddleStandBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(HorseArmorStandTileEntity.class, horseArmorStandRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(SteakTileEntity.class, steakBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPearlBlockTileEntity.class, enderPearlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(EnderEyeBlockTileEntity.class, enderEyeBlockRenderer);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.saddleStand), saddleStandBlockRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.horseArmorStand), horseArmorStandRenderer);
	}
}
