package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumCompass;
import me.ferdz.placeableitems.state.EnumPreciseFacing;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCompass extends BlockFaceable {

	public static final PropertyEnum<EnumCompass> DIRECTION = PropertyEnum.create("direction", EnumCompass.class);
	
	public BlockCompass(String name) {
		super(name);
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		World world = Minecraft.getMinecraft().theWorld;
		
		if (world.provider.isSurfaceWorld())
        {
			int deltaZ = world.getSpawnPoint().getZ() - pos.getZ();
			int deltaX = world.getSpawnPoint().getX() - pos.getX();
			double t = (((Math.atan2(deltaZ, deltaX) * 180 / Math.PI))) + 180;
			
			EnumPreciseFacing facing = state.getValue(FACING);
			if(facing == EnumPreciseFacing.D270) {
				t += -90;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D90) {
				t += -270;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D0) {
				t += -180;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D45) {
				t += -135;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D315) {
				t += -225;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D225) {
				t += -315;
				if(t < 0)
					t = 360 - Math.abs(t);
			} else if (facing == EnumPreciseFacing.D135) {
				t += -45;
				if(t < 0)
					t = 360 - Math.abs(t);
			}

			t = Math.ceil((t*31)/360);
			return state.withProperty(DIRECTION, EnumCompass.values()[(int)t]);
		}

		return state;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{ DIRECTION, FACING });
    }
}
