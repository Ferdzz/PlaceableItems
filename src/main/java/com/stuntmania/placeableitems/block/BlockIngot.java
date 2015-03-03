package com.stuntmania.placeableitems.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.tileentity.TEIngot;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockIngot extends BlockPlaceableItems {

	private IIcon[] icons = new IIcon[8];

	/* Metadatas:
	 * 0: Iron ingot (1)
	 * 1: Gold ingot (1)
	 * 2: Iron ingot (2)
	 * 3: Gold ingot (2)
	 * 4: Iron ingot (3)
	 * 5: Gold ingot (3)
	 * 6: Iron ingot (4)
	 * 7: Gold ingot (4) 
	 **/
	
	public BlockIngot() {
		super(Material.iron);
		setBlockBounds(0.125F, 0.0F, 0.125F, 1.0F - 0.125F, 0.3F, 1.0F - 0.125F);
		GameRegistry.registerBlock(this, "ingotBlock");
		setBlockName("ingotBlock");
		setHardness(0.1F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TEIngot();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon("minecraft" + ":iron_block");
		icons[1] = reg.registerIcon("minecraft" + ":gold_block");
		icons[2] = reg.registerIcon("minecraft" + ":iron_block");
		icons[3] = reg.registerIcon("minecraft" + ":gold_block");
		icons[4] = reg.registerIcon("minecraft" + ":iron_block");
		icons[5] = reg.registerIcon("minecraft" + ":gold_block");
		icons[6] = reg.registerIcon("minecraft" + ":iron_block");
		icons[7] = reg.registerIcon("minecraft" + ":gold_block");
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
		case 2:
			return Items.iron_ingot;
		case 3:
			return Items.gold_ingot;
		case 4:
			return Items.iron_ingot;
		case 5:
			return Items.gold_ingot;
		case 6:
			return Items.iron_ingot;
		case 7:
			return Items.gold_ingot;
		default:
			return null;
		}
	}
	
	private int blockMetadata;
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		blockMetadata = world.getBlockMetadata(x, y, z);
		System.out.println(blockMetadata);
	}
	
	@Override
	public int quantityDropped(Random rand) {
		switch (blockMetadata) {
		default:
			return 1;
		case 2:
			return 2;
		case 3:
			return 2;
		case 4:
			return 3;
		case 5:
			return 3;
		case 6:
			return 4;
		case 7:
			return 4;
		}
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
			return new ItemStack(Items.iron_ingot);
		case 1:
			return new ItemStack(Items.gold_ingot);
		case 2:
			return new ItemStack(Items.iron_ingot);
		case 3:
			return new ItemStack(Items.gold_ingot);
		case 4:
			return new ItemStack(Items.iron_ingot);
		case 5:
			return new ItemStack(Items.gold_ingot);
		case 6:
			return new ItemStack(Items.iron_ingot);
		case 7:
			return new ItemStack(Items.gold_ingot);
		default:
			return null;
		}
	}
}
