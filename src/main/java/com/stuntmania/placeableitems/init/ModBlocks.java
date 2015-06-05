package com.stuntmania.placeableitems.init;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import com.stuntmania.placeableitems.block.*;
import com.stuntmania.placeableitems.tileentity.*;
import com.stuntmania.placeableitems.tileentity.renderers.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block ingot;
	public static Block brick;
	public static Block bowl;
	public static Block horse_saddle_stand;
	public static Block horse_armor_stand;
	public static Block book;
	public static Block disk;
	public static Block bone;
	
	public static Block steak; //cooked
	public static Block apple;
	public static Block fish; //cooked
	public static Block melon;
	public static Block egg;
	public static Block pumpkin_pie;
	public static Block chicken; //raw
	
	public static Block gunpowder;
	public static Block snowball;
	
	public static Block ender_pearl;
	public static Block ender_eye;
	
	public static Block bucket;
	
	public static HashMap<Class<? extends TileEntity>, String> TERegistry;
	
	public static void init() {
		TERegistry = new HashMap<Class<? extends TileEntity>, String>();
		
		ingot = RegistryUtil.fullRegister(new BlockIngot(), "ingotBlock", TEIngot.class, new TESRIngot());
		brick = RegistryUtil.fullRegister(new BlockBrick(), "brickBlock", TEBrick.class, new TESRBrick());
		bowl = RegistryUtil.fullRegister(new BlockBowl(), "bowlBlock", TEBowl.class, new TESRBowl());
		book = RegistryUtil.fullRegister(new BlockBook(), "bookBlock", TEBook.class, new TESRBook());
		disk = RegistryUtil.fullRegister(new BlockDisk(), "diskBlock", TEDisk.class, new TESRDisk());
		bone = RegistryUtil.fullRegister(new BlockBone(), "boneBlock", TEBone.class, new TESRBone());
		
		steak = RegistryUtil.fullRegister(new BlockSteak(), "steakBlock", TESteak.class, new TESRSteak());
		apple = RegistryUtil.fullRegister(new BlockApple(), "appleBlock", TEApple.class, new TESRApple());
		fish = RegistryUtil.fullRegister(new BlockFish(), "fishBlock", TEFish.class, new TESRFish());
		melon = RegistryUtil.fullRegister(new BlockMelon(), "melonBlock", TEMelon.class, new TESRMelon());
		egg = RegistryUtil.fullRegister(new BlockEgg(), "eggBlock", TEEgg.class, new TESREgg());
		pumpkin_pie = RegistryUtil.fullRegister(new BlockPumpkinPie(), "pumpkinPieBlock", TEPumpkinPie.class, new TESRPumpkinPie());
		chicken = RegistryUtil.fullRegister(new BlockChicken(), "chickenBlock", TEChicken.class, new TESRChicken());
		
		gunpowder = RegistryUtil.fullRegister(new BlockGunpowder(), "gunpowderBlock", TEGunpowder.class, new TESRGunpowder());
		snowball = RegistryUtil.fullRegister(new BlockSnowball(), "snowballBlock", TESnowball.class, new TESRSnowball());
		
		ender_pearl = RegistryUtil.fullRegister(new BlockEnderPearl(), "placeableEnderPearlBlock", TEEnderPearl.class, new TESREnderPearl());
		ender_eye = RegistryUtil.fullRegister(new BlockEnderEye(), "placeableEnderEyeBlock", TEEnderEye.class, new TESREnderEye());
		
		bucket = RegistryUtil.fullRegister(new BlockBucket(), "bucketBlock", TEBucket.class, new TESRBucket());
		
		horse_saddle_stand = new BlockSaddleStand();
		RegistryUtil.addToBlockRegistry(horse_saddle_stand, "saddleStandBlock");
		RegistryUtil.addToTERegistry(TESaddleStand.class, horse_saddle_stand.getUnlocalizedName());
		horse_armor_stand = new BlockHorseArmorStand();
		RegistryUtil.addToBlockRegistry(horse_armor_stand, "horseArmorStandBlock");
		RegistryUtil.addToTERegistry(TEHorseArmorStand.class, horse_armor_stand.getUnlocalizedName());

		for (HashMap.Entry<Class<? extends TileEntity>, String> entry : TERegistry.entrySet()) {
		    GameRegistry.registerTileEntity(entry.getKey(), entry.getValue());
		}
	}
}
