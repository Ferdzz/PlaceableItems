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
import com.stuntmania.PlaceableItems.TileEntities.TEEnderPearl;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockEnderPearl extends BlockPlaceableItems {
	private IIcon icon;

	public BlockEnderPearl() {
		super(Material.glass);
		setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.4F, 0.3F + 0.4F);
		setBlockName("enderPearlBlock");
		GameRegistry.registerBlock(this, "enderPearlBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEEnderPearl();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":destroy/ender_pearl");
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}

	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		return Items.ender_pearl;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.ender_pearl);
	}
}
