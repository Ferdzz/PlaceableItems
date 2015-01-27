package com.stuntmania.PlaceableItems.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.TileEntities.PlaceableItemsTileEntity;

public abstract class PlaceableItemsBlock extends BlockContainer {
	
	protected PlaceableItemsBlock(Material material) {
		super(material);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
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
    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        super.harvestBlock(world, player, x, y, z, meta);
        world.setBlockToAir(x, y, z);
    }
    
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		if (willHarvest) return true;
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack itemStack) {
		int facing = MathHelper.floor_double((double) ((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		TileEntity te = world.getTileEntity(i, j, k);
		if (te != null && te instanceof PlaceableItemsTileEntity) {
			PlaceableItemsTileEntity ted = (PlaceableItemsTileEntity) te;
			ted.wasPlaced(entityLiving, itemStack);
			ted.setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
	}

	abstract public TileEntity createNewTileEntity(World world, int par2);
}
