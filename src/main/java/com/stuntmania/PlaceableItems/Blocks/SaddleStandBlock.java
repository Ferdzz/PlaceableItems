package com.stuntmania.PlaceableItems.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.SaddleStandTileEntity;

public class SaddleStandBlock extends PlaceableItemsBlock {

	private IIcon icon;

	public SaddleStandBlock(Material material) {
		super(material);
	}

	public TileEntity createNewTileEntity(World world, int par2) {
		return new SaddleStandTileEntity();
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		ItemStack equip = player.getCurrentEquippedItem();
		if (world.getBlockMetadata(x, y, z) == 1) { // Removing saddle
			PlaceableItems.spawnItem(world, x, y, z, Items.saddle);
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
