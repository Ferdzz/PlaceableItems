package com.stuntmania.placeableitems.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.stuntmania.placeableitems.init.ModBlocks;
import com.stuntmania.placeableitems.init.ModItems;
import com.stuntmania.placeableitems.tileentity.TEBowl;
import com.stuntmania.placeableitems.utils.WorldUtils;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class RightClickHandler {

	/**
	 * List of placeable items
	 */
	private Item[] placeableItems = {
			 Items.ender_eye,
			 Items.ender_pearl,
			 Items.bucket,
			 Items.water_bucket,
			 Items.lava_bucket,
			 Items.egg,
			 Items.iron_ingot,
			 Items.gold_ingot,
			 Items.snowball,
			 Items.record_13,
			 Items.record_cat,
			 Items.record_blocks,
			 Items.record_chirp,
			 Items.record_far,
			 Items.record_mall,
			 Items.record_mellohi,
			 Items.record_stal,
			 Items.record_strad,
			 Items.record_ward,
			 Items.record_11,
			 Items.record_wait,
			 Items.bone
	};
	
	private Item[] placeableItemsAirOnly = {	
	};
	
	private Item[] placeableItemsBlockOnly = {	
	};
	
	@SuppressWarnings("incomplete-switch")
	@SubscribeEvent
	public void rightClick(PlayerInteractEvent event) {
    	boolean c = event.entityPlayer.capabilities.isCreativeMode;
    	ItemStack equip = event.entityPlayer.getCurrentEquippedItem();
		if (!event.world.isRemote)
			switch (event.action) {
			case RIGHT_CLICK_AIR:
				handleRightClickAir(event);
				break;
				
			case RIGHT_CLICK_BLOCK:
			{
				handleRightClickBlock(event);
				
				//Stacked Ingots
				if (equip != null && event.entityPlayer.isSneaking() && event.world.getBlock(event.x, event.y, event.z).equals(ModBlocks.ingot)) {
					if (!event.world.isRemote) {
						if (equip.getItem().equals(Items.iron_ingot)) {
							switch (event.world.getBlockMetadata(event.x, event.y, event.z)) {
							case 0:
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 2, 3);
								if (!c) equip.stackSize--;
								break;
							case 2:
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 4, 3);
								if (!c) equip.stackSize--;
								break;
							case 4: 
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 6, 3);
								if (!c) equip.stackSize--;
								break;
							}
						}
						if (equip.getItem().equals(Items.gold_ingot)) {
							switch (event.world.getBlockMetadata(event.x, event.y, event.z)) {
							case 1: 
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 3, 3);
								if (!c) equip.stackSize--;
								break;
							case 3: 
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 5, 3);
								if (!c) equip.stackSize--;
								break;
							case 5: 
								event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 7, 3);
								if (!c) equip.stackSize--;
								break;
							}
						}
					}
					break;
				}
				
				if (event.world.getBlock(event.x, event.y, event.z).equals(ModBlocks.ingot) && !event.world.isRemote && !event.entityPlayer.isSneaking()) {
					switch (event.world.getBlockMetadata(event.x, event.y, event.z)) {
					case 0: 
							event.world.setBlockToAir(event.x, event.y, event.z);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 1)));
						break;
					case 1: 
							event.world.setBlockToAir(event.x, event.y, event.z);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 1)));
						break;
					case 2: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 0, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 1)));
						break;
					case 3: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 1, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 1)));
						break;
					case 4: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 2, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 1)));
						break;
					case 5: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 3, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 1)));
						break;
					case 6: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 4, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.iron_ingot, 1)));
						break;
					case 7: 
							event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 5, 3);
							if (!c) event.world.spawnEntityInWorld(new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(Items.gold_ingot, 1)));
						break;
					}
				}
				
				if (equip != null && event.entityPlayer.isSneaking() && getBlockFromFace(event.x, event.y, event.z, event.world, event.face).equals(Blocks.air)) {
					// Ingots
					if (equip.getItem().equals(Items.iron_ingot)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.ingot, 0, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					} else if (equip.getItem().equals(Items.gold_ingot)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.ingot, 1, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					}
					
					//Bricks
					if (equip.getItem().equals(Items.brick)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.brick, 0, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					} else if (equip.getItem().equals(Items.netherbrick)) {
						if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.brick, 1, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					}
					
					//Bone
					if(equip.getItem().equals(Items.bone)) {
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.bone, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					}
					
					//Book
					if (equip.getItem().equals(Items.book)) {
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.book, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					}
					
					// Gunpowder
					if (equip.getItem().equals(Items.gunpowder))
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.gunpowder, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					
					// Snowball
					if (equip.getItem().equals(Items.snowball))
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.snowball, event.world, event.entityPlayer))
							if (!c) equip.stackSize--;
					
					// Ender pearl
					if (equip.getItem().equals(Items.ender_pearl))
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.ender_pearl, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					// Ender eye
					if (equip.getItem().equals(Items.ender_eye))
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.ender_eye, event.world, event.entityPlayer))
						    if (!c) equip.stackSize--;
					
					// Buckets
					if (equip.getItem().equals(Items.bucket))
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.bucket, 0, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;
					if (equip.getItem().equals(Items.water_bucket))
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.bucket, 1, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;
					if (equip.getItem().equals(Items.lava_bucket))
					    if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.bucket, 2, event.world, event.entityPlayer))
					    	if (!c) equip.stackSize--;	
					
					//Food
					if(equip.getItem().equals(Items.apple))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.apple, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.cooked_fished)) 
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.fish, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.melon))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.melon, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.egg))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.egg, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.pumpkin_pie))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.pumpkin_pie, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.cooked_beef))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.steak, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					if(equip.getItem().equals(Items.chicken))
						if(placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.chicken, event.world, event.entityPlayer))
							if(!c) equip.stackSize--;
					
					// Bowls
					if (equip.getItem().getUnlocalizedName().endsWith("Bowl") || equip.getItem().getUnlocalizedName().endsWith("bowl")) {
						if (placeBlockWithoutMetadata(event.x, event.y, event.z, event.face, ModBlocks.bowl, event.world, event.entityPlayer)) {
							if (equip.getItem().equals(Items.bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(0);
							else if (equip.getItem().equals(ModItems.black_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(1);
							else if (equip.getItem().equals(ModItems.red_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(2);
							else if (equip.getItem().equals(ModItems.green_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(3);
							else if (equip.getItem().equals(ModItems.brown_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(4);
							else if (equip.getItem().equals(ModItems.blue_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(5);
							else if (equip.getItem().equals(ModItems.purple_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(6);
							else if (equip.getItem().equals(ModItems.cyan_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(7);
							else if (equip.getItem().equals(ModItems.light_gray_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(8);
							else if (equip.getItem().equals(ModItems.gray_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(9);
							else if (equip.getItem().equals(ModItems.pink_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(10);
							else if (equip.getItem().equals(ModItems.lime_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(11);
							else if (equip.getItem().equals(ModItems.yellow_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(12);
							else if (equip.getItem().equals(ModItems.light_blue_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(13);
							else if (equip.getItem().equals(ModItems.magenta_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(14);
							else if (equip.getItem().equals(ModItems.orange_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(15);
							else if (equip.getItem().equals(ModItems.white_bowl))
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(16);
							else
								((TEBowl) getTileEntityFromFace(event.x, event.y, event.z, event.world, event.face)).setState(0);
							if (!c) equip.stackSize--;
						}
					}
					
					if(equip.getItem().getUnlocalizedName().endsWith("record")) {
						if (equip.getItem().equals(Items.record_13))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 0, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_cat))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 1, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_blocks))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 2, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_chirp))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 3, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_far))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 4, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_mall))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 5, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_mellohi))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 6, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_stal))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 7, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_strad))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 8, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_ward))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 9, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_11))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 10, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
						if (equip.getItem().equals(Items.record_wait))
							if (placeBlockWithMetadata(event.x, event.y, event.z, event.face, ModBlocks.disk, 11, event.world, event.entityPlayer))
								if (!c) equip.stackSize--;
					}
				} // end of != null if
			}// end of case RIGHT_CLICK_BLOCK
		}// end of switch statement
	} // end of rightClick event

	private void handleRightClickBlock(PlayerInteractEvent event) {
		if(event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.isSneaking()) {
			for (int i = 0; i < placeableItems.length; i++) {
				if(event.entityPlayer.getCurrentEquippedItem().equals(placeableItems[i])) {
					event.setCanceled(true);
					return;
				}
			}
			
			for (int i = 0; i < placeableItemsBlockOnly.length; i++) {
				if(event.entityPlayer.getCurrentEquippedItem().equals(placeableItemsBlockOnly[i])) {
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	private void handleRightClickAir(PlayerInteractEvent event) {
		if(event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.isSneaking()) {
			for (int i = 0; i < placeableItems.length; i++) {
				if(event.entityPlayer.getCurrentEquippedItem().equals(placeableItems[i])) {
					event.setCanceled(true);
					return;
				}
			}
			
			for (int i = 0; i < placeableItemsAirOnly.length; i++) {
				if(event.entityPlayer.getCurrentEquippedItem().equals(placeableItemsAirOnly[i])) {
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	public static TileEntity getTileEntityFromFace(int x, int y, int z, World world, int face) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		return world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
	}
	
	public static Block getBlockFromFace(int x, int y, int z, World world, int face) {
		ForgeDirection direction = ForgeDirection.getOrientation(face);
		return world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
	}
	
	public static boolean placeBlockWithoutMetadata(int x, int y, int z, int face, Block block, World world, EntityPlayer player) {
		return WorldUtils.placeBlockWithoutMetadata(x, y, z, face, block, world, player);
	}

	public static boolean placeBlockWithMetadata(int x, int y, int z, int face, Block block, int metadata, World world, EntityPlayer player) {
		return WorldUtils.placeBlockWithMetadata(x, y, z, face, block, metadata, world, player);
	}
}
