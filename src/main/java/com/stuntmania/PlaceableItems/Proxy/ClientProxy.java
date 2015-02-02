package com.stuntmania.PlaceableItems.Proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Renderers.AppleBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.BowlBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.BucketBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.EnderEyeBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.EnderPearlBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.FishBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.GunpowderBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.HorseArmorStandRenderer;
import com.stuntmania.PlaceableItems.Renderers.IngotBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.MelonBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.SaddleStandBlockRenderer;
import com.stuntmania.PlaceableItems.Renderers.SteakBlockRenderer;
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

import cpw.mods.fml.client.registry.ClientRegistry;


public class ClientProxy extends CommonProxy {

	IngotBlockRenderer ingotBlockRenderer;
	BowlBlockRenderer bowlBlockRenderer;
	SaddleStandBlockRenderer saddleStandBlockRenderer;
	HorseArmorStandRenderer horseArmorStandRenderer;
	
	SteakBlockRenderer steakBlockRenderer;
	AppleBlockRenderer appleBlockRenderer;
	FishBlockRenderer fishBlockRenderer;
	MelonBlockRenderer melonBlockRenderer;
	
	EnderPearlBlockRenderer enderPearlBlockRenderer;
	EnderEyeBlockRenderer enderEyeBlockRenderer;
	
	GunpowderBlockRenderer gunpowderBlockRenderer;
	
	BucketBlockRenderer bucketBlockRenderer;
	
	@Override
	public void registerRenderers() {
		ingotBlockRenderer = new IngotBlockRenderer();
		bowlBlockRenderer = new BowlBlockRenderer();
		saddleStandBlockRenderer = new SaddleStandBlockRenderer();
		horseArmorStandRenderer = new HorseArmorStandRenderer();

		steakBlockRenderer = new SteakBlockRenderer();
		appleBlockRenderer = new AppleBlockRenderer();
		fishBlockRenderer = new FishBlockRenderer();
		melonBlockRenderer = new MelonBlockRenderer();
		
		enderPearlBlockRenderer = new EnderPearlBlockRenderer();
		enderEyeBlockRenderer = new EnderEyeBlockRenderer();
		
		gunpowderBlockRenderer = new GunpowderBlockRenderer();
		
		bucketBlockRenderer = new BucketBlockRenderer();
		
		ClientRegistry.bindTileEntitySpecialRenderer(IngotBlockTileEntity.class, ingotBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(BowlBlockTileEntity.class, bowlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(SaddleStandTileEntity.class, saddleStandBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(HorseArmorStandTileEntity.class, horseArmorStandRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(SteakTileEntity.class, steakBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(AppleBlockTileEntity.class, appleBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(FishBlockTileEntity.class, fishBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(MelonBlockTileEntity.class, melonBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(EnderPearlBlockTileEntity.class, enderPearlBlockRenderer);
		ClientRegistry.bindTileEntitySpecialRenderer(EnderEyeBlockTileEntity.class, enderEyeBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(GunpowderBlockTileEntity.class, gunpowderBlockRenderer);
		
		ClientRegistry.bindTileEntitySpecialRenderer(BucketBlockTileEntity.class, bucketBlockRenderer);
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.saddleStand), saddleStandBlockRenderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(PlaceableItems.horseArmorStand), horseArmorStandRenderer);
	}
}
