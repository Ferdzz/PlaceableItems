package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

public class RightClickHandler {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onItemRightClick(RightClickBlock e) {
		if (e.getEntityPlayer().isSneaking() && e.getFace() != null && e.getHand() == EnumHand.MAIN_HAND && e.getItemStack() != null && e.getSide() == Side.SERVER) {
			for (Item item : ModBlocks.blockMap.keySet()) {
				if (e.getItemStack().getItem().equals(item)) {
					BlockPos blockPos = e.getPos().offset(e.getFace());
					Block block = ModBlocks.blockMap.get(item);

					IBlockState state = block.onBlockPlaced(e.getWorld(), blockPos, e.getFace(), 0, 0, 0, 0, e.getEntityPlayer());
					e.getWorld().setBlockState(blockPos, state);
					block.onBlockPlacedBy(e.getWorld(), blockPos, state, e.getEntityPlayer(), e.getItemStack());

					if (!e.getEntityPlayer().isCreative())
						e.getItemStack().stackSize--;

					e.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onBookRightClick(RightClickItem e) {
		if (e.getEntityPlayer().isSneaking() && e.getItemStack() != null) {
			if (e.getItemStack().getItem().equals(Items.WRITABLE_BOOK) || e.getItemStack().getItem().equals(Items.WRITTEN_BOOK)) {
				e.setCanceled(true);
				return;
			}
		}
	}

	@SubscribeEvent
	public void onBucketRightClick(FillBucketEvent e) {
		if (e.getEntityPlayer().isSneaking()) {
			if (e.getTarget().typeOfHit == Type.BLOCK)
				e.setCanceled(true);
		}
	}
}
