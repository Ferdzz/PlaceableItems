package me.ferdz.placeableitems.block;

import java.util.Arrays;
import java.util.List;

import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.state.EnumHorseArmor;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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

public class BlockHorseArmorStand extends BlockFaceable implements ITileEntityProvider {

	public static final PropertyEnum<EnumHorseArmor> TYPE = PropertyEnum.create("type", EnumHorseArmor.class);
	
	public BlockHorseArmorStand(String name) {
		super(name);
		this.setDefaultState(getDefaultState().withProperty(TYPE, EnumHorseArmor.EMPTY));
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.isBlockContainer = true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		switch(getActualState(state, worldIn, pos).getValue(TYPE)) {
		case DIAMOND:
		case GOLD:
		case IRON:
			if(te instanceof TEStack && !worldIn.isRemote) {
				EntityItem drop = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), ((TEStack) te).getStack());
				worldIn.spawnEntityInWorld(drop);
				((TEStack)te).setStack(new ItemStack(Items.STICK));
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 2);
			}
			return true;
		case EMPTY:
			if(te instanceof TEStack && !worldIn.isRemote) {
				((TEStack)te).setStack(heldItem.copy());
				if(!playerIn.isCreative())
					heldItem.stackSize--;
				te.markDirty();
				worldIn.notifyBlockUpdate(pos, state, state, 2);
			}
			return true;
		}
		return true;
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return Arrays.asList(new ItemStack(ModItems.itemHorseArmorStand));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(ModItems.itemHorseArmorStand);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEStack) {
			ItemStack stack = ((TEStack) te).getStack();
			if(stack.getItem() == Items.IRON_HORSE_ARMOR) {
				return state.withProperty(TYPE, EnumHorseArmor.IRON);
			} else if(stack.getItem() == Items.GOLDEN_HORSE_ARMOR) {
				return state.withProperty(TYPE, EnumHorseArmor.GOLD);
			} else if(stack.getItem() == Items.DIAMOND_HORSE_ARMOR) {
				return state.withProperty(TYPE, EnumHorseArmor.DIAMOND);
			} else {
				return state.withProperty(TYPE, EnumHorseArmor.EMPTY);
			}
		}
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
