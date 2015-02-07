package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.PumpkinPieBlockTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public class PumpkinPieBlock extends PlaceableItemsBlock {
	
	private int clicks = 0;
	
	public PumpkinPieBlock() {
		super(Material.sponge);
		setBlockTextureName(PlaceableItems.MODID + ":destroy/pumpkinPieBlock");
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		setBlockName("pumpkinPieBlock");
		GameRegistry.registerBlock(this, "pumpkinPieBlock");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		FoodStats foodStats = player.getFoodStats();
		if (foodStats.needFood()) {
			clicks++;
			PumpkinPieBlockTileEntity entity = (PumpkinPieBlockTileEntity) world.getTileEntity(x, y, z);
			int facing = entity.getFacing();
			player.addChatMessage(new ChatComponentText(String.valueOf(clicks)));
			switch (clicks) {
				case 0: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 1: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 2: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 3: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 4: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 5: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 6: 
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F); entity.setFacing(facing); break;
				case 7:
					world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					foodStats.setFoodLevel(foodStats.getFoodLevel() + 8);
					if (foodStats.getFoodLevel() > 20) foodStats.setFoodLevel(20);
					foodStats.setFoodSaturationLevel(foodStats.getSaturationLevel() + 0.3F);
					world.setBlockToAir(x, y, z);
					clicks = 0;
					break;
			}
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new PumpkinPieBlockTileEntity();
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.pumpkin_pie;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.pumpkin_pie);
	}
}
