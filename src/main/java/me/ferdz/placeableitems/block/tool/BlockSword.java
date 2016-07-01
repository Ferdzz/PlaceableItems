package me.ferdz.placeableitems.block.tool;

import me.ferdz.placeableitems.init.ModBlocks;
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

	public static final PropertyEnum<EnumSword> MODEL = PropertyEnum.create("model", EnumSword.class);
	
	public BlockSword(String name) {
		super(name);
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		switch(facing) {
		case UP:
			return state.withProperty(MODEL, EnumSword.TOP);
		default:
			return state.withProperty(MODEL, EnumSword.SIDE0);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		TESword te = (TESword)worldIn.getTileEntity(pos);
		te.setModel(state.getValue(MODEL));
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TESword te = (TESword)world.getTileEntity(pos);
		EnumSword modelState = te.getModel();

		modelState = modelState.next();
		
		world.setBlockState(pos, state.withProperty(MODEL, modelState)); // this resets the TE
		te = (TESword)world.getTileEntity(pos);
		te.setModel(modelState);
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
