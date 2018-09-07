package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumBook;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookAndQuill extends BlockFaceable implements ITileEntityProvider {

	public static final PropertyEnum<EnumBook> CONTENT = PropertyEnum.create("content", EnumBook.class);
	
	public BlockBookAndQuill(String name) {
		super(name);
		
		this.hasTileEntity = true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEStack) {
			((TEStack) te).setStack(stack.copy());
		}
	}
	
//	@Override
//	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
//		TileEntity te = worldIn.getTileEntity(pos);
//		if(te != null && te instanceof TEBook) { 
//			playerIn.openBook(((TEBook) te).getBook(), hand);
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te != null && te instanceof TEStack) {
			ItemStack book = ((TEStack)te).getStack();
			if(book.getTagCompound() != null) {
				NBTTagList l = book.getTagCompound().getTagList("pages", 8);
				for (int i = 0; i < l.tagCount(); i++) {
					if(l.get(i).toString().equals("\"\""))
						return state.withProperty(CONTENT, EnumBook.EMPTY);						
				}
				return state.withProperty(CONTENT, EnumBook.WRITTEN);}
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ CONTENT, FACING });
    }
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEStack();
	}
}
