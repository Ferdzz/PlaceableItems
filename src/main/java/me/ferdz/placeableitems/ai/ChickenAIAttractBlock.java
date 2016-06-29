package me.ferdz.placeableitems.ai;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ChickenAIAttractBlock extends EntityAIBase {

	private EntityChicken chicken;
	
	public ChickenAIAttractBlock(EntityChicken entity) {
		chicken = entity;
	}
	
	@Override
	public boolean shouldExecute() {
		for (int i = -6; i < 7; i++) {
			for (int j = -6; j < 7; j++) {
				for (int k = -6; k < 7; k++) {
					BlockPos pos = chicken.getPosition().add(i, j, k);
					if (chicken.getEntityWorld().getBlockState(pos).getBlock() == ModBlocks.blockBeetrootSeeds) {
						if(chicken.getPosition().getDistance(pos.getX(), pos.getY(), pos.getZ()) > 2) {
							pos = pos.offset(EnumFacing.getFacingFromVector(pos.getX(), pos.getY(), pos.getZ()));
							chicken.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1);
						}
						chicken.getLookHelper().setLookPosition(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 30, 30);
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
