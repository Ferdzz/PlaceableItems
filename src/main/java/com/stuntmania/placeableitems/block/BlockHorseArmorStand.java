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

import com.stuntmania.placeableitems.tileentity.TEHorseArmorStand;
import com.stuntmania.placeableitems.utils.WorldUtils;

import cpw.mods.fml.common.registry.GameRegistry;

//TODO fix hitbox
public class BlockHorseArmorStand extends BlockPlaceableItems {
	private IIcon icon;

	public BlockHorseArmorStand() {
		super(Material.wood);
		GameRegistry.registerBlock(this, "horseArmorStandBlock");
		setBlockName("horseArmorStandBlock");
		this.setHardness(0.8F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TEHorseArmorStand();
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int unknown, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		ItemStack equip = player.getCurrentEquippedItem();
		if (world.getBlockMetadata(x, y, z) != 0) { // If something is on it
			switch (world.getBlockMetadata(x, y, z)) {
			case 1:
				WorldUtils.spawnItem(world, x, y, z, Items.iron_horse_armor);
				break;
			case 2:
				WorldUtils.spawnItem(world, x, y, z, Items.golden_horse_armor);
				break;
			case 3:
				WorldUtils.spawnItem(world, x, y, z, Items.diamond_horse_armor);
				break;
			}
			world.setBlockMetadataWithNotify(x, y, z, 0, 2 | 1);
			return true;
		} else if (equip != null) { // If nothing is on it
			if (equip.getItem().equals(Items.iron_horse_armor)) {
				world.setBlockMetadataWithNotify(x, y, z, 1, 2 | 1);
				if (!player.capabilities.isCreativeMode)
					equip.stackSize--;
				return true;
			} else if (equip.getItem().equals(Items.golden_horse_armor)) {
				world.setBlockMetadataWithNotify(x, y, z, 2, 2 | 1);
				if (!player.capabilities.isCreativeMode)
					equip.stackSize--;
				return true;
			} else if (equip.getItem().equals(Items.diamond_horse_armor)) {
				world.setBlockMetadataWithNotify(x, y, z, 3, 2 | 1);
				if (!player.capabilities.isCreativeMode)
					equip.stackSize--;
				return true;
			}
		}
		return false;
	}
}
