package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.TEBucket;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBucket extends BlockPlaceableItems {

	private IIcon icon;

	public BlockBucket() {
		super(Material.iron);
		setBlockBounds(0.1875F, 0, 0.1875F, 0.1875F + 0.625F, 0.875F, 0.1875F + 0.625F);
		setBlockName("bucketBlock");
		GameRegistry.registerBlock(this, "bucketBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEBucket();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":destroy/bucket");
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		switch (meta) {
		case 0:
			return Items.bucket;
		case 1:
			return Items.water_bucket;
		case 2:
			return Items.lava_bucket;
		default:
			return null;
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.bucket);
		case 1:
			return new ItemStack(Items.water_bucket);
		case 2:
			return new ItemStack(Items.lava_bucket);
		default:
			return null;
		}
	}
}
