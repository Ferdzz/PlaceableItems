package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.TEMelon;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMelon extends BlockPlaceableItems {
	
	private int clicks = 0;
	
	public BlockMelon() {
		super(Material.sponge);
		setBlockName("melonBlock");
		GameRegistry.registerBlock(this, "melonBlock");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		FoodStats foodStats = player.getFoodStats();
		if (!world.isRemote)
		if (foodStats.needFood()) {
			clicks++;
			TEMelon entity = (TEMelon) world.getTileEntity(x, y, z);
			int facing = entity.getFacing();
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
					foodStats.setFoodLevel(foodStats.getFoodLevel() + 2);
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
		return new TEMelon();
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.melon;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.melon);
	}
	
}
