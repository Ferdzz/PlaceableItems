package com.stuntmania.placeableitems.init;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import com.stuntmania.placeableitems.block.*;
import com.stuntmania.placeableitems.tileentity.*;

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
	public static Block bread;
	
	public static Block gunpowder;
	public static Block snowball;
	
	public static Block ender_pearl;
	public static Block ender_eye;
	
	public static Block bucket;
	
	public static HashMap<String, Class<? extends TileEntity>> TERegistry;
	
	public static void init() {
		TERegistry = new HashMap<String, Class<? extends TileEntity>>();
		
		ingot = RegistryUtil.fullRegister(new BlockIngot(), "ingotBlock", TEIngot.class);
		brick = RegistryUtil.fullRegister(new BlockBrick(), "brickBlock", TEBrick.class);
		bowl = RegistryUtil.fullRegister(new BlockBowl(), "bowlBlock", TEBowl.class);
		book = RegistryUtil.fullRegister(new BlockBook(), "bookBlock", TEBook.class);
		disk = RegistryUtil.fullRegister(new BlockDisk(), "diskBlock", TEDisk.class);
		bone = RegistryUtil.fullRegister(new BlockBone(), "boneBlock", TEBone.class);
		
		steak = RegistryUtil.fullRegister(new BlockSteak(), "steakBlock", TESteak.class);
		apple = RegistryUtil.fullRegister(new BlockApple(), "appleBlock", TEApple.class);
		fish = RegistryUtil.fullRegister(new BlockFish(), "fishBlock", TEFish.class);
		melon = RegistryUtil.fullRegister(new BlockMelon(), "melonBlock", TEMelon.class);
		egg = RegistryUtil.fullRegister(new BlockEgg(), "eggBlock", TEEgg.class);
		pumpkin_pie = RegistryUtil.fullRegister(new BlockPumpkinPie(), "pumpkinPieBlock", TEPumpkinPie.class);
		chicken = RegistryUtil.fullRegister(new BlockChicken(), "chickenBlock", TEChicken.class);
		bread = RegistryUtil.fullRegister(new BlockBread(), "breadBlock", TEBread.class);
		
		gunpowder = RegistryUtil.fullRegister(new BlockGunpowder(), "gunpowderBlock", TEGunpowder.class);
		snowball = RegistryUtil.fullRegister(new BlockSnowball(), "snowballBlock", TESnowball.class);
		
		ender_pearl = RegistryUtil.fullRegister(new BlockEnderPearl(), "placeableEnderPearlBlock", TEEnderPearl.class);
		ender_eye = RegistryUtil.fullRegister(new BlockEnderEye(), "placeableEnderEyeBlock", TEEnderEye.class);
		
		bucket = RegistryUtil.fullRegister(new BlockBucket(), "bucketBlock", TEBucket.class);
		
		horse_saddle_stand = new BlockSaddleStand();
		RegistryUtil.addToBlockRegistry(horse_saddle_stand, "saddleStandBlock");
		RegistryUtil.addToTERegistry(horse_saddle_stand.getUnlocalizedName(), TESaddleStand.class);
		horse_armor_stand = new BlockHorseArmorStand();
		RegistryUtil.addToBlockRegistry(horse_armor_stand, "horseArmorStandBlock");
		RegistryUtil.addToTERegistry(horse_armor_stand.getUnlocalizedName(), TEHorseArmorStand.class);

		for (Entry<String, Class<? extends TileEntity>> entry : TERegistry.entrySet()) {
		    GameRegistry.registerTileEntity(entry.getValue(), entry.getKey());
		}
	}
}
