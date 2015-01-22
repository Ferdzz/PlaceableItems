package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;

public class IngotBlock extends BlockContainer {

	private IIcon[] icons = new IIcon[2];

	public IngotBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new IngotBlockTileEntity();
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F, 0.1F, 0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}

	// TODO FIX IT
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0F, 0.345F, 0.3F + 0.4F, 0.1F, 0.3F + 0.3F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.345F, x + 0.3 + 0.4, y + 0.1F, z + 0.3 + 0.3F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(PlaceableItems.MODID + ":ironBlock");
		icons[1] = reg.registerIcon(PlaceableItems.MODID + ":goldBlock");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int p_149650_3_) {
		switch (meta) {
		case 0:
			return Items.iron_ingot;
		case 1:
			return Items.gold_ingot;
		default:
			return null;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack itemStack) {
		int facing = MathHelper.floor_double((double) ((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		TileEntity te = world.getTileEntity(i, j, k);
		if (te != null && te instanceof IngotBlockTileEntity) {
			IngotBlockTileEntity ted = (IngotBlockTileEntity) te;
			ted.wasPlaced(entityLiving, itemStack);
			ted.setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
	}
}
