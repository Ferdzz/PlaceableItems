package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumUpDown;
import me.ferdz.placeableitems.tileentity.TEEdibleBiPosition;
import me.ferdz.placeableitems.utils.BiPositionUtils;
import me.ferdz.placeableitems.utils.Utils;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBiPositionBiEdible extends BlockBiEdible implements IBlockBiPosition {

	public BlockBiPositionBiEdible(String name, Item rawItem, int rawFoodLevel, float rawSaturation, Item cookedItem, int cookedFoodLevel, float cookedSaturation) {
		super(name, rawItem, rawFoodLevel, rawSaturation, cookedItem, cookedFoodLevel, cookedSaturation);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		AxisAlignedBB box = super.getBoundingBox(state, source, pos);
		
		TileEntity te = source.getTileEntity(pos);
		
		if(te instanceof TEEdibleBiPosition) {
			return BiPositionUtils.getReverseBound(box, ((TEEdibleBiPosition)te).getPosition());
		}
		return null;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return true;
		} else if (Utils.isValidBlock(worldIn,pos.subtract(new Vec3i(0, 1, 0)))) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
		TileEntity te = world.getTileEntity(pos);
		EnumUpDown upDown = BiPositionUtils.getPosition(te, state);
		return BiPositionUtils.canBlockStay(upDown, world, pos);
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public void onBlockPlacedBySide(EnumFacing side, BlockPos pos, EntityPlayer player, World world) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TEEdibleBiPosition) {
			if (side == EnumFacing.DOWN) {
				((TEEdibleBiPosition) te).setPosition(EnumUpDown.UP);
			} else {
				((TEEdibleBiPosition) te).setPosition(EnumUpDown.DOWN);
			}
		}
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TEEdibleBiPosition) {
			TEEdibleBiPosition te2 = (TEEdibleBiPosition)te;
			return state.withProperty(BlockBiPosition.POSITION, te2.getPosition());
		}
		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ BlockBiPosition.POSITION, TYPE, FACING });
    }
	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEEdibleBiPosition();
	};
}
