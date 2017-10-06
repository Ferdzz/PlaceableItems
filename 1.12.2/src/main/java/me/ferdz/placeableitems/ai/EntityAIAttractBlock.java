package me.ferdz.placeableitems.ai;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EntityAIAttractBlock extends EntityAIBase {

	private EntityLiving entity;
	private List<Block> blocks;
	private BlockPos destPos;
	
	public EntityAIAttractBlock(EntityLiving entity, List<Block> blocks) {
		this.entity = entity;
		this.blocks = blocks;
	}
	
	@Override
	public boolean shouldExecute() {
		try {
			for (int i = -6; i < 7; i++) {
				for (int j = -6; j < 7; j++) {
					for (int k = -6; k < 7; k++) {
						BlockPos pos = entity.getPosition().add(i, j, k);
						if (blocks.contains(entity.getEntityWorld().getBlockState(pos).getBlock())) {
							destPos = pos.offset(EnumFacing.getFacingFromVector(pos.getX(), pos.getY(), pos.getZ()));
							return true;
						}
					}
				}
			}
		} catch (NullPointerException e) { /* not exactly sure why this happens sometimes */ }
		
		return false;
	}
	
	@Override
	public void startExecuting() {
		entity.getLookHelper().setLookPosition(destPos.getX() + 0.5D, destPos.getY(), destPos.getZ() + 0.5D, 30, 30);
	}
	
	@Override
	public void updateTask() {
		entity.getNavigator().setPath(entity.getNavigator().getPathToPos(destPos), 1);
		entity.getLookHelper().setLookPosition(destPos.getX() + 0.5D, destPos.getY(), destPos.getZ() + 0.5D, 30, 30);
	}
	
	public boolean continueExecuting() {
		return entity.getPosition().getDistance(destPos.getX(), destPos.getY(), destPos.getZ()) > 1 || entity.getNavigator().noPath();
	}

	@Override
	public void resetTask() {
		destPos = null;
	}
}
