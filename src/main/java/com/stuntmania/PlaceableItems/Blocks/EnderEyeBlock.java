package com.stuntmania.PlaceableItems.Blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.PlaceableItems.PlaceableItems;
import com.stuntmania.PlaceableItems.TileEntities.EnderEyeBlockTileEntity;

public class EnderEyeBlock extends PlaceableItemsBlock {

	private IIcon icon;
	
	public EnderEyeBlock(Material material) {
		super(material);
		setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.4F, 0.3F + 0.4F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2) {
		return new EnderEyeBlockTileEntity();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(PlaceableItems.MODID + ":destroy/ender_eye");
	}

	@Override
	public IIcon getIcon(int face, int meta) {
		return icon;
	}

	@Override
	public Item getItemDropped(int meta, Random p_149650_2_, int p_149650_3_) {
		return Items.ender_eye;
	}
	    
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
	    return new ItemStack(Items.ender_eye);
	}
	
	@Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
        for (int l = 0; l < 3; ++l)
        {
            //double d6 = (double)((float)x + rand.nextFloat());
            double d1 = (double)((float)y + rand.nextFloat());
            //d6 = (double)((float)z + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            int j1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            d4 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            d5 = ((double)rand.nextFloat() - 0.5D) * 0.125D;
            double d2 = (double)z + 0.5D + 0.25D * (double)j1;
            d5 = (double)(rand.nextFloat() * 1.0F * (float)j1);
            double d0 = (double)x + 0.5D + 0.25D * (double)i1;
            d3 = (double)(rand.nextFloat() * 1.0F * (float)i1);
			world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
		}
	}
}
