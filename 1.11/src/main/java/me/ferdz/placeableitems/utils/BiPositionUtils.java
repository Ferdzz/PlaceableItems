package me.ferdz.placeableitems.utils;

import me.ferdz.placeableitems.block.BlockBiPosition;
import me.ferdz.placeableitems.state.EnumUpDown;
import me.ferdz.placeableitems.tileentity.TEEdibleBiPosition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;

public class BiPositionUtils {

	public static boolean canBlockStay(EnumUpDown upDown, IBlockAccess world, BlockPos pos) {
		if (upDown == EnumUpDown.DOWN) {
			if (Utils.isValidBlock(EnumFacing.UP, world, pos.subtract(new Vec3i(0,1,0)))) {
				return true;
			}
		} else {
			if (Utils.isValidBlock(EnumFacing.DOWN, world, pos.add(new Vec3i(0,1,0)))) {
				return true;
			}
		}
		return false;
	}

	public static AxisAlignedBB getReverseBound(AxisAlignedBB box, EnumUpDown enumUpDown) {
		switch (enumUpDown) {
		case DOWN:
			return box;
		case UP:
			return new AxisAlignedBB(box.minX, 1 - box.maxY, box.minZ, box.maxX, 1, box.maxZ);
			
		default:
			return null;
		}
	}

	public static EnumUpDown getPosition(TileEntity te, IBlockState state) {
		EnumUpDown upDown = EnumUpDown.DOWN;
		if(te instanceof TEEdibleBiPosition) {
			upDown = ((TEEdibleBiPosition) te).getPosition();
		} else {
			upDown = state.getValue(BlockBiPosition.POSITION);
		}
		return upDown;
	}
	
}
