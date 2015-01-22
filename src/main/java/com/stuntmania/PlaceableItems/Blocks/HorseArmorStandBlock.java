package com.stuntmania.PlaceableItems.Blocks;

import com.stuntmania.PlaceableItems.TileEntities.HorseArmorStandTileEntity;
import com.stuntmania.PlaceableItems.TileEntities.IngotBlockTileEntity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HorseArmorStandBlock extends BlockContainer {

	public HorseArmorStandBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
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
	
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new HorseArmorStandTileEntity();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack itemStack) {
		int facing = MathHelper.floor_double((double) ((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		TileEntity te = world.getTileEntity(i, j, k);
		if (te != null && te instanceof HorseArmorStandTileEntity) {
			HorseArmorStandTileEntity ted = (HorseArmorStandTileEntity) te;
			ted.wasPlaced(entityLiving, itemStack);
			ted.setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
	}
}
