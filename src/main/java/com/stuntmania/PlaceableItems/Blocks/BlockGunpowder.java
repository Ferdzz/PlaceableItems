package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.TEGunpowder;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockGunpowder extends BlockPlaceableItems {

	private IIcon icon;

	public BlockGunpowder() {
		super(Material.sand);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.71875F, 0.125F, 0.71875F);
		GameRegistry.registerBlock(this, "gunpowderBlock");
		setBlockName("gunpowderBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEGunpowder();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":destroy/gunpowder");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return icon;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int side) {
		return Items.gunpowder;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		return new ItemStack(Items.gunpowder);
	}

	@Override
	public boolean canDropFromExplosion(Explosion exp) {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem().equals(Items.flint_and_steel) && !player.isSneaking()) {
			world.createExplosion(null, x + 0.5D, y + 0.5D, z + 0.5D, 0.5F, true);
			return true;
		}
		return false;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
		world.createExplosion(null, x + 0.5D, y + 0.5D, z + 0.5D, 0.5F, true);
	}
}
