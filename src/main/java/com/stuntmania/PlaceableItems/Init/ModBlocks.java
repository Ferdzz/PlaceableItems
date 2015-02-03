package com.stuntmania.PlaceableItems.Init;

import net.minecraft.block.Block;

import com.stuntmania.PlaceableItems.Blocks.*;
import com.stuntmania.PlaceableItems.TileEntities.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block ingotBlock;
	public static Block bowlBlock;
	public static Block saddleStand;
	public static Block horseArmorStand;
	
	public static Block steakBlock;
	public static Block appleBlock;
	public static Block fishBlock;
	public static Block melonBlock;
	
	public static Block gunpowderBlock;
	
	public static Block enderPearlBlock;
	public static Block enderEyeBlock;
	
	public static Block bucketBlock;
	
	public static void init() {
		
		ingotBlock = new IngotBlock();
		bowlBlock = new BowlBlock();
		saddleStand = new SaddleStandBlock();
		horseArmorStand = new HorseArmorStandBlock();
		
		steakBlock = new SteakBlock(); // TODO fix block texture
		appleBlock = new AppleBlock();
		fishBlock = new FishBlock();
		melonBlock = new MelonBlock();
		
		gunpowderBlock = new GunpowderBlock();
		
		enderPearlBlock = new EnderPearlBlock();
		enderEyeBlock = new EnderEyeBlock();
		
		bucketBlock = new BucketBlock();

		
		GameRegistry.registerTileEntity(IngotBlockTileEntity.class, "ingotBlock");
		GameRegistry.registerTileEntity(BowlBlockTileEntity.class, "bowlBlock");
		GameRegistry.registerTileEntity(SaddleStandTileEntity.class, "saddleStandBlock");
		GameRegistry.registerTileEntity(HorseArmorStandTileEntity.class, "horseArmorStandBlock");

		GameRegistry.registerTileEntity(SteakTileEntity.class, "steakBlock");
		GameRegistry.registerTileEntity(AppleBlockTileEntity.class, "appleBlock");
		GameRegistry.registerTileEntity(FishBlockTileEntity.class, "fishBlock");
		GameRegistry.registerTileEntity(MelonBlockTileEntity.class, "melonBlock");

		GameRegistry.registerTileEntity(GunpowderBlockTileEntity.class, "gunpowderBlock");

		GameRegistry.registerTileEntity(EnderPearlBlockTileEntity.class, "enderPearlBlock");
		GameRegistry.registerTileEntity(EnderEyeBlockTileEntity.class, "enderEyeBlock");
		GameRegistry.registerTileEntity(BucketBlockTileEntity.class, "bucketBlock");
	}
}
