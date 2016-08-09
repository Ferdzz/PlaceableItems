package me.ferdz.placeableitems.event;

import java.util.List;

import me.ferdz.placeableitems.block.BlockSplashPotion;
import me.ferdz.placeableitems.tileentity.ITEStackHolder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
				
				if (te.getBlockType() instanceof BlockSplashPotion) {
					splash(e, stack.getStack(), new BlockPos(drop), drop);
				}
			}
		}
	}

	/**
	 * Spawns a splash effect as well as giving potions effects to nearby entities, refer to EntityPotion
	 */
	private void splash(BreakEvent e, ItemStack is, BlockPos pos, EntityItem drop) {
		World worldObj = e.getWorld();
		if (!worldObj.isRemote) {
			ItemStack itemstack = is;
			PotionType potiontype = PotionUtils.getPotionFromItem(itemstack);
			List<PotionEffect> list = PotionUtils.getEffectsFromStack(itemstack);

			worldObj.playEvent(2002, pos, PotionType.getID(potiontype));
			
			AxisAlignedBB axisalignedbb = drop.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
			List<EntityLivingBase> list1 = worldObj.<EntityLivingBase> getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

			if (!list1.isEmpty()) {
				for (EntityLivingBase entitylivingbase : list1) {
					if (entitylivingbase.canBeHitWithPotion()) {
						double d0 = drop.getDistanceSqToEntity(entitylivingbase);

						if (d0 < 16.0D) {
							double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

							for (PotionEffect potioneffect1 : list) {
								Potion potion = potioneffect1.getPotion();

								if (potion.isInstant()) {
									potion.affectEntity(drop,e.getPlayer(), entitylivingbase, potioneffect1.getAmplifier(), d1);
								} else {
									int i = (int) (d1 * (double) potioneffect1.getDuration() + 0.5D);

									if (i > 20) {
										entitylivingbase.addPotionEffect(new PotionEffect(potion, i, potioneffect1.getAmplifier()));
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
