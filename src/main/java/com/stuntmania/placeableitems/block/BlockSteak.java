package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TESteak;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSteak extends BlockPlaceableItems {

	IIcon icon;
	
	private int clicks = 0;
	
	public BlockSteak() {
		super(Material.sponge);
		setBlockBounds(0, 0, 0, 1, 0.1F, 1);
		setBlockName("steakBlock");
		GameRegistry.registerBlock(this, "steakBlock");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		FoodStats foodStats = player.getFoodStats();
		if (!world.isRemote)
			if (foodStats.needFood()) {
				clicks++;
				TESteak entity = (TESteak) world.getTileEntity(x, y, z);
				int facing = entity.getFacing();
				switch (clicks) {
				case 0:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 1:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 2:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 3:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 4:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 5:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 6:
					world.playSoundAtEntity(player, "random.eat", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					entity.setFacing(facing);
					break;
				case 7:
					world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
					foodStats.setFoodLevel(foodStats.getFoodLevel() + 8);
					if (foodStats.getFoodLevel() > 20)
						foodStats.setFoodLevel(20);
					foodStats.setFoodSaturationLevel(foodStats.getSaturationLevel() + 0.8F);
					world.setBlockToAir(x, y, z);
					clicks = 0;
					break;
				}
				return true;
		}
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TESteak();
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Items.cooked_beef;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.cooked_beef);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":steak");
	}
	
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return icon;
	}
}
