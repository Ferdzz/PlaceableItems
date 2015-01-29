package com.stuntmania.PlaceableItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.stuntmania.PlaceableItems.Blocks.*;
import com.stuntmania.PlaceableItems.Proxy.CommonProxy;
import com.stuntmania.PlaceableItems.TileEntities.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = PlaceableItems.MODID, version = PlaceableItems.VERSION)
public class PlaceableItems {
	@SidedProxy(clientSide = "com.stuntmania.PlaceableItems.Proxy.ClientProxy", serverSide = "com.stuntmania.PlaceableItems.Proxy.CommonProxy")
	public static CommonProxy proxy;
	@Instance
	public static PlaceableItems instance;

	public static final String NAME = "Placeable Items Mod";
	public static final String MODID = "placeableitems";
	public static final String VERSION = "1.0";

	public static Block ingotBlock;
	public static Block bowlBlock;
	public static Block saddleStand;
	public static Block horseArmorStand;
	public static Block steakBlock;
	public static Block appleBlock;

	public static Block gunpowderBlock;
	
	public static Block enderPearlBlock;
	public static Block enderEyeBlock;
	
	public static Block bucketBlock;

	public static Item blackBowl;
	public static Item redBowl;
	public static Item greenBowl;
	public static Item brownBowl;
	public static Item blueBowl;
	public static Item purpleBowl;
	public static Item cyanBowl;
	public static Item lightGrayBowl;
	public static Item grayBowl;
	public static Item pinkBowl;
	public static Item limeBowl;
	public static Item yellowBowl;
	public static Item lightBlueBowl;
	public static Item magentaBowl;
	public static Item orangeBowl;
	public static Item whiteBowl;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println("Started loading " + MODID + " version " + VERSION);

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
		steakBlock = new SteakBlock(Material.sponge).setBlockName("steakBlock");
		GameRegistry.registerBlock(steakBlock, "steakBlock");
		GameRegistry.registerTileEntity(SteakTileEntity.class, "steakBlock");
		appleBlock = new AppleBlock(Material.sponge).setBlockName("appleBlock");
		GameRegistry.registerBlock(appleBlock, "appleBlock");
		GameRegistry.registerTileEntity(AppleBlockTileEntity.class, "appleBlock");
		
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
		
		blackBowl = new Item().setUnlocalizedName("blackBowl").setTextureName(MODID + ":blackBowl").setCreativeTab(CreativeTabs.tabDecorations);
		redBowl = new Item().setUnlocalizedName("redBowl").setTextureName(MODID + ":redBowl").setCreativeTab(CreativeTabs.tabDecorations);
		greenBowl = new Item().setUnlocalizedName("greenBowl").setTextureName(MODID + ":greenBowl").setCreativeTab(CreativeTabs.tabDecorations);
		brownBowl = new Item().setUnlocalizedName("brownBowl").setTextureName(MODID + ":brownBowl").setCreativeTab(CreativeTabs.tabDecorations);
		blueBowl = new Item().setUnlocalizedName("blueBowl").setTextureName(MODID + ":blueBowl").setCreativeTab(CreativeTabs.tabDecorations);
		purpleBowl = new Item().setUnlocalizedName("purpleBowl").setTextureName(MODID + ":purpleBowl").setCreativeTab(CreativeTabs.tabDecorations);
		cyanBowl = new Item().setUnlocalizedName("cyanBowl").setTextureName(MODID + ":cyanBowl").setCreativeTab(CreativeTabs.tabDecorations);
		lightGrayBowl = new Item().setUnlocalizedName("lightGrayBowl").setTextureName(MODID + ":lightGrayBowl").setCreativeTab(CreativeTabs.tabDecorations);
		grayBowl = new Item().setUnlocalizedName("grayBowl").setTextureName(MODID + ":grayBowl").setCreativeTab(CreativeTabs.tabDecorations);
		pinkBowl = new Item().setUnlocalizedName("pinkBowl").setTextureName(MODID + ":pinkBowl").setCreativeTab(CreativeTabs.tabDecorations);
		limeBowl = new Item().setUnlocalizedName("limeBowl").setTextureName(MODID + ":limeBowl").setCreativeTab(CreativeTabs.tabDecorations);
		yellowBowl = new Item().setUnlocalizedName("yellowBowl").setTextureName(MODID + ":yellowBowl").setCreativeTab(CreativeTabs.tabDecorations);
		lightBlueBowl = new Item().setUnlocalizedName("lightBlueBowl").setTextureName(MODID + ":lightBlueBowl").setCreativeTab(CreativeTabs.tabDecorations);
		magentaBowl = new Item().setUnlocalizedName("magentaBowl").setTextureName(MODID + ":magentaBowl").setCreativeTab(CreativeTabs.tabDecorations);
		orangeBowl = new Item().setUnlocalizedName("orangeBowl").setTextureName(MODID + ":orangeBowl").setCreativeTab(CreativeTabs.tabDecorations);
		whiteBowl = new Item().setUnlocalizedName("whiteBowl").setTextureName(MODID + ":whiteBowl").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerItem(blackBowl, "blackBowl");
		GameRegistry.registerItem(redBowl, "redBowl");
		GameRegistry.registerItem(greenBowl, "greenBowl");
		GameRegistry.registerItem(brownBowl, "brownBowl");
		GameRegistry.registerItem(blueBowl, "blueBowl");
		GameRegistry.registerItem(purpleBowl, "purpleBowl");
		GameRegistry.registerItem(cyanBowl, "cyanBowl");
		GameRegistry.registerItem(lightGrayBowl, "lightGrayBowl");
		GameRegistry.registerItem(grayBowl, "grayBowl");
		GameRegistry.registerItem(pinkBowl, "pinkBowl");
		GameRegistry.registerItem(limeBowl, "limeBowl");
		GameRegistry.registerItem(yellowBowl, "yellowBowl");
		GameRegistry.registerItem(lightBlueBowl, "lightBlueBowl");
		GameRegistry.registerItem(magentaBowl, "magentaBowl");
		GameRegistry.registerItem(orangeBowl, "orangeBowl");
		GameRegistry.registerItem(whiteBowl, "whiteBowl");

