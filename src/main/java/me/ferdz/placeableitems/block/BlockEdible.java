package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.tileentity.TEEdible;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEdible extends BlockFaceable implements ITileEntityProvider {

	public static final PropertyBool PLATED = PropertyBool.create("plated");

	protected int foodLevel;
	protected float saturation;
	
	public BlockEdible(String name) {
		super(name, Material.SPONGE);
		
		this.isBlockContainer = true;
		
		if(this.createBlockState().getProperty("plated") != null) {
			this.setDefaultState(super.getDefaultState().withProperty(PLATED, false));
		}
	}
	
	public BlockEdible(String name, int foodLevel, float saturation) {
		this(name);
		
		this.foodLevel = foodLevel;
		this.saturation = saturation;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdible) {
			((TEEdible)te).bite(foodLevel, saturation, playerIn, worldIn);
			return true;
		}
		return false;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdible) {
			if(state.getProperties().containsKey(PLATED))
				return state.withProperty(PLATED, ((TEEdible) te).isPlated());
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PLATED, FACING });
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEEdible();
	}
}
