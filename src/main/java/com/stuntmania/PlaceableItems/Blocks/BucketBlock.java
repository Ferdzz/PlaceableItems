package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.BucketBlockTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BucketBlock extends PlaceableItemsBlock {

	private IIcon icon;

	public BucketBlock(Material material) {
		super(material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new BucketBlockTileEntity();
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		setBlockBounds(0.1875F, 0, 0.1875F, 0.1875F + 0.625F, 0.875F, 0.1875F + 0.625F);
		return AxisAlignedBB.getBoundingBox(x + 0.1875, y, z + 0.1875F, x + 0.1875 + 0.625, y + 0.875F, z + 0.1875 + 0.625F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z) {
		setBlockBounds(0.1875F, 0, 0.1875F, 0.1875F + 0.625F, 0.875F, 0.1875F + 0.625F);
		return AxisAlignedBB.getBoundingBox(x + 0.1875, y, z + 0.1875F, x + 0.1875 + 0.625, y + 0.875F, z + 0.1875 + 0.625F);
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
