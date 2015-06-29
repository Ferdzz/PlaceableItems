package com.stuntmania.placeableitems.block;

import java.util.Random;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TEPorkchop;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockPorkchop extends BlockPlaceableItems {

	/*
	 * 0 = raw
	 * 1 = cooked
	 */
	private IIcon icons[] = new IIcon[2];
	
	public BlockPorkchop() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.1F, 1);
	}
	
	@Override
	public Item getItemDropped(int meta, Random rand, int par3) {
		if(meta == 0) 
			return Items.porkchop;
		else if (meta == 1)
			return Items.cooked_porkchop;
		else 
			return null;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0) 
			return new ItemStack(Items.porkchop);
		else if (meta == 1)
			return new ItemStack(Items.cooked_porkchop);
		else 
			return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		if(meta == 0) 
			return new TEPorkchop(3, 0.9F);
		else if (meta == 1)
			return new TEPorkchop(8, 1.6F);
		else 
			return null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons[0] = reg.registerIcon(PlaceableItems.MODID + ":destroy/porkchopRaw");
		icons[1] = reg.registerIcon(PlaceableItems.MODID + ":destroy/porkchopCooked");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}
}
