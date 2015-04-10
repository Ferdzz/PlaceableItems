package com.stuntmania.placeableitems.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.stuntmania.placeableitems.init.ModItems;
import com.stuntmania.placeableitems.tileentity.TEBowl;
import com.stuntmania.placeableitems.utils.WorldUtils;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBowl extends BlockPlaceableItems {
	
	
	public BlockBowl() {
		super(Material.wood);
		this.setBlockBounds(0.3F, 0, 0.3F, 0.3F + 0.4F, 0.3F, 0.3F + 0.4F);
		this.setBlockName("bowlBlock");
		this.setBlockTextureName("minecraft" + ":planks_oak");
		GameRegistry.registerBlock(this, "bowlBlock");
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TEBowl();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack item = player.getCurrentEquippedItem();
		TEBowl entity = (TEBowl) world.getTileEntity(x, y, z);
		
		//Add the dye to the bowl
		if (entity.getState() == 0 && item != null && item.getItem().equals(Items.dye)) {
			entity.setState(item.getItemDamage() + 1);
			if(!player.capabilities.isCreativeMode) {
				player.getCurrentEquippedItem().stackSize--;
			}
			return true;
		}
		
		//Remove the dye from the bowl
		if(entity.getState() != 0) {
			WorldUtils.spawnItem(world, x, y, z, new ItemStack(Items.dye, 1, entity.getState() - 1));
			entity.setState(0);
			return true;
		}
		return false;
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		TEBowl entity = (TEBowl) world.getTileEntity(x, y, z);
		ArrayList<ItemStack> drop = new ArrayList<ItemStack>();
		switch (entity.getState()) {
		case 0:
			drop.add(new ItemStack(Items.bowl));
			break;
		case 1:
			drop.add(new ItemStack(ModItems.black_bowl));
			break;
		case 2:
			drop.add(new ItemStack(ModItems.red_bowl));
			break;
		case 3:
			drop.add(new ItemStack(ModItems.green_bowl));
			break;
		case 4:
			drop.add(new ItemStack(ModItems.brown_bowl));
			break;
		case 5:
			drop.add(new ItemStack(ModItems.blue_bowl));
			break;
		case 6:
			drop.add(new ItemStack(ModItems.purple_bowl));
			break;
		case 7:
			drop.add(new ItemStack(ModItems.cyan_bowl));
			break;
		case 8:
			drop.add(new ItemStack(ModItems.light_gray_bowl));
			break;
		case 9:
			drop.add(new ItemStack(ModItems.gray_bowl));
			break;
		case 10:
			drop.add(new ItemStack(ModItems.pink_bowl));
			break;
		case 11:
			drop.add(new ItemStack(ModItems.lime_bowl));
			break;
		case 12:
			drop.add(new ItemStack(ModItems.yellow_bowl));
			break;
		case 13:
			drop.add(new ItemStack(ModItems.light_blue_bowl));
			break;
		case 14:
			drop.add(new ItemStack(ModItems.magenta_bowl));
			break;
		case 15:
			drop.add(new ItemStack(ModItems.orange_bowl));
			break;
		case 16:
			drop.add(new ItemStack(ModItems.white_bowl));
			break;
		}
		return drop;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
		int meta = ((TEBowl) world.getTileEntity(x, y, z)).getState();
		switch (meta) {
		case 0:
			return new ItemStack(Items.bowl);
		case 1:
			return new ItemStack(ModItems.black_bowl);
		case 2:
			return new ItemStack(ModItems.red_bowl);
		case 3:
			return new ItemStack(ModItems.green_bowl);
		case 4:
			return new ItemStack(ModItems.brown_bowl);
		case 5:
			return new ItemStack(ModItems.blue_bowl);
		case 6:
			return new ItemStack(ModItems.purple_bowl);
		case 7:
			return new ItemStack(ModItems.cyan_bowl);
		case 8:
			return new ItemStack(ModItems.light_gray_bowl);
		case 9:
			return new ItemStack(ModItems.gray_bowl);
		case 10:
			return new ItemStack(ModItems.pink_bowl);
		case 11:
			return new ItemStack(ModItems.lime_bowl);
		case 12:
			return new ItemStack(ModItems.yellow_bowl);
		case 13:
			return new ItemStack(ModItems.light_blue_bowl);
		case 14:
			return new ItemStack(ModItems.magenta_bowl);
		case 15:
			return new ItemStack(ModItems.orange_bowl);
		case 16:
			return new ItemStack(ModItems.white_bowl);
		default:
			return null;
		}
	}
}
