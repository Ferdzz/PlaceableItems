package com.stuntmania.PlaceableItems.Blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
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
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		BowlBlockTileEntity entity = (BowlBlockTileEntity) world.getTileEntity(x, y, z);
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		switch (entity.getState()) {
		case 0:
			drop.add(new ItemStack(Items.bowl));
			break;
		case 1:
			drop.add(new ItemStack(PlaceableItems.blackBowl));
			break;
		case 2:
			drop.add(new ItemStack(PlaceableItems.redBowl));
			break;
		case 3:
			drop.add(new ItemStack(PlaceableItems.greenBowl));
			break;
		case 4:
			drop.add(new ItemStack(PlaceableItems.brownBowl));
			break;
		case 5:
			drop.add(new ItemStack(PlaceableItems.blueBowl));
			break;
		case 6:
			drop.add(new ItemStack(PlaceableItems.purpleBowl));
			break;
		case 7:
			drop.add(new ItemStack(PlaceableItems.cyanBowl));
			break;
		case 8:
			drop.add(new ItemStack(PlaceableItems.lightGrayBowl));
			break;
		case 9:
			drop.add(new ItemStack(PlaceableItems.grayBowl));
			break;
		case 10:
			drop.add(new ItemStack(PlaceableItems.pinkBowl));
			break;
		case 11:
			drop.add(new ItemStack(PlaceableItems.limeBowl));
			break;
		case 12:
			drop.add(new ItemStack(PlaceableItems.yellowBowl));
			break;
		case 13:
			drop.add(new ItemStack(PlaceableItems.lightBlueBowl));
			break;
		case 14:
			drop.add(new ItemStack(PlaceableItems.magentaBowl));
			break;
		case 15:
			drop.add(new ItemStack(PlaceableItems.orangeBowl));
			break;
		case 16:
			drop.add(new ItemStack(PlaceableItems.whiteBowl));
			break;
		}
		return drop;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = ((BowlBlockTileEntity) world.getTileEntity(x, y, z)).getState();
		switch (meta) {
		case 0:
			return new ItemStack(Items.bowl);
		case 1:
			return new ItemStack(PlaceableItems.blackBowl);
		case 2:
			return new ItemStack(PlaceableItems.redBowl);
		case 3:
			return new ItemStack(PlaceableItems.greenBowl);
		case 4:
			return new ItemStack(PlaceableItems.brownBowl);
		case 5:
			return new ItemStack(PlaceableItems.blueBowl);
		case 6:
			return new ItemStack(PlaceableItems.purpleBowl);
		case 7:
			return new ItemStack(PlaceableItems.cyanBowl);
		case 8:
			return new ItemStack(PlaceableItems.lightGrayBowl);
		case 9:
			return new ItemStack(PlaceableItems.grayBowl);
		case 10:
			return new ItemStack(PlaceableItems.pinkBowl);
		case 11:
			return new ItemStack(PlaceableItems.limeBowl);
		case 12:
			return new ItemStack(PlaceableItems.yellowBowl);
		case 13:
			return new ItemStack(PlaceableItems.lightBlueBowl);
		case 14:
			return new ItemStack(PlaceableItems.magentaBowl);
		case 15:
			return new ItemStack(PlaceableItems.orangeBowl);
		case 16:
			return new ItemStack(PlaceableItems.whiteBowl);
		default:
			return null;
		}
	}
}
