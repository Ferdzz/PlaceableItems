package me.ferdz.placeableitems.particle;

import me.ferdz.placeableitems.event.TextureStichHandler;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlazePowderParticle extends Particle {

	protected BlazePowderParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}
	
	public BlazePowderParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		
		this.motionX = this.motionX * 0.009999999776482582D + xSpeedIn;
        this.motionY = this.motionY * 0.009999999776482582D + ySpeedIn;
        this.motionZ = this.motionZ * 0.009999999776482582D + zSpeedIn;
		
		switch(worldIn.rand.nextInt(3)) {
		case 0:
			this.setParticleTexture(TextureStichHandler.blazePowder1);
			break;
		case 1:
			this.setParticleTexture(TextureStichHandler.blazePowder2);
			break;
		default:
			this.setParticleTexture(TextureStichHandler.blazePowder3);
			break;
		}
	}
}
