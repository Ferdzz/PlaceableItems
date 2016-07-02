package me.ferdz.placeableitems.block.tool;

import me.ferdz.placeableitems.state.EnumPreciseFacing;
import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.tileentity.tool.TESword;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSword extends BlockTool {

	public static final PropertyEnum<EnumSword> MODEL = PropertyEnum.create("smodel", EnumSword.class);
	
	public BlockSword(String name) {
		super(name);
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		switch(facing) {
		case UP:
			return state.withProperty(MODEL, EnumSword.TOP);
		case EAST:
			return state.withProperty(FACING, EnumPreciseFacing.D0).withProperty(MODEL, EnumSword.SIDE0);
		case SOUTH:
			return state.withProperty(FACING, EnumPreciseFacing.D90).withProperty(MODEL, EnumSword.SIDE0);
		case WEST:
			return state.withProperty(FACING, EnumPreciseFacing.D180).withProperty(MODEL, EnumSword.SIDE0);
		case NORTH:
			return state.withProperty(FACING, EnumPreciseFacing.D270).withProperty(MODEL, EnumSword.SIDE0);
		}
		return state;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		
		if(worldIn.isRemote)
			return;
		
		TESword te = (TESword)worldIn.getTileEntity(pos);
		te.setModel(state.getValue(MODEL));
		te.setTool(stack);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
			return true;
		
		TESword te = (TESword)world.getTileEntity(pos);
		EnumSword modelState = te.getModel();

		modelState = modelState.next();

		te.setModel(modelState);
		world.notifyBlockUpdate(pos, state, state, 2);
		te.markDirty();
		return true;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TESword) {
			TESword sword = (TESword) te;
			return state.withProperty(MODEL, sword.getModel());
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ MODEL, FACING });
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TESword();
	}
}
