package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.BlockArrow;
import me.ferdz.placeableitems.tileentity.TEArrow;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {

	// only way to drop depending on TE state
	@SubscribeEvent
	public void onBlockBreak(BreakEvent e) {
		if (e.getWorld().getBlockState(e.getPos()).getBlock() instanceof BlockArrow) {
			TEArrow te = (TEArrow) e.getWorld().getTileEntity(e.getPos());
			System.out.println(te.getType());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getType()));
		}
	}
}
