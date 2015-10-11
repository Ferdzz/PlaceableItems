package com.stuntmania.placeableitems.init;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

import com.stuntmania.placeableitems.block.*;
import com.stuntmania.placeableitems.tileentity.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block bone;
	public static Block book;
	public static Block bowl;
	public static Block brick;
	public static Block bucket;
	public static Block clay;
	public static Block diamond;
	public static Block disk;
	public static Block ender_eye;
	public static Block ender_pearl;
	public static Block glowstone;
	public static Block gunpowder;
	public static Block horse_armor_stand;
	public static Block horse_saddle_stand;
	public static Block ingot;
	public static Block slimeBall;
	public static Block snowball;
	public static Block stick;
	
	//food
	public static Block apple;
	public static Block bread;
	public static Block carrot;
	public static Block chicken; // raw
	public static Block cookie;
	public static Block egg;
	public static Block fish; // cooked
	public static Block melon;
	public static Block porkchop; // both	
	public static Block pumpkin_pie;
	public static Block steak; // both
	
	public static HashMap<String, Class<? extends TileEntity>> TERegistry;
	
	public static void init() {
		TERegistry = new HashMap<String, Class<? extends TileEntity>>();
		
		bone = RegistryUtil.fullRegister(new BlockBone(), "boneBlock", TEBone.class);
		book = RegistryUtil.fullRegister(new BlockBook(), "bookBlock", TEBook.class);
		bowl = RegistryUtil.fullRegister(new BlockBowl(), "bowlBlock", TEBowl.class);
		brick = RegistryUtil.fullRegister(new BlockBrick(), "brickBlock", TEBrick.class);
		bucket = RegistryUtil.fullRegister(new BlockBucket(), "bucketBlock", TEBucket.class);
		clay = RegistryUtil.fullRegister(new BlockClay(), "claiBallBlock", TEClay.class);
		diamond = RegistryUtil.fullRegister(new BlockDiamond(), "diamondBlock", TEDiamond.class);
		disk = RegistryUtil.fullRegister(new BlockDisk(), "diskBlock", TEDisk.class);
		ender_eye = RegistryUtil.fullRegister(new BlockEnderEye(), "placeableEnderEyeBlock", TEEnderEye.class);
		ender_pearl = RegistryUtil.fullRegister(new BlockEnderPearl(), "placeableEnderPearlBlock", TEEnderPearl.class);
		glowstone = RegistryUtil.fullRegister(new BlockGlowstone(), "glowstoneBlock", TEGlowstone.class);
		gunpowder = RegistryUtil.fullRegister(new BlockGunpowder(), "gunpowderBlock", TEGunpowder.class);
		ingot = RegistryUtil.fullRegister(new BlockIngot(), "ingotBlock", TEIngot.class);
		slimeBall = RegistryUtil.fullRegister(new BlockSlimeBall(), "slimeBallBlock", TESlimeBall.class);
		snowball = RegistryUtil.fullRegister(new BlockSnowball(), "snowballBlock", TESnowball.class);
		stick = RegistryUtil.fullRegister(new BlockStick(), "stickBlock", TEStick.class);
		
		apple = RegistryUtil.fullRegister(new BlockApple(), "appleBlock", TEApple.class);
		bread = RegistryUtil.fullRegister(new BlockBread(), "breadBlock", TEBread.class);
		carrot = RegistryUtil.fullRegister(new BlockCarrot(), "carrotBlock", TECarrot.class);
		chicken = RegistryUtil.fullRegister(new BlockChicken(), "chickenBlock", TEChicken.class);
		cookie = RegistryUtil.fullRegister(new BlockCookie(), "cookieBlock", TECookie.class);
		egg = RegistryUtil.fullRegister(new BlockEgg(), "eggBlock", TEEgg.class);
		fish = RegistryUtil.fullRegister(new BlockFish(), "fishBlock", TEFish.class);
		melon = RegistryUtil.fullRegister(new BlockMelon(), "melonBlock", TEMelon.class);
		porkchop = RegistryUtil.fullRegister(new BlockPorkchop(), "blockPorkChop", TEPorkchop.class);		
		pumpkin_pie = RegistryUtil.fullRegister(new BlockPumpkinPie(), "pumpkinPieBlock", TEPumpkinPie.class);
		steak = RegistryUtil.fullRegister(new BlockSteak(), "steakBlock", TESteak.class);
		
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
