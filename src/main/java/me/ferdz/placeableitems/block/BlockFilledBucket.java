package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFilledBucket extends BlockPlaceableItems {

	public static final PropertyEnum<EnumFluid> FLUID = PropertyEnum.create("fluid", EnumFluid.class);
	
	public BlockFilledBucket(String name) {
		super(Material.WOOD, name);
		
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(FLUID, EnumFluid.LAVA));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(heldItem != null && heldItem.getItem().equals(Items.BUCKET)) {
			if(state.getValue(FLUID) == EnumFluid.LAVA && playerIn.inventory.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET, 1))) {
				worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.onBlockPlaced(worldIn, pos, EnumFacing.DOWN, hitX, hitY, hitZ, 0, playerIn));
			} else if(state.getValue(FLUID) == EnumFluid.WATER && playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET, 1))) {
				worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.onBlockPlaced(worldIn, pos, EnumFacing.DOWN, hitX, hitY, hitZ, 0, playerIn));
			} else if(state.getValue(FLUID) == EnumFluid.MILK && playerIn.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET, 1))) {
				worldIn.setBlockState(pos, ModBlocks.blockEmptyBucket.onBlockPlaced(worldIn, pos, EnumFacing.DOWN, hitX, hitY, hitZ, 0, playerIn));
			}
			heldItem.stackSize--;
			return true;
		}
		return false;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(placer.getHeldItemMainhand().getItem().equals(Items.LAVA_BUCKET))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FLUID, EnumFluid.LAVA);
		else if(placer.getHeldItemMainhand().getItem().equals(Items.WATER_BUCKET))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FLUID, EnumFluid.WATER);
		else if(placer.getHeldItemMainhand().getItem().equals(Items.MILK_BUCKET))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FLUID, EnumFluid.MILK);
		return null;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta % 4));
		s = s.withProperty(FLUID, EnumFluid.values()[(int)(meta / 4)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal() - 2;
		int fluid = state.getValue(FLUID).getID();
		return face + (fluid * 4);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FLUID, FACING});
    }
	
	public enum EnumFluid implements IStringSerializable {
	    LAVA(0, "lava"),
	    WATER(1, "water"),
	    MILK(2, "milk");

	    private int ID;
	    private String name;
	    
	    private EnumFluid(int ID, String name) {
	        this.ID = ID;
	        this.name = name;
	    }
	    
	    @Override
	    public String getName() {
	        return name;
	    }

	    public int getID() {
	        return ID;
	    }
	    
	    @Override
	    public String toString() {
	        return getName();
	    }
	}
}