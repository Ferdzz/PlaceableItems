package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.particle.BlazePowderParticle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBlazePowder extends BlockFaceable {

	public BlockBlazePowder(String name) {
		super(name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		super.randomDisplayTick(stateIn, worldIn, pos, rand);
		
		double xCoord = 0.2 + (0.8 - 0.2) * worldIn.rand.nextDouble();
		double zCoord = 0.2 + (0.8 - 0.2) * worldIn.rand.nextDouble();
		
		BlazePowderParticle particle = new BlazePowderParticle(worldIn, pos.getX() + xCoord, pos.getY() + 0.2, pos.getZ() + zCoord, 0, 0, 0);
		particle.setMaxAge(60);
		net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(particle);
	}
}
