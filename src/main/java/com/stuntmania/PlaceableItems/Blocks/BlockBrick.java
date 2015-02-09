package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.TEBrick;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBrick extends BlockPlaceableItems {

	private IIcon icon;

	public BlockBrick() {
		super(Material.rock);
		setBlockBounds(0.125F, 0, 0.125F, 0.875F, 0.1875F, 0.875F);
		setBlockName("brickBlock");
		GameRegistry.registerBlock(this, "brickBlock");
		setBlockTextureName(PlaceableItems.MODID + ":destroy/brick");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEBrick();
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		return Items.brick;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.brick);
	}
}