		GameRegistry.addShapelessRecipe(new ItemStack(blackBowl), Items.bowl, new ItemStack(Items.dye, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(redBowl), Items.bowl, new ItemStack(Items.dye, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(greenBowl), Items.bowl, new ItemStack(Items.dye, 1, 2));
		GameRegistry.addShapelessRecipe(new ItemStack(brownBowl), Items.bowl, new ItemStack(Items.dye, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(blueBowl), Items.bowl, new ItemStack(Items.dye, 1, 4));
		GameRegistry.addShapelessRecipe(new ItemStack(purpleBowl), Items.bowl, new ItemStack(Items.dye, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(cyanBowl), Items.bowl, new ItemStack(Items.dye, 1, 6));
		GameRegistry.addShapelessRecipe(new ItemStack(lightGrayBowl), Items.bowl, new ItemStack(Items.dye, 1, 7));
		GameRegistry.addShapelessRecipe(new ItemStack(grayBowl), Items.bowl, new ItemStack(Items.dye, 1, 8));
		GameRegistry.addShapelessRecipe(new ItemStack(pinkBowl), Items.bowl, new ItemStack(Items.dye, 1, 9));
		GameRegistry.addShapelessRecipe(new ItemStack(limeBowl), Items.bowl, new ItemStack(Items.dye, 1, 10));
		GameRegistry.addShapelessRecipe(new ItemStack(yellowBowl), Items.bowl, new ItemStack(Items.dye, 1, 11));
		GameRegistry.addShapelessRecipe(new ItemStack(lightBlueBowl), Items.bowl, new ItemStack(Items.dye, 1, 12));
		GameRegistry.addShapelessRecipe(new ItemStack(magentaBowl), Items.bowl, new ItemStack(Items.dye, 1, 13));
		GameRegistry.addShapelessRecipe(new ItemStack(orangeBowl), Items.bowl, new ItemStack(Items.dye, 1, 14));
		GameRegistry.addShapelessRecipe(new ItemStack(whiteBowl), Items.bowl, new ItemStack(Items.dye, 1, 15));
		//TODO Add crafting recipes for Armor Stand && Saddle Stand
		proxy.registerRenderers();
		MinecraftForge.EVENT_BUS.register(instance);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("Loaded " + MODID + " version " + VERSION + " correctly");
	}

	@SuppressWarnings("incomplete-switch")
	@SubscribeEvent
	public void rightClick(PlayerInteractEvent event) {
	    	boolean c = event.entityPlayer.capabilities.isCreativeMode;
	    	ItemStack equip = event.entityPlayer.getCurrentEquippedItem();
		if (!event.world.isRemote)
			switch (event.action) {
			case RIGHT_CLICK_AIR: //TODO: bucket still places fluid blocks
				if (event.entityPlayer.isSneaking() && equip != null
				&& (equip.getItem().equals(Items.ender_eye)
				|| equip.getItem().equals(Items.ender_pearl)
				|| equip.getItem().equals(Items.bucket)
				|| equip.getItem().equals(Items.water_bucket)
				|| equip.getItem().equals(Items.lava_bucket))) {
				    event.setCanceled(true);
				}

			case RIGHT_CLICK_BLOCK:
				if (event.entityPlayer.isSneaking() && equip != null
				&& (equip.getItem().equals(Items.ender_eye)
				|| equip.getItem().equals(Items.ender_pearl)
				|| equip.getItem().equals(Items.bucket)
				|| equip.getItem().equals(Items.water_bucket)
				|| equip.getItem().equals(Items.lava_bucket))) {
				    event.setCanceled(true);
				}

				// TODO change the logic so that the player can only place blocks if shifting (to bypass inventory interactions such as opening a chest) (I will handle it)
				if (equip != null) {
					// Placeable ingots
					if (equip.getItem().equals(Items.iron_ingot)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ingotBlock, 0, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					} else if (equip.getItem().equals(Items.gold_ingot)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ingotBlock, 1, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					}
					
					
					// Gunpowder
					if (equip.getItem().equals(Items.gunpowder) && event.entityPlayer.isSneaking())
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, gunpowderBlock, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					// Ender pearl
					if (equip.getItem().equals(Items.ender_pearl) && event.entityPlayer.isSneaking())
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, enderPearlBlock, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					// Ender eye
					if (equip.getItem().equals(Items.ender_eye) && event.entityPlayer.isSneaking())
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, enderEyeBlock, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					
					// Buckets
					if (equip.getItem().equals(Items.bucket) && event.entityPlayer.isSneaking())
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, bucketBlock, 0, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;
					if (equip.getItem().equals(Items.water_bucket) && event.entityPlayer.isSneaking())
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, bucketBlock, 1, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;
					if (equip.getItem().equals(Items.lava_bucket) && event.entityPlayer.isSneaking())
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, bucketBlock, 2, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;
					
					
					//Food
					if(equip.getItem().equals(Items.apple) && event.entityPlayer.isSneaking())
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, appleBlock, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					
					// Bowls
					if (equip.getItem().getUnlocalizedName().endsWith("Bowl")) {
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, bowlBlock, event.world, event.entityPlayer)) {
							if (equip.getItem().equals(Items.bowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(0);
							else if (equip.getItem().equals(blackBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(1);
							else if (equip.getItem().equals(redBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(2);
							else if (equip.getItem().equals(greenBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(3);
							else if (equip.getItem().equals(brownBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(4);
							else if (equip.getItem().equals(blueBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(5);
							else if (equip.getItem().equals(purpleBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(6);
							else if (equip.getItem().equals(cyanBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(7);
							else if (equip.getItem().equals(lightGrayBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(8);
							else if (equip.getItem().equals(grayBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(9);
							else if (equip.getItem().equals(pinkBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(10);
							else if (equip.getItem().equals(limeBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(11);
							else if (equip.getItem().equals(yellowBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(12);
							else if (equip.getItem().equals(lightBlueBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(13);
							else if (equip.getItem().equals(magentaBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(14);
							else if (equip.getItem().equals(orangeBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(15);
							else if (equip.getItem().equals(whiteBowl))
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(16);
							else
								((BowlBlockTileEntity) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(0);
							if (!c) equip.stackSize--;
						}
					}
					
				} // end of != null if
			} // end of switch statement
	} // end of rightClick event

	public static void spawnItem(World world, double x, double y, double z, Item itemSpawn) {
		if (!world.isRemote) {
			EntityItem item = new EntityItem(world, x, y, z, new ItemStack(itemSpawn));
			world.spawnEntityInWorld(item);
		}
	}

	public static boolean placeBlockWithMetadata(int x, int y, int z, int face, Block block, int metadata, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
				world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
				world.setBlockMetadataWithNotify(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, metadata, 2 | 1);
				block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
				return true;
			}
		return false;
	}

	public static boolean placeBlockWithoutMetadata(int x, int y, int z, int face, Block block, World world, EntityPlayer player) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.air)
			if (player.canPlayerEdit(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, face, player.getCurrentEquippedItem())) {
				world.setBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, block);
				block.onBlockPlacedBy(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, player, player.getCurrentEquippedItem());
				return true;
			}
		return false;
	}

	public static TileEntity getTileEntityFromFace(int x, int y, int z, World world, int face) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		TileEntity entity = world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
		return entity;
	}
}
