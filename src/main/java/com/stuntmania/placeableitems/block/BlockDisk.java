package com.stuntmania.placeableitems.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.tileentity.TEDisk;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockDisk extends BlockPlaceableItems {
	
	private IIcon icon;
	
	public BlockDisk() {
		super(Material.sponge);
		setBlockBounds(0.2F, 0, 0.2F, 0.8F, 0.1F, 0.8F);
		setBlockName("diskBlock");
		GameRegistry.registerBlock(this, "diskBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEDisk();
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		TEDisk entity = (TEDisk) world.getTileEntity(x, y, z);
		switch (entity.getState()) {
		case 0:
			return new ItemStack(Items.record_13);
		case 1:
			return new ItemStack(Items.record_cat);
		case 2:
			return new ItemStack(Items.record_blocks);
		case 3:
			return new ItemStack(Items.record_chirp);
		case 4:
			return new ItemStack(Items.record_far);
		case 5:
			return new ItemStack(Items.record_mall);
		case 6:
			return new ItemStack(Items.record_mellohi);
		case 7:
			return new ItemStack(Items.record_stal);
		case 8:
			return new ItemStack(Items.record_strad);
		case 9:
			return new ItemStack(Items.record_ward);
		case 10:
			return new ItemStack(Items.record_11);
		case 11:
			return new ItemStack(Items.record_wait);
		}
		return null;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TEDisk entity = (TEDisk) world.getTileEntity(x, y, z);
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		switch (entity.getState()) {
		case 0:
			drop.add(new ItemStack(Items.record_13));
			break;
		case 1:
			drop.add(new ItemStack(Items.record_cat));
			break;
		case 2:
			drop.add(new ItemStack(Items.record_blocks));
			break;
		case 3:
			drop.add(new ItemStack(Items.record_chirp));
			break;
		case 4:
			drop.add(new ItemStack(Items.record_far));
			break;
		case 5:
			drop.add(new ItemStack(Items.record_mall));
			break;
		case 6:
			drop.add(new ItemStack(Items.record_mellohi));
			break;
		case 7:
			drop.add(new ItemStack(Items.record_stal));
			break;
		case 8:
			drop.add(new ItemStack(Items.record_strad));
			break;
		case 9:
			drop.add(new ItemStack(Items.record_ward));
			break;
		case 10:
			drop.add(new ItemStack(Items.record_11));
			break;
		case 11:
			drop.add(new ItemStack(Items.record_wait));
			break;
		}
		return drop;
	}
	
	//TODO: Fix the destroy particles
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon("minecraft" + ":record_11");
	}
	
	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
		return icon;
	}
}
