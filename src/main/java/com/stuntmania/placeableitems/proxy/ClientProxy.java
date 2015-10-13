package com.stuntmania.placeableitems.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.tileentity.*;
import com.stuntmania.placeableitems.tileentity.renderers.*;
import com.stuntmania.placeableitems.utils.UpdateChecker;

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {

	TESRBone boneBlockRenderer;
	TESRBook bookBlockRenderer;
	TESRBowl bowlBlockRenderer;
	TESRBrick brickBlockRenderer;
	TESRBucket bucketBlockRenderer;
	TESRCarrotOnStick carrotOnStickBlockRenderer;
	TESRClay clayBlockRenderer;
	TESRDiamond diamondBlockRenderer;
	TESRDisk diskBlockRenderer;
	TESREnderEye enderEyeBlockRenderer;
	TESREnderPearl enderPearlBlockRenderer;
	TESRGlowstone glowstoneBlockRenderer;
	TESRGunpowder gunpowderBlockRenderer;
	TESRIngot ingotBlockRenderer;
	TESRPaper paperBlockRenderer;
	TESRQuartz quartzBlockRenderer;
	TESRSaddleStand horseSaddleStandBlockRenderer;
	TESRHorseArmorStand horseArmorStandRenderer;
	TESRSlimeBall slimeBallBlockRenderer;
	TESRSnowball snowballBlockRenderer;
	TESRSpiderEye spiderEyeBlockRenderer;
	TESRStick stickBlockRenderer;
	
	//Food
	TESRApple appleBlockRenderer;
	TESRBread breadRenderer;
	TESRCarrot carrotRenderer;
	TESRChicken chickenRenderer;
	TESRCookie cookieRenderer;
	TESREgg eggBlockRenderer;
	TESRFish fishBlockRenderer;
	TESRMelon melonBlockRenderer;
	TESRPorkchop porkchopRenderer;
	TESRPumpkinPie pumpkinPieRenderer;
	TESRSteak steakBlockRenderer;
		
	@Override
	public void registerRenderers() {
		boneBlockRenderer = new TESRBone();
		bookBlockRenderer = new TESRBook();
		bowlBlockRenderer = new TESRBowl();
		brickBlockRenderer = new TESRBrick();
		bucketBlockRenderer = new TESRBucket();
		carrotOnStickBlockRenderer = new TESRCarrotOnStick();
		clayBlockRenderer = new TESRClay();
		diamondBlockRenderer = new TESRDiamond();
		diskBlockRenderer = new TESRDisk();
		enderEyeBlockRenderer = new TESREnderEye();
		enderPearlBlockRenderer = new TESREnderPearl();
		glowstoneBlockRenderer = new TESRGlowstone();
		gunpowderBlockRenderer = new TESRGunpowder();
		horseArmorStandRenderer = new TESRHorseArmorStand();
		horseSaddleStandBlockRenderer = new TESRSaddleStand();
		ingotBlockRenderer = new TESRIngot();
		paperBlockRenderer = new TESRPaper();
		quartzBlockRenderer = new TESRQuartz();
		slimeBallBlockRenderer = new TESRSlimeBall();
		snowballBlockRenderer = new TESRSnowball();
		spiderEyeBlockRenderer = new TESRSpiderEye();
		stickBlockRenderer = new TESRStick();

		appleBlockRenderer = new TESRApple();
		breadRenderer = new TESRBread();
		carrotRenderer = new TESRCarrot();
		chickenRenderer = new TESRChicken();
		cookieRenderer = new TESRCookie();
		eggBlockRenderer = new TESREgg();
		fishBlockRenderer = new TESRFish();
		melonBlockRenderer = new TESRMelon();
		porkchopRenderer = new TESRPorkchop();
		pumpkinPieRenderer = new TESRPumpkinPie();
		steakBlockRenderer = new TESRSteak();		
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEBone.class, boneBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBook.class, bookBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBowl.class, bowlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBrick.class, brickBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBucket.class, bucketBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TECarrotOnStick.class, carrotOnStickBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEClay.class, clayBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEDiamond.class, diamondBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEDisk.class, diskBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderEye.class, enderEyeBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEEnderPearl.class, enderPearlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEGlowstone.class, glowstoneBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEGunpowder.class, gunpowderBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEHorseArmorStand.class, horseArmorStandRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESaddleStand.class, horseSaddleStandBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEIngot.class, ingotBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEQuartz.class, quartzBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEPaper.class, paperBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESlimeBall.class, slimeBallBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESnowball.class, snowballBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESpiderEye.class, spiderEyeBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEStick.class, stickBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(TEApple.class, appleBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEBread.class, breadRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TECarrot.class, carrotRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEChicken.class, chickenRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TECookie.class, cookieRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEEgg.class, eggBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEFish.class, fishBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEMelon.class, melonBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEPorkchop.class, porkchopRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TEPumpkinPie.class, pumpkinPieRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(TESteak.class, steakBlockRenderer);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_saddle_stand), horseSaddleStandBlockRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.horse_armor_stand), horseArmorStandRenderer);
	}
	
	@Override
	public void checkUpdate() {
		PlaceableItems.updateChecker = new UpdateChecker();
		Thread updateCheck = new Thread(PlaceableItems.updateChecker, "Update Check");
		updateCheck.start();
	}
}