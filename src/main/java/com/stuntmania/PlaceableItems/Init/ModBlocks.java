package com.stuntmania.PlaceableItems.Init;

import com.stuntmania.PlaceableItems.Blocks.AppleBlock;
import com.stuntmania.PlaceableItems.Blocks.BowlBlock;
import com.stuntmania.PlaceableItems.Blocks.BucketBlock;
import com.stuntmania.PlaceableItems.Blocks.EnderEyeBlock;
import com.stuntmania.PlaceableItems.Blocks.EnderPearlBlock;
import com.stuntmania.PlaceableItems.Blocks.FishBlock;
import com.stuntmania.PlaceableItems.Blocks.GunpowderBlock;
import com.stuntmania.PlaceableItems.Blocks.HorseArmorStandBlock;
import com.stuntmania.PlaceableItems.Blocks.IngotBlock;
import com.stuntmania.PlaceableItems.Blocks.MelonBlock;
import com.stuntmania.PlaceableItems.Blocks.SaddleStandBlock;
import com.stuntmania.PlaceableItems.Blocks.SteakBlock;
import com.stuntmania.PlaceableItems.TileEntities.AppleBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.BowlBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.BucketBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.EnderEyeBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.EnderPearlBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.FishBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.GunpowderBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.HorseArmorStandTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.MelonBlockTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.SteakTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

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
		
		ingotBlock = new IngotBlock(Material.iron).setBlockName("ingotBlock");
		GameRegistry.registerBlock(ingotBlock, "ingotBlock");
		GameRegistry.registerTileEntity(IngotBlockTileEntity.class, "ingotBlock");
		bowlBlock = new BowlBlock(Material.wood).setBlockName("bowlBlock");
		GameRegistry.registerBlock(bowlBlock, "bowlBlock");
		GameRegistry.registerTileEntity(BowlBlockTileEntity.class, "bowlBlock");
		saddleStand = new SaddleStandBlock(Material.wood).setBlockName("saddleStandBlock").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(saddleStand, "saddleStandBlock");
		GameRegistry.registerTileEntity(SaddleStandTileEntity.class, "saddleStandBlock");
		horseArmorStand = new HorseArmorStandBlock(Material.wood).setBlockName("horseArmorStandBlock").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerBlock(horseArmorStand, "horseArmorStandBlock");
		GameRegistry.registerTileEntity(HorseArmorStandTileEntity.class, "horseArmorStandBlock");
		
		steakBlock = new SteakBlock(Material.sponge).setBlockName("steakBlock"); // TODO fix block texture
		GameRegistry.registerBlock(steakBlock, "steakBlock");
		GameRegistry.registerTileEntity(SteakTileEntity.class, "steakBlock");
		appleBlock = new AppleBlock(Material.sponge).setBlockName("appleBlock");
		GameRegistry.registerBlock(appleBlock, "appleBlock");
		GameRegistry.registerTileEntity(AppleBlockTileEntity.class, "appleBlock");
		fishBlock = new FishBlock(Material.sponge).setBlockName("fishBlock");
		GameRegistry.registerBlock(fishBlock, "fishBlock");
		GameRegistry.registerTileEntity(FishBlockTileEntity.class, "fishBlock");
		melonBlock = new MelonBlock(Material.sponge).setBlockName("melonBlock");
		GameRegistry.registerBlock(melonBlock, "melonBlock");
		GameRegistry.registerTileEntity(MelonBlockTileEntity.class, "melonBlock");
		
		gunpowderBlock = new GunpowderBlock(Material.sand).setBlockName("gunpowderBlock");
		GameRegistry.registerBlock(gunpowderBlock, "gunpowderBlock");
		GameRegistry.registerTileEntity(GunpowderBlockTileEntity.class, "gunpowderBlock");
		
		enderPearlBlock = new EnderPearlBlock(Material.glass).setBlockName("enderPearlBlock");
		GameRegistry.registerBlock(enderPearlBlock, "enderPearlBlock");
		GameRegistry.registerTileEntity(EnderPearlBlockTileEntity.class, "enderPearlBlock");
		enderEyeBlock = new EnderEyeBlock(Material.glass).setBlockName("enderEyeBlock");
		GameRegistry.registerBlock(enderEyeBlock, "enderEyeBlock");
		GameRegistry.registerTileEntity(EnderEyeBlockTileEntity.class, "enderEyeBlock");
		
		bucketBlock = new BucketBlock(Material.iron).setBlockName("bucketBlock");
		GameRegistry.registerBlock(bucketBlock, "bucketBlock");
		GameRegistry.registerTileEntity(BucketBlockTileEntity.class, "bucketBlock");
	}
}
