package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.BlockEdible;
import me.ferdz.placeableitems.block.BlockIngot;
import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.tileentity.ITEStackHolder;
import me.ferdz.placeableitems.tileentity.TEIngot;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onBlockBreak(BreakEvent e) {
		if(e.getWorld().isRemote)
			return;
		
		// the code below this line is destined to handle TE block drops
		TileEntity te = e.getWorld().getTileEntity(e.getPos());
		if (te instanceof ITEStackHolder) {
			if (e.getPlayer() != null || !e.getPlayer().isCreative()) {
				ITEStackHolder stack = (ITEStackHolder) te;
				EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, stack.getStack());
				e.getWorld().spawnEntityInWorld(drop);
			}
		} else if (te instanceof TEIngot) {
			if(e.getPlayer() != null || !e.getPlayer().isCreative()) {
				ItemStack stack = null;
				switch(e.getState().getValue(BlockIngot.TYPE)) {
				case IRON:
					stack = new ItemStack(Items.IRON_INGOT, ((TEIngot)te).stackSize);					
					break;
				case GOLD:
					stack = new ItemStack(Items.GOLD_INGOT, ((TEIngot)te).stackSize);
					break;
				}
				
				EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, stack);
				e.getWorld().spawnEntityInWorld(drop);
			}
		}
		if(e.getState().getBlock() instanceof BlockEdible) {
			if(e.getState().getProperties().get(BlockEdible.PLATED) != null && e.getState().getValue(BlockEdible.PLATED)) { // if the block is edible and plated
				EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, new ItemStack(ModItems.plate));
				e.getWorld().spawnEntityInWorld(drop);
			}
		}
	}
}
