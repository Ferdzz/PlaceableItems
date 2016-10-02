package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumIngot;
import me.ferdz.placeableitems.state.EnumStackSize;
import me.ferdz.placeableitems.tileentity.TEIngot;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockIngot extends BlockFaceable implements ITileEntityProvider {

	public static final PropertyEnum<EnumStackSize> STACK = PropertyEnum.create("stack", EnumStackSize.class);
	public static final PropertyEnum<EnumIngot> TYPE = PropertyEnum.create("type", EnumIngot.class);
	
	public BlockIngot(String name) {
		super(name);
		
		setDefaultState(super.getDefaultState().withProperty(STACK, EnumStackSize._1));
		isBlockContainer = true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote)
			return true;
		
		TileEntity te = worldIn.getTileEntity(pos);
		
		// to remove an ingot
		if(playerIn.isSneaking()) {
			ItemStack stack = null;
			switch(state.getValue(TYPE)) {
			case GOLD:
				stack = new ItemStack(Items.GOLD_INGOT, 1);
				break;
			case IRON:
				stack = new ItemStack(Items.IRON_INGOT, 1);
				break;
			}
			if(te instanceof TEIngot) {
				EntityItem drop = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
				worldIn.spawnEntityInWorld(drop);
				if(--((TEIngot) te).stackSize == 0)
					worldIn.setBlockToAir(pos);
				
				worldIn.notifyBlockUpdate(pos, state, state, 2);
				te.markDirty();
			}
			return true;
		}

		// to add an ingot
		if(heldItem == null)
			return false;
		switch(state.getValue(TYPE)) {
		case GOLD:
			if(heldItem.getItem() != Items.GOLD_INGOT)
				return false;
			break;
		case IRON:
			if(heldItem.getItem() != Items.IRON_INGOT)
				return false;
			break;
		}
		
		if(te instanceof TEIngot) {
			int stackSize = ((TEIngot) te).stackSize;
			if(stackSize == 6) return false;

			if(!playerIn.isCreative())
				heldItem.stackSize--;
			
			((TEIngot)te).stackSize++;
			worldIn.notifyBlockUpdate(pos, state, state, 2);
			te.markDirty();
		}
		return false;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TEIngot) {
			switch (state.getValue(TYPE)) {
			case GOLD:
				return new ItemStack(Items.GOLD_INGOT, ((TEIngot) te).stackSize);
			case IRON:
				return new ItemStack(Items.IRON_INGOT, ((TEIngot) te).stackSize);
			}
		}
		return null;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		if(placer.getHeldItemMainhand().getItem().equals(Items.GOLD_INGOT))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumIngot.GOLD);
		else if(placer.getHeldItemMainhand().getItem().equals(Items.IRON_INGOT))
			return super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(TYPE, EnumIngot.IRON);
		return null;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState s = super.getStateFromMeta(meta % 8);
		s = s.withProperty(TYPE, EnumIngot.values()[(int)(meta / 8)]);
		return s;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int face = state.getValue(FACING).ordinal();
		int type = state.getValue(TYPE).getID();
		return face + (type * 8);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEIngot) {
			return state.withProperty(STACK, EnumStackSize.values()[((TEIngot) te).stackSize - 1]);
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ STACK, TYPE, FACING });
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEIngot();
	}
}
