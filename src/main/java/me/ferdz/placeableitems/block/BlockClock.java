package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.state.EnumClockSide;
import me.ferdz.placeableitems.state.EnumTime;
import me.ferdz.placeableitems.tileentity.TEClock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockClock extends BlockPlaceableItems implements ITileEntityProvider {

	public static final PropertyEnum<EnumTime> TIME = PropertyEnum.create("time", EnumTime.class);
	public static final PropertyEnum<EnumClockSide> SIDE = PropertyEnum.create("side", EnumClockSide.class);
	
	public BlockClock(String name) {
		super(name);
		setTickRandomly(true);
		isBlockContainer = true;
	}
	
	@Override
	public void onBlockPlacedBySide(EnumFacing side, BlockPos pos, EntityPlayer player, World world) {
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TEClock) {
			TEClock clock = (TEClock)te;
			switch(side) {
			case EAST:
				clock.setSide(EnumClockSide.EAST);
				return;
			case WEST:
				clock.setSide(EnumClockSide.WEST);
				return;
			case NORTH:
				clock.setSide(EnumClockSide.NORTH);
				return;
			case SOUTH:
				clock.setSide(EnumClockSide.SOUTH);
				return;
			case UP:
				clock.setSide(EnumClockSide.DOWN);
			}
		}
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		if(side == EnumFacing.DOWN)
			return false;
		return true;
	}
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return true;
	}
	
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.randomDisplayTick(stateIn, worldIn, pos, rand);
		worldIn.markBlockRangeForRenderUpdate(pos, pos);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		World world = Minecraft.getMinecraft().theWorld;

		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TEClock) {
			TEClock clock = (TEClock)te;
			state = state.withProperty(SIDE, clock.getSide());
		}
		
		if (world.provider.isSurfaceWorld()) {
			long time = (((world.getWorldTime() % 24000) * 63) / 24000) - 16;
			if(time < 0)
				time = 64 + time;
			return state.withProperty(TIME, EnumTime.values()[(int) time]);
		}

		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ SIDE, TIME, FACING });
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEClock();
	}
}
