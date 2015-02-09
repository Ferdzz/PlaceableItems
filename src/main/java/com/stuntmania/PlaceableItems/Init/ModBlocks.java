package com.stuntmania.PlaceableItems.Init;

import net.minecraft.block.Block;

import com.stuntmania.PlaceableItems.Blocks.*;
import com.stuntmania.PlaceableItems.TileEntities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block ingotBlock;
	public static Block brickBlock;
	public static Block bowlBlock;
	public static Block saddleStand;
	public static Block horseArmorStand;
	
	public static Block steakBlock;
	public static Block appleBlock;
	public static Block fishBlock;
	public static Block melonBlock;
	public static Block eggBlock;
	public static Block pumpkinPieBlock;
	
	public static Block gunpowderBlock;
	
	public static Block enderPearlBlock;
	public static Block enderEyeBlock;
	
	public static Block bucketBlock;
	
	public static void init() {
		
		ingotBlock = new BlockIngot();
		brickBlock = new BlockBrick();
		bowlBlock = new BlockBowl();
		saddleStand = new BlockSaddleStand();
		horseArmorStand = new BlockHorseArmorStand();
		
		steakBlock = new BlockSteak(); // TODO fix block texture
		appleBlock = new BlockApple();
		fishBlock = new BlockFish();
		melonBlock = new BlockMelon();
		eggBlock = new BlockEgg();
		pumpkinPieBlock = new BlockPumpkinPie();
		
		gunpowderBlock = new BlockGunpowder();
		
		enderPearlBlock = new BlockEnderPearl();
		enderEyeBlock = new BlockEnderEye();
		
		bucketBlock = new BlockBucket();

		
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

		GameRegistry.registerTileEntity(TEGunpowder.class, "gunpowderBlock");

		GameRegistry.registerTileEntity(TEEnderPearl.class, "enderPearlBlock");
		GameRegistry.registerTileEntity(TEEnderEye.class, "enderEyeBlock");
		
		GameRegistry.registerTileEntity(TEBucket.class, "bucketBlock");
	}
}
