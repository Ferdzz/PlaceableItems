package com.stuntmania.placeableitems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.tileentity.*;
import com.stuntmania.placeableitems.tileentity.renderers.*;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEIngot.class, new TESRIngot());
		ClientRegistry.bindTileEntitySpecialRenderer(TEBowl.class, new TESRBowl());
		ClientRegistry.bindTileEntitySpecialRenderer(TEBrick.class, new TESRBrick());
		ClientRegistry.bindTileEntitySpecialRenderer(TESaddleStand.class, new TESRSaddleStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TEHorseArmorStand.class, new TESRHorseArmorStand());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TESteak.class, new TESRSteak());
		ClientRegistry.bindTileEntitySpecialRenderer(TEApple.class, new TESRApple());
		ClientRegistry.bindTileEntitySpecialRenderer(TEFish.class, new TESRFish());
		ClientRegistry.bindTileEntitySpecialRenderer(TEMelon.class, new TESRMelon());
		ClientRegistry.bindTileEntitySpecialRenderer(TEEgg.class, new TESREgg());
		ClientRegistry.bindTileEntitySpecialRenderer(TEPumpkinPie.class, new TESRPumpkinPie());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderPearl.class, new TESREnderPearl());
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderEye.class, new TESREnderEye());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEGunpowder.class, new TESRGunpowder());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEBucket.class, new TESRBucket());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.saddle), new TESRSaddleStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_armor_stand), new TESRHorseArmorStand());
	}
}
