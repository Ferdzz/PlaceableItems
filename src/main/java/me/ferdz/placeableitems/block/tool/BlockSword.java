package me.ferdz.placeableitems.block.tool;

import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import me.ferdz.placeableitems.state.tool.EnumSword;
import me.ferdz.placeableitems.state.tool.EnumToolMaterial;
import me.ferdz.placeableitems.tileentity.tool.TESword;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSword extends BlockTool {

	public static final PropertyEnum<EnumSword> MODEL = PropertyEnum.create("smodel", EnumSword.class);
	public static final PropertyEnum<EnumToolMaterial> MATERIAL = PropertyEnum.create("material", EnumToolMaterial.class);

	public BlockSword(String name) {
		super(name);
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(MATERIAL, EnumToolMaterial.WOOD);
	
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
		default:
			return state;
		}
	}
	
//	private void updateBox(IBlockState state) {
//		switch(state.getValue(MODEL)) {
//		case SIDE0:
//			break;
//		case SIDE135:
//			break;
//		case SIDE180:
//			break;
//		case SIDE225:
//			break;
//		case SIDE270:
//			break;
//		case SIDE315:
//			break;
//		case SIDE45:
//			break;
//		case SIDE90:
//			break;
//		case SIDE_IN0:
//			break;
//		case SIDE_IN135:
//			break;
//		case SIDE_IN180:
//			break;
//		case SIDE_IN225:
//			break;
//		case SIDE_IN270:
//			break;
//		case SIDE_IN315:
//			break;
//		case SIDE_IN45:
//			break;
//		case SIDE_IN90:
//			break;
//		case SIDE_LEAN:
//			break;
//		case SIDE_OUT0:
//			break;
//		case SIDE_OUT135:
//			break;
//		case SIDE_OUT180:
//			break;
//		case SIDE_OUT225:
//			break;
//		case SIDE_OUT270:
//			break;
//		case SIDE_OUT315:
//			break;
//		case SIDE_OUT45:
//			break;
//		case SIDE_OUT90:
//			break;
//		case TOP:
//			setBoundingBox(getBox(1, 3, 1));
//		case TOP_VERTICAL:
//			setBoundingBox(getBox(3, 7, 3));
//		}
//	}
	
//	private AxisAlignedBB getBox(double x, double y, double z) {
//		return new AxisAlignedBB(x / 16, 0, z / 16, (16 - x) / 16, y / 16, (16 - z) / 16);
//	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		Block under = worldIn.getBlockState(pos.subtract(new Vec3i(0, 1, 0))).getBlock();
		if((side == EnumFacing.EAST || side == EnumFacing.NORTH || side == EnumFacing.SOUTH || side == EnumFacing.WEST) && under == Blocks.AIR) {
			if(!(under instanceof BlockPlaceableItems)) {
				return true;
			}
			return false;
		}
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		
		if(worldIn.isRemote)
			return;
		
		TESword te = (TESword)worldIn.getTileEntity(pos);
		te.setModel(state.getValue(MODEL));
		te.setStack(stack.copy());
		worldIn.notifyBlockUpdate(pos, state, state, 2);
		te.markDirty();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
			return true;
		
		TESword te = (TESword)world.getTileEntity(pos);
		EnumSword modelState = te.getModel();

		modelState = modelState.next();
		if(world.getBlockState(pos.subtract(new Vec3i(0,1,0))).getBlock() == Blocks.AIR && modelState == EnumSword.SIDE_LEAN) {
			modelState = modelState.next();
		}
		
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
			Item i = sword.getStack().getItem();
			state = state.withProperty(MODEL, sword.getModel());
			if(i == Items.WOODEN_SWORD)
				return state.withProperty(MATERIAL, EnumToolMaterial.WOOD);
			else if (i == Items.STONE_SWORD)
				return state.withProperty(MATERIAL, EnumToolMaterial.STONE);
			else if (i == Items.IRON_SWORD)
				return state.withProperty(MATERIAL, EnumToolMaterial.IRON);
			else if (i == Items.GOLDEN_SWORD)
				return state.withProperty(MATERIAL, EnumToolMaterial.GOLD);
			else if (i == Items.DIAMOND_SWORD)
				return state.withProperty(MATERIAL, EnumToolMaterial.DIAMOND);
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ MODEL, MATERIAL, FACING });
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TESword();
	}
}
