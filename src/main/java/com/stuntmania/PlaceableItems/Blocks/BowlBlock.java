package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.BowlBlockTileEntity;

public class BowlBlock extends PlaceableItemsBlock {

	private IIcon icon;

	public BowlBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new BowlBlockTileEntity();
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.3F, 0.3F + 0.4F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.3F, x + 0.3 + 0.4, y + 0.3F, z + 0.3 + 0.4F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int x, int y, int z) {
		setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.3F, 0.3F + 0.4F);
		return AxisAlignedBB.getBoundingBox(x + 0.3, y, z + 0.3F, x + 0.3 + 0.4, y + 0.3F, z + 0.3 + 0.4F);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":woodBowlBlock");
	}
	
	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}
	
	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		switch (meta) {
		case 0:
			return Items.bowl;
		case 1:
			return PlaceableItems.blackBowl;
		case 2:
			return PlaceableItems.redBowl;
		case 3:
			return PlaceableItems.greenBowl;
		case 4:
			return PlaceableItems.brownBowl;
		case 5:
			return PlaceableItems.blueBowl;
		case 6:
			return PlaceableItems.purpleBowl;
		case 7:
			return PlaceableItems.cyanBowl;
		case 8:
			return PlaceableItems.lightGrayBowl;
		case 9:
			return PlaceableItems.grayBowl;
		case 10:
			return PlaceableItems.pinkBowl;
		case 11:
			return PlaceableItems.limeBowl;
		case 12:
			return PlaceableItems.yellowBowl;
		case 13:
			return PlaceableItems.lightBlueBowl;
		case 14:
			return PlaceableItems.magentaBowl;
		case 15:
			return PlaceableItems.orangeBowl;
		case 16:
			return PlaceableItems.whiteBowl;
		default:
			return null;
		}
	}
}
