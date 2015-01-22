package com.stuntmania.PlaceableItems.Proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Renderers.*;
import com.stuntmania.PlaceableItems.TileEntities.*;

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {

	IngotBlockRenderer ingotBlockRenderer;
	BowlBlockRenderer bowlBlockRenderer;
	SaddleStandBlockRenderer saddleStandBlockRenderer;
	HorseArmorStandRenderer horseArmorStandRenderer;
	SteakBlockRenderer steakBlockRenderer;
	EnderPearlBlockRenderer enderPearlBlockRenderer;
	
	@Override
	public void registerRenderers() {
		ingotBlockRenderer = new IngotBlockRenderer();
		bowlBlockRenderer = new BowlBlockRenderer();
		saddleStandBlockRenderer = new SaddleStandBlockRenderer();
		steakBlockRenderer = new SteakBlockRenderer();
		horseArmorStandRenderer = new HorseArmorStandRenderer();
		enderPearlBlockRenderer = new EnderPearlBlockRenderer();
		
		ClientRegistry.bindTileEntitySpecialRenderer(IngotBlockTileEntity.class, ingotBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(BowlBlockTileEntity.class, bowlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(SaddleStandTileEntity.class, saddleStandBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(HorseArmorStandTileEntity.class, horseArmorStandRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(SteakTileEntity.class, steakBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPearlBlockTileEntity.class, enderPearlBlockRenderer);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.saddleStand), saddleStandBlockRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.horseArmorStand), horseArmorStandRenderer);
	}
}
