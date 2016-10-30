package com.stuntmania.placeableitems.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.tileentity.TEIngot;

public class BlockIngot extends BlockPlaceableItems {
	
	private IIcon[] icons = new IIcon[2];
	
	/*
	 * Metadatas: 
	 * 0: Iron ingot (1) 
	 * 1: Gold ingot (1) 
	 * 2: Iron ingot (2) 
	 * 3: Gold ingot (2) 
	 * 4: Iron ingot (3) 
	 * 5: Gold ingot (3) 
	 * 6: Iron ingot (4) 
	 * 7: Gold ingot (4)
	 */
	
	public BlockIngot() {
		super(Material.circuits);
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 1.0F - 0.125F, 0.3F, 1.0F - 0.125F);
//		this.setHardness(0.1F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TEIngot();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon("minecraft" + ":iron_block");
		icons[1] = reg.registerIcon("minecraft" + ":gold_block");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta % 2];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack equip = player.getCurrentEquippedItem();
		if (equip == null || world.isRemote)
			return false;
		int meta = world.getBlockMetadata(x, y, z);
		boolean c = player.capabilities.isCreativeMode;

		/*
		 * Un-stacking Blocks
		 */
		if (!player.isSneaking()) {
			if (!c) {
				if (world.getBlockMetadata(x, y, z) % 2 == 0) {
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.iron_ingot, 1)));
				} else {
					world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Items.gold_ingot, 1)));
				}
			}
			if (meta > 1) {
				world.setBlockMetadataWithNotify(x, y, z, meta - 2, 3);
			} else if (meta <= 1) {
				world.setBlockToAir(x, y, z);
			}
			return true;
		}
		
		return false;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		switch (metadata) {
		case 0:
			drops.add(new ItemStack(Items.iron_ingot, 1));
			break;
		case 1:
			drops.add(new ItemStack(Items.gold_ingot, 1));
			break;
		case 2:
			drops.add(new ItemStack(Items.iron_ingot, 2));
			break;
		case 3:
			drops.add(new ItemStack(Items.gold_ingot, 2));
			break;
		case 4:
			drops.add(new ItemStack(Items.iron_ingot, 3));
			break;
		case 5:
			drops.add(new ItemStack(Items.gold_ingot, 3));
			break;
		case 6:
			drops.add(new ItemStack(Items.iron_ingot, 4));
			break;
		case 7:
			drops.add(new ItemStack(Items.gold_ingot, 4));
			break;
		}
		return drops;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		switch (meta) {
		case 0:
		case 2:
		case 4:
		case 6:
			return new ItemStack(Items.iron_ingot);
		case 1:
		case 3:
		case 5:
		case 7:
			return new ItemStack(Items.gold_ingot);
		}
		return null;
	}
}
