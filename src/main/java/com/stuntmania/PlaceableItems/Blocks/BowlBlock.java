package com.stuntmania.PlaceableItems.Blocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.Init.ModItems;
import com.stuntmania.PlaceableItems.TileEntities.BowlBlockTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public class BowlBlock extends PlaceableItemsBlock {

	private IIcon icon;

	public BowlBlock() {
		super(Material.wood);
		setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.3F, 0.3F + 0.4F);
		GameRegistry.registerBlock(this, "bowlBlock");
		setBlockName("bowlBlock");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new BowlBlockTileEntity();
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
			drop.add(new ItemStack(ModItems.blackBowl));
			break;
		case 2:
			drop.add(new ItemStack(ModItems.redBowl));
			break;
		case 3:
			drop.add(new ItemStack(ModItems.greenBowl));
			break;
		case 4:
			drop.add(new ItemStack(ModItems.brownBowl));
			break;
		case 5:
			drop.add(new ItemStack(ModItems.blueBowl));
			break;
		case 6:
			drop.add(new ItemStack(ModItems.purpleBowl));
			break;
		case 7:
			drop.add(new ItemStack(ModItems.cyanBowl));
			break;
		case 8:
			drop.add(new ItemStack(ModItems.lightGrayBowl));
			break;
		case 9:
			drop.add(new ItemStack(ModItems.grayBowl));
			break;
		case 10:
			drop.add(new ItemStack(ModItems.pinkBowl));
			break;
		case 11:
			drop.add(new ItemStack(ModItems.limeBowl));
			break;
		case 12:
			drop.add(new ItemStack(ModItems.yellowBowl));
			break;
		case 13:
			drop.add(new ItemStack(ModItems.lightBlueBowl));
			break;
		case 14:
			drop.add(new ItemStack(ModItems.magentaBowl));
			break;
		case 15:
			drop.add(new ItemStack(ModItems.orangeBowl));
			break;
		case 16:
			drop.add(new ItemStack(ModItems.whiteBowl));
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
			return new ItemStack(ModItems.blackBowl);
		case 2:
			return new ItemStack(ModItems.redBowl);
		case 3:
			return new ItemStack(ModItems.greenBowl);
		case 4:
			return new ItemStack(ModItems.brownBowl);
		case 5:
			return new ItemStack(ModItems.blueBowl);
		case 6:
			return new ItemStack(ModItems.purpleBowl);
		case 7:
			return new ItemStack(ModItems.cyanBowl);
		case 8:
			return new ItemStack(ModItems.lightGrayBowl);
		case 9:
			return new ItemStack(ModItems.grayBowl);
		case 10:
			return new ItemStack(ModItems.pinkBowl);
		case 11:
			return new ItemStack(ModItems.limeBowl);
		case 12:
			return new ItemStack(ModItems.yellowBowl);
		case 13:
			return new ItemStack(ModItems.lightBlueBowl);
		case 14:
			return new ItemStack(ModItems.magentaBowl);
		case 15:
			return new ItemStack(ModItems.orangeBowl);
		case 16:
			return new ItemStack(ModItems.whiteBowl);
		default:
			return null;
		}
	}
}
