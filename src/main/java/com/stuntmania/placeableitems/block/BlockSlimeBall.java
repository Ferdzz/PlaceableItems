package com.stuntmania.placeableitems.block;

import java.util.Random;

import com.stuntmania.placeableitems.PlaceableItems;
import com.stuntmania.placeableitems.tileentity.TESlimeBall;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockSlimeBall extends BlockPlaceableItems {
	
	public BlockSlimeBall() {
		super(Material.sponge);
		this.setBlockBounds(0, 0, 0, 1, 0.6F, 1);
		this.setBlockTextureName(PlaceableItems.MODID + ":destroy/slimeBall");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new TESlimeBall();
	}
	
	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		return Items.slime_ball;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.slime_ball);
	}
}
