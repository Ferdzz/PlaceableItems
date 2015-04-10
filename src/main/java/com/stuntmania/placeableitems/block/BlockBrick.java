package com.stuntmania.placeableitems.block;

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

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEBrick;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBrick extends BlockPlaceableItems {

	//TODO: Implement stacking of bricks
	
	private IIcon[] icons = new IIcon[2];

	public BlockBrick() {
		super(Material.wood);
		setBlockBounds(0.125F, 0, 0.125F, 0.875F, 0.1875F, 0.875F);
		setBlockName("brickBlock");
		GameRegistry.registerBlock(this, "brickBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TEBrick();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(PlaceableItems.MODID + ":destroy/brick");
		icons[1] = reg.registerIcon(PlaceableItems.MODID + ":destroy/nether_brick");
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icons[meta];
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		switch (meta) {
			case 0:
				return Items.brick;
			case 1:
				return Items.netherbrick;
			default:
				return null;
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.brick);
		case 1:
			return new ItemStack(Items.netherbrick);
		default:
			return null;
		}
	}
}
