package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.BlockEdible;
import me.ferdz.placeableitems.init.ModItems;
import me.ferdz.placeableitems.tileentity.ITEStackHolder;
import net.minecraft.entity.item.EntityItem;
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
		if(te instanceof ITEStackHolder &&
				(e.getPlayer() == null || !e.getPlayer().isCreative())) {
			ItemStack stack = ((ITEStackHolder) te).getDroppedItemStack(e.getState());
			if(stack == null)
				return;
			EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY(), e.getPos().getZ() + 0.5D, stack);
			e.getWorld().spawnEntity(drop);
		}
		
		// PLATES (not handled in TE)
		if(e.getState().getBlock() instanceof BlockEdible) {
			if(e.getState().getProperties().get(BlockEdible.PLATED) != null && e.getState().getValue(BlockEdible.PLATED)) { // if the block is edible and plated
				EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, new ItemStack(ModItems.itemPlate));
				e.getWorld().spawnEntity(drop);
//				e.getWorld().spawnEntityInWorld(drop);
			}
		}
	}
}
