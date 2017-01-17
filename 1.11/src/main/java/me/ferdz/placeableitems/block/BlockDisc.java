package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumDisc;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDisc extends BlockFaceable implements ITileEntityProvider {

	public static final PropertyEnum<EnumDisc> TYPE = PropertyEnum.create("type", EnumDisc.class);
	
	public BlockDisc(String name) {
		super(name);
		
		this.isBlockContainer = true;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEStack) {
			ItemStack is = stack.copy();
			is.setCount(1);
			((TEStack) te).setStack(is);
		}
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TEStack te = (TEStack) world.getTileEntity(pos);
		return te.getStack();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TEStack te = (TEStack)worldIn.getTileEntity(pos);
		Item item = te.getStack().getItem();
		
		if(item == Items.RECORD_11)
			return state.withProperty(TYPE, EnumDisc.RECORD_11);
		else if(item == Items.RECORD_13)
			return state.withProperty(TYPE, EnumDisc.RECORD_13);
		else if(item == Items.RECORD_BLOCKS)
			return state.withProperty(TYPE, EnumDisc.RECORD_BLOCKS);
		else if(item == Items.RECORD_CAT)
			return state.withProperty(TYPE, EnumDisc.RECORD_CAT);
		else if(item == Items.RECORD_CHIRP)
			return state.withProperty(TYPE, EnumDisc.RECORD_CHIRP);
		else if(item == Items.RECORD_FAR)
			return state.withProperty(TYPE, EnumDisc.RECORD_FAR);
		else if(item == Items.RECORD_MALL)
			return state.withProperty(TYPE, EnumDisc.RECORD_MALL);
		else if(item == Items.RECORD_MELLOHI)
			return state.withProperty(TYPE, EnumDisc.RECORD_MELLOHI);
		else if(item == Items.RECORD_STAL)
			return state.withProperty(TYPE, EnumDisc.RECORD_STAL);
		else if(item == Items.RECORD_STRAD)
			return state.withProperty(TYPE, EnumDisc.RECORD_STRAD);
		else if(item == Items.RECORD_WAIT)
			return state.withProperty(TYPE, EnumDisc.RECORD_WAIT);
		else if(item == Items.RECORD_WARD)
			return state.withProperty(TYPE, EnumDisc.RECORD_WARD);
		else 
			return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ TYPE, FACING });
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEStack();
	}
}
