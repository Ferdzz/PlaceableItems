package me.ferdz.placeableitems.block;

import java.util.ArrayList;
import java.util.List;

import me.ferdz.placeableitems.init.ModItems;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSaddleStand extends BlockFaceable {

	public static final PropertyBool SADDLED = PropertyBool.create("saddled");

	public BlockSaddleStand(String name) {
		super(name);
		this.setDefaultState(getDefaultState().withProperty(SADDLED, false));
		this.setCreativeTab(CreativeTabs.DECORATIONS);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote)
			return true;
		
		if(heldItem != null && heldItem.getItem() == Items.SADDLE && !state.getValue(SADDLED)) {
			state = state.withProperty(SADDLED, true);
			worldIn.setBlockState(pos, state);
			if(!playerIn.isCreative()) {
				heldItem.stackSize--;
			}
			return true;
		} else if (!playerIn.isSneaking() && state.getValue(SADDLED)) {
			state = state.withProperty(SADDLED, false);
			EntityItem drop = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.SADDLE, 1));
			worldIn.spawnEntityInWorld(drop);
			worldIn.setBlockState(pos, state);
			return true;
		}
		return false;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> l = new ArrayList<ItemStack>();
		if(state.getValue(SADDLED))
			l.add(new ItemStack(Items.SADDLE, 1));
		l.add(new ItemStack(ModItems.itemSaddleStand, 1));
		return l;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(ModItems.itemSaddleStand);
	}
	
	@Override
  	public IBlockState getStateFromMeta(int meta) {
  		IBlockState s = super.getStateFromMeta(meta % 8);
 		s = s.withProperty(SADDLED, meta % 8 == 0);
 		s = s.withProperty(SADDLED, (meta / 8) == 0);
  		return s;
  	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		boolean saddled = state.getValue(SADDLED);
		if(saddled)
			return face + 8;
		return face;
	}

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ SADDLED, FACING });
    }
}
