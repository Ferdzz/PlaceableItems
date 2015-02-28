package com.stuntmania.placeableitems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.tileentity.*;
import com.stuntmania.placeableitems.tileentity.renderers.*;

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {

	TESRIngot ingotBlockRenderer;
	TESRBowl bowlBlockRenderer;
	TESRBrick brickBlockRenderer;
	TESRSaddleStand saddleStandBlockRenderer;
	TESRHorseArmorStand horseArmorStandRenderer;
	
	TESRSteak steakBlockRenderer;
	TESRApple appleBlockRenderer;
	TESRFish fishBlockRenderer;
	TESRMelon melonBlockRenderer;
	TESREgg eggBlockRenderer;
	TESRPumpkinPie pumpkinPieRenderer;
	TESRChicken chickenRenderer;
	
	TESREnderPearl enderPearlBlockRenderer;
	TESREnderEye enderEyeBlockRenderer;
	
	TESRGunpowder gunpowderBlockRenderer;
	
	TESRBucket bucketBlockRenderer;
	
	@Override
	public void registerRenderers() {
		ingotBlockRenderer = new TESRIngot();
		bowlBlockRenderer = new TESRBowl();
		brickBlockRenderer = new TESRBrick();
		saddleStandBlockRenderer = new TESRSaddleStand();
		horseArmorStandRenderer = new TESRHorseArmorStand();

		steakBlockRenderer = new TESRSteak();
		appleBlockRenderer = new TESRApple();
		fishBlockRenderer = new TESRFish();
		melonBlockRenderer = new TESRMelon();
		eggBlockRenderer = new TESREgg();
		pumpkinPieRenderer = new TESRPumpkinPie();
		chickenRenderer = new TESRChicken();
		
		enderPearlBlockRenderer = new TESREnderPearl();
		enderEyeBlockRenderer = new TESREnderEye();
		
		gunpowderBlockRenderer = new TESRGunpowder();
		
		bucketBlockRenderer = new TESRBucket();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEIngot.class, ingotBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBowl.class, bowlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBrick.class, brickBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESaddleStand.class, saddleStandBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEHorseArmorStand.class, horseArmorStandRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TESteak.class, steakBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEApple.class, appleBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEFish.class, fishBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEMelon.class, melonBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEEgg.class, eggBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEPumpkinPie.class, pumpkinPieRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEChicken.class, chickenRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderPearl.class, enderPearlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderEye.class, enderEyeBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEGunpowder.class, gunpowderBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEBucket.class, bucketBlockRenderer);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_saddle_stand), saddleStandBlockRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_armor_stand), horseArmorStandRenderer);
	}
}
