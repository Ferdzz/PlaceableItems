package com.stuntmania.placeableitems.init;

import net.minecraft.block.Block;

import com.stuntmania.placeableitems.block.*;
import com.stuntmania.placeableitems.tileentity.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block ingot;
	public static Block brick;
	public static Block bowl;
	public static Block horse_saddle_stand;
	public static Block horse_armor_stand;
	
	public static Block steak;
	public static Block apple;
	public static Block fish;
	public static Block melon;
	public static Block egg;
	public static Block pumpkin_pie;
	public static Block chicken;
	
	public static Block gunpowder;
	public static Block snowball;
	
	public static Block ender_pearl;
	public static Block ender_eye;
	
	public static Block bucket;
	
	public static void init() {
		
		ingot = new BlockIngot();
		brick = new BlockBrick();
		bowl = new BlockBowl();
		horse_saddle_stand = new BlockSaddleStand();
		horse_armor_stand = new BlockHorseArmorStand();
		
		steak = new BlockSteak(); // TODO fix block texture
		apple = new BlockApple();
		fish = new BlockFish();
		melon = new BlockMelon();
		egg = new BlockEgg();
		pumpkin_pie = new BlockPumpkinPie();
		chicken = new BlockChicken();
		
		gunpowder = new BlockGunpowder();
		snowball = new BlockSnowball();
		
		ender_pearl = new BlockEnderPearl();
		ender_eye = new BlockEnderEye();
		
		bucket = new BlockBucket();

		
		GameRegistry.registerTileEntity(TEIngot.class, "ingotBlock");
		GameRegistry.registerTileEntity(TEBrick.class, "brickBlock");
		GameRegistry.registerTileEntity(TEBowl.class, "bowlBlock");
		GameRegistry.registerTileEntity(TESaddleStand.class, "saddleStandBlock");
		GameRegistry.registerTileEntity(TEHorseArmorStand.class, "horseArmorStandBlock");

		GameRegistry.registerTileEntity(TESteak.class, "steakBlock");
		GameRegistry.registerTileEntity(TEApple.class, "appleBlock");
		GameRegistry.registerTileEntity(TEFish.class, "fishBlock");
		GameRegistry.registerTileEntity(TEMelon.class, "melonBlock");
		GameRegistry.registerTileEntity(TEEgg.class, "eggBlock");
		GameRegistry.registerTileEntity(TEPumpkinPie.class, "pumpkinPieBlock");
		GameRegistry.registerTileEntity(TEChicken.class, "chickenBlock");

		GameRegistry.registerTileEntity(TEGunpowder.class, "gunpowderBlock");
		GameRegistry.registerTileEntity(TESnowball.class, "snowballBlock");

		GameRegistry.registerTileEntity(TEEnderPearl.class, "placeableEnderPearlBlock");
		GameRegistry.registerTileEntity(TEEnderEye.class, "placeableEyeBlock");
		
		GameRegistry.registerTileEntity(TEBucket.class, "bucketBlock");
	}
}
