package com.stuntmania.placeableitems.handler;

import com.stuntmania.placeableitems.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketPlaceHandler
{	
	@SubscribeEvent
	public void onUseBucket(FillBucketEvent event)
	{		
		int slot = event.entityPlayer.inventory.currentItem;
		ItemStack held = event.entityPlayer.inventory.getStackInSlot(slot);
		
		if (event.entityPlayer.isSneaking()) {
			
		if (held.getItem().equals(Items.water_bucket) || held.getItem().equals(Items.lava_bucket) || held.getItem().equals(Items.bucket))
			event.setCanceled(true);
		}
		else
		{
			if (event.target.typeOfHit == MovingObjectType.BLOCK)
			{
				int x = event.target.blockX;
				int y = event.target.blockY;
				int z = event.target.blockZ;
				
				Block target = event.world.getBlock(x, y, z);
				
				int amount = held.stackSize;
				
				if (target == ModBlocks.bucket) {
					int meta = event.world.getBlockMetadata(x, y, z);
					switch (meta) {
					case 0: //Empty Bucket
						if (held.getItem() == Items.water_bucket) {
							if (!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(Items.bucket));
							event.world.setBlockMetadataWithNotify(x, y, z, 1, 3);
							event.setCanceled(true);
						}
						if (held.getItem() == Items.lava_bucket) {
							if (!event.entityPlayer.capabilities.isCreativeMode)
								event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack (Items.bucket));
							event.world.setBlockMetadataWithNotify(x, y, z, 2, 3);
							event.setCanceled(true);
						}
						break;
					case 1: //Water Bucket
						if (held.getItem() == Items.bucket) {
							if (!event.entityPlayer.capabilities.isCreativeMode)
								if (amount == 1)
									event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(Items.water_bucket));
								else {
									event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack (Items.bucket, amount - 1));
									if (!event.world.isRemote)
										event.world.spawnEntityInWorld(new EntityItem(event.world, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, new ItemStack(Items.water_bucket)));
								}
							event.world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						}
						break;
					case 2: //Lava Bucket
						if (held.getItem() == Items.bucket) {
							if (!event.entityPlayer.capabilities.isCreativeMode)
								if (amount == 1)
									event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(Items.lava_bucket));
								else {
									event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack (Items.bucket, amount - 1));
									if (!event.world.isRemote)
										event.world.spawnEntityInWorld(new EntityItem(event.world, event.entityPlayer.posX, event.entityPlayer.posY, event.entityPlayer.posZ, new ItemStack(Items.lava_bucket)));
								}
							event.world.setBlockMetadataWithNotify(x, y, z, 0, 3);
						break;
						}
					}
				}
			}
		}
	}
}
