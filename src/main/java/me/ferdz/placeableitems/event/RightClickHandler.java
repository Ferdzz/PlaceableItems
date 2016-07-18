package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
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
		BlockPos blockPos = e.getPos().offset(e.getFace());
		if(e.getWorld().getBlockState(e.getPos()).getBlock().isReplaceable(e.getWorld(), e.getPos())) // if the block is replaceable (grass), it changes the grass instead
			blockPos = e.getPos();
			
		if (e.getEntityPlayer().isSneaking() && e.getFace() != null && e.getHand() == EnumHand.MAIN_HAND && e.getItemStack() != null && e.getSide() == Side.SERVER) {
			for (Item item : ModBlocks.blockMap.keySet()) {
				if (e.getItemStack().getItem().equals(item)) {
					BlockPlaceableItems block = ModBlocks.blockMap.get(item);
					
					// Separates fish in their own BlockBiEdible according to the fishtype
					if(item == Items.FISH || item == Items.COOKED_FISH) {
						FishType type = FishType.byItemStack(e.getItemStack());
						switch (type) {
						case COD:
							block = ModBlocks.blockFish;
							break;
						case CLOWNFISH:
							block = ModBlocks.blockClownfish;
							break;
						case PUFFERFISH:
							block = ModBlocks.blockPufferfish;
							break;
						case SALMON:
							block = ModBlocks.blockSalmon;
							break;
						}
					}
					
					// Checks the validity of position
					IBlockState s = e.getWorld().getBlockState(blockPos);
					if(!e.getWorld().checkNoEntityCollision(new AxisAlignedBB(blockPos)) || 
							!e.getEntityPlayer().canPlayerEdit(e.getPos(), e.getFace(), null) || 
							!s.getMaterial().isReplaceable() || 
							!block.canPlaceBlockAt(e.getWorld(), blockPos) ||
							!block.canPlaceBlockOnSide(e.getWorld(), blockPos, e.getFace()))
						return;
					
					IBlockState state = block.onBlockPlaced(e.getWorld(), blockPos, e.getFace(), 0, 0, 0, 0, e.getEntityPlayer());
					e.getWorld().setBlockState(blockPos, state);
					block.onBlockPlacedBy(e.getWorld(), blockPos, state, e.getEntityPlayer(), e.getItemStack());
					block.onBlockPlacedBySide(e.getFace(), blockPos, e.getEntityPlayer(), e.getWorld());
					
					if (!e.getEntityPlayer().isCreative())
						e.getItemStack().stackSize--;

					e.setCanceled(true);
					break;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onBookRightClick(RightClickItem e) {
		if (e.getEntityPlayer().isSneaking() && e.getItemStack() != null) {
			Item item = e.getItemStack().getItem(); 
			if (item.equals(Items.WRITABLE_BOOK) || item.equals(Items.EXPERIENCE_BOTTLE) || item.equals(Items.SPLASH_POTION) || item.equals(Items.EGG) || item.equals(Items.SNOWBALL) || item.equals(Items.ENDER_PEARL) || item.equals(Items.ENDER_PEARL)) { // TODO: find a way to fix the drops from the egg, we want to allow shift right clicking, same thing with potions. Ray tracing?
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
