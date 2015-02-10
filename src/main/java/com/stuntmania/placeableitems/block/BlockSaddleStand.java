package com.stuntmania.placeableitems.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.tileentity.TESaddleStand;
import com.stuntmania.placeableitems.utils.WorldUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockSaddleStand extends BlockPlaceableItems {

	private IIcon icon;

	public BlockSaddleStand() {
		super(Material.wood);
		GameRegistry.registerBlock(this, "saddleStandBlock");
		setBlockName("saddleStandBlock");
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public TileEntity createNewTileEntity(World world, int par2) {
		return new TESaddleStand();
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon("minecraft" + ":planks_oak");
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		ItemStack equip = player.getCurrentEquippedItem();
		if (world.getBlockMetadata(x, y, z) == 1) { // Removing saddle
			WorldUtils.spawnItem(world, x, y, z, Items.saddle);
			world.setBlockMetadataWithNotify(x, y, z, 0, 2 | 1);
		} else if(world.getBlockMetadata(x, y, z) == 0 && equip != null){ // Placing saddle
			if (equip.getItem().equals(Items.saddle)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2 | 1);
				if (!player.capabilities.isCreativeMode)
					equip.stackSize--;
				return true;
			}
			
		}
		return false;
	}
}
