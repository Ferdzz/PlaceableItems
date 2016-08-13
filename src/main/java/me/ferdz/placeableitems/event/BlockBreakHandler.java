package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.tileentity.ITEStackHolder;
import net.minecraft.entity.item.EntityItem;
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
			if (!e.getPlayer().isCreative()) {
				ITEStackHolder stack = (ITEStackHolder) te;
				EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, stack.getStack());
				e.getWorld().spawnEntityInWorld(drop);
			}
		}
	}
}
