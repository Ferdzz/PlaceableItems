package com.stuntmania.placeableitems.proxy;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.tileentity.TEHorseArmorStand;
import com.stuntmania.placeableitems.tileentity.TESaddleStand;
import com.stuntmania.placeableitems.tileentity.renderers.TESRHorseArmorStand;
import com.stuntmania.placeableitems.tileentity.renderers.TESRSaddleStand;

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {
	public static HashMap<Class <? extends TileEntity>, TileEntitySpecialRenderer> TESRRegistry = new HashMap<Class <? extends TileEntity>, TileEntitySpecialRenderer>();	
	
	private TESRHorseArmorStand horseArmorSand;
	private TESRSaddleStand saddleStand;
	
	@Override
	public void registerRenderers() {
		
		for (Entry<Class<? extends TileEntity>, TileEntitySpecialRenderer> entry : TESRRegistry.entrySet()) {
			ClientRegistry.bindTileEntitySpecialRenderer(entry.getKey(), entry.getValue());
		}
		
		//Different handling for horse armor stand & Saddle stand
		//because both needs to be rendered as a 3D model when held by the player as well.
		//maybe I'll change things around so this is not necessary
		horseArmorSand = new TESRHorseArmorStand();
		saddleStand = new TESRSaddleStand();
		ClientRegistry.bindTileEntitySpecialRenderer(TEHorseArmorStand.class, horseArmorSand);
		ClientRegistry.bindTileEntitySpecialRenderer(TESaddleStand.class, saddleStand);		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_armor_stand), horseArmorSand);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_saddle_stand), saddleStand);
	}
}
