package me.ferdz.placeableitems.ai;

import java.util.List;
import me.ferdz.placeableitems.block.BlockPlaceableItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIEat extends EntityAIMoveToBlock{
	private final EntityCreature creature;
	private final List<Block> blocks;
	protected int eatduration;
	
    public EntityAIEat(EntityCreature creatureIn, double speedIn, List<Block> blocksIn){
        super(creatureIn, speedIn, 20);
        this.creature = creatureIn;
        this.blocks = blocksIn;
        this.eatduration = 50;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
        if (this.runDelay <= 0){
            if (!this.creature.world.getGameRules().getBoolean("mobGriefing")){
            	return false;
            }
        }
        	return super.shouldExecute();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting(){
    	return super.shouldContinueExecuting();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    
    public void updateTask(){
        super.updateTask();
        this.creature.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.creature.getVerticalFaceSpeed());
        
        if (this.getIsAboveDestination()){
        	if (this.eatduration > 0){
                --this.eatduration;
            }
        	else
        	{
            World world = this.creature.world;
            BlockPos blockpos = this.destinationBlock.up();
            IBlockState iblockstate = world.getBlockState(blockpos);
            Block block = iblockstate.getBlock();{
            	for(Block b : blocks){
            		if(block == b){
            			System.out.println("ENTITY HAS EATEN");
            			world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
            			world.destroyBlock(blockpos, true);
            			this.eatduration = 50;
            			}
            		}
            	}
        	}
            this.runDelay = 250;
        }
    }

	protected boolean shouldMoveTo(World worldIn, BlockPos pos){
    	Block block = worldIn.getBlockState(pos).getBlock();
    	pos = pos.up();
		IBlockState iblockstate = worldIn.getBlockState(pos);
		block = iblockstate.getBlock();
			
		if (block instanceof BlockPlaceableItems){
			for (Block b : this.blocks){
				if(block == b){
					return true;
				}
        	}
		}
        return false;
    }
}
