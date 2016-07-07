package me.ferdz.placeableitems.ai;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EntityAIAttractBlock extends EntityAIBase {

	private EntityLiving entity;
	private List<Block> blocks;
	
	@SuppressWarnings("unchecked")
	public EntityAIAttractBlock(EntityLiving entity, Block... blocks) {
		this.entity = entity;
		this.blocks = Arrays.asList(blocks);
	}
	
	@Override
	public boolean shouldExecute() {
		for (int i = -6; i < 7; i++) {
			for (int j = -6; j < 7; j++) {
				for (int k = -6; k < 7; k++) {
					BlockPos pos = entity.getPosition().add(i, j, k);
					if (blocks.contains(entity.getEntityWorld().getBlockState(pos).getBlock())) {
						if(entity.getPosition().getDistance(pos.getX(), pos.getY(), pos.getZ()) > 2) {
							pos = pos.offset(EnumFacing.getFacingFromVector(pos.getX(), pos.getY(), pos.getZ()));
							entity.getNavigator().tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ(), 1);
						}
						entity.getLookHelper().setLookPosition(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 30, 30);
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
