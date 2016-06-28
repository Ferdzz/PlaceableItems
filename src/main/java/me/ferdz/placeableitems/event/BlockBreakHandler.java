package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.BlockArrow;
import me.ferdz.placeableitems.block.BlockBookAndQuill;
import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.block.BlockPotion;
import me.ferdz.placeableitems.block.BlockTool;
import me.ferdz.placeableitems.tileentity.TEArrow;
import me.ferdz.placeableitems.tileentity.TEBook;
import me.ferdz.placeableitems.tileentity.TEPotion;
import me.ferdz.placeableitems.tileentity.TETool;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {

	// only way to drop depending on TE state
	@SubscribeEvent
	public void onBlockBreak(BreakEvent e) {
		
		BlockPos upperPos = e.getPos().add(new Vec3i(0, 1, 0));
		if(e.getWorld().getBlockState(upperPos).getBlock() instanceof BlockPlaceableItems) {
			e.getWorld().destroyBlock(upperPos, !e.getPlayer().isCreative());
			return;
		}
		
		if(e.getPlayer().isCreative())
			return;

		Block block = e.getWorld().getBlockState(e.getPos()).getBlock();
		if (block instanceof BlockArrow) {
			TEArrow te = (TEArrow) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getArrow()));
		} else if (block instanceof BlockBookAndQuill) {
			TEBook te = (TEBook) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getBook()));
		} else if (block instanceof BlockPotion) {
			TEPotion te = (TEPotion) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getPotion()));
		} else if (block instanceof BlockTool) {
			TETool te = (TETool) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getTool()));
		}
	}
}
