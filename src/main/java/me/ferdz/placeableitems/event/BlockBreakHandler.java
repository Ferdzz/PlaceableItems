package me.ferdz.placeableitems.event;

import java.util.List;

import me.ferdz.placeableitems.block.BlockAppleGolden;
import me.ferdz.placeableitems.block.BlockArrow;
import me.ferdz.placeableitems.block.BlockBookAndQuill;
import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.block.BlockPotion;
import me.ferdz.placeableitems.block.BlockSplashPotion;
import me.ferdz.placeableitems.block.BlockTool;
import me.ferdz.placeableitems.tileentity.TEArrow;
import me.ferdz.placeableitems.tileentity.TEBook;
import me.ferdz.placeableitems.tileentity.TEGoldenApple;
import me.ferdz.placeableitems.tileentity.TEPotion;
import me.ferdz.placeableitems.tileentity.TETool;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakHandler {

	// only way to drop depending on TE state
	@SubscribeEvent
	public void onBlockBreak(BreakEvent e) {

		BlockPos upperPos = e.getPos().add(new Vec3i(0, 1, 0));
		if (e.getWorld().getBlockState(upperPos).getBlock() instanceof BlockPlaceableItems) {
			e.getWorld().destroyBlock(upperPos, !e.getPlayer().isCreative());
			return;
		}

		if (e.getPlayer().isCreative())
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
		} else if (block instanceof BlockSplashPotion) {
			TEPotion te = (TEPotion) e.getWorld().getTileEntity(e.getPos());
			EntityItem drop = new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getPotion());
			splash(e, te.getPotion(), new BlockPos(drop), drop);
		} else if (block instanceof BlockTool) {
			TETool te = (TETool) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getTool()));
		} else if (block instanceof BlockAppleGolden) {
			TEGoldenApple te = (TEGoldenApple) e.getWorld().getTileEntity(e.getPos());
			e.getWorld().spawnEntityInWorld(new EntityItem(e.getWorld(), e.getPos().getX() + 0.5D, e.getPos().getY() + 0.5D, e.getPos().getZ() + 0.5D, te.getApple()));
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
