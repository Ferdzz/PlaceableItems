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

public class EntityAIEat extends EntityAIMoveToBlock {
    public static final int MAX_EAT_DURATION = 50;

	private final EntityCreature creature;
	private final Block[] blocks;
	private int eatDuration;
	
    public EntityAIEat(EntityCreature creature, double speed, Block[] blocks) {
        super(creature, speed, 20);
        this.creature = creature;
        this.blocks = blocks;
        this.eatDuration = MAX_EAT_DURATION;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        System.out.println(this.runDelay);
        boolean value = super.shouldExecute();
        if (value) {
            this.runDelay = 100 + this.creature.getRNG().nextInt(100);
        }
        return value;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask() {
        super.updateTask();

        this.creature.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5D, 10.0F, (float)this.creature.getVerticalFaceSpeed());
        
        if (this.getIsAboveDestination()) {
        	if (this.eatDuration > 0){
                --this.eatDuration;
            } else {
                World world = this.creature.world;
                BlockPos blockpos = this.destinationBlock.up();
                IBlockState iblockstate = world.getBlockState(blockpos);
                Block block = iblockstate.getBlock();

                for (Block b : blocks) {
                    if (block == b) {
                        world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
                        world.destroyBlock(blockpos, true);
                        this.eatDuration = MAX_EAT_DURATION;
                        break;
                    }
                }
            }
        }
    }

    @Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
    	pos = pos.up();
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
			
		if (block instanceof BlockPlaceableItems) {
			for (Block b : this.blocks) {
				if (block == b) {
					return true;
				}
        	}
		}
        return false;
    }
}
