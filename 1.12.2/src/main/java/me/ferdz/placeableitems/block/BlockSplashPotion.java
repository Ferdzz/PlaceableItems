package me.ferdz.placeableitems.block;

import java.util.List;
import java.util.Random;

import me.ferdz.placeableitems.state.EnumPotionType;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSplashPotion extends BlockBiPosition implements ITileEntityProvider {

	public static final PropertyEnum<EnumPotionType> TYPE = PropertyEnum.create("type", EnumPotionType.class);

	public BlockSplashPotion(String name) {
		super(name);

		this.isBlockContainer = true;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TEStack) {
			ItemStack is = stack.copy();
			is.setCount(1);
			((TEStack) te).setStack(is);
		}
	}

	/**
	 * Spawns a splash effect as well as giving potions effects to nearby entities, refer to EntityPotion
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof TEStack) {
				ItemStack stack = ((TEStack) te).getStack();
				
				EntityItem drop = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
				
				PotionType potiontype = PotionUtils.getPotionFromItem(stack);
				List<PotionEffect> list = PotionUtils.getEffectsFromStack(stack);
				
				int d = potiontype.hasInstantEffect() ? 2007 : 2002;
	            worldIn.playEvent(d, drop.getPosition(), PotionUtils.getColor(stack));
	            
				AxisAlignedBB axisalignedbb = drop.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
				List<EntityLivingBase> list1 = worldIn.<EntityLivingBase> getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
				
				if (!list1.isEmpty()) {
					for (EntityLivingBase entitylivingbase : list1) {
						if (entitylivingbase.canBeHitWithPotion()) {
							double d0 = drop.getDistanceSqToEntity(entitylivingbase);
							
							if (d0 < 16.0D) {
								double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
								
								for (PotionEffect potioneffect1 : list) {
									Potion potion = potioneffect1.getPotion();
									
									if (potion.isInstant()) {
										potion.affectEntity(drop, playerIn, entitylivingbase, potioneffect1.getAmplifier(), d1);
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
		worldIn.setBlockToAir(pos);
		return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TEStack te = (TEStack) world.getTileEntity(pos);
		return te.getStack();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		TEStack te = (TEStack) worldIn.getTileEntity(pos);
		if (te.getStack().getItem().equals(Items.SPLASH_POTION)) {
			if (te.getStack().getTagCompound() == null) // if some the NBT is empty
				return state.withProperty(TYPE, EnumPotionType.NORMAL);

			String type = te.getStack().getTagCompound().getString("Potion");
			if (type == null)
				return state.withProperty(TYPE, EnumPotionType.NORMAL);

			type = type.substring(10).toUpperCase();
			EnumPotionType potionType = EnumPotionType.NORMAL;
			if (type.contains("FIRE_RESISTANCE"))
				potionType = EnumPotionType.FIRE_RESISTANCE;
			else if (type.contains("HARMING"))
				potionType = EnumPotionType.HARMING;
			else if (type.contains("HEALING"))
				potionType = EnumPotionType.HEALING;
			else if (type.contains("INVISIBILITY"))
				potionType = EnumPotionType.INVISIBILITY;
			else if (type.contains("LEAPING"))
				potionType = EnumPotionType.LEAPING;
			else if (type.contains("LUCK"))
				potionType = EnumPotionType.LUCK;
			else if (type.contains("NIGHT_VISION"))
				potionType = EnumPotionType.NIGHT_VISION;
			else if (type.contains("POISON"))
				potionType = EnumPotionType.POISON;
			else if (type.contains("REGENERATION"))
				potionType = EnumPotionType.REGENERATION;
			else if (type.contains("SLOWNESS"))
				potionType = EnumPotionType.SLOWNESS;
			else if (type.contains("STRENGTH"))
				potionType = EnumPotionType.STRENGTH;
			else if (type.contains("SWIFTNESS"))
				potionType = EnumPotionType.SWIFTNESS;
			else if (type.contains("WATER_BREATHING"))
				potionType = EnumPotionType.WATER_BREATHING;
			else if (type.contains("WEAKNESS"))
				potionType = EnumPotionType.WEAKNESS;
			else if (type.contains("WATER"))
				potionType = EnumPotionType.NORMAL;
			return state.withProperty(TYPE, potionType);
		} else {
			return state;
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockBiPosition.POSITION, TYPE, FACING });
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TEStack();
	}
	
	/* FOR LINGERING POTIONS (INCLUDE PSARTICLES)
	// STOLEN FROM EntityPotion.class
	private void makeAreaOfEffectCloud(World world, double x, double y, double z, EntityLivingBase thrower, ItemStack p_190542_1_, PotionType p_190542_2_) {
        EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(world, x, y, z);
        entityareaeffectcloud.setOwner(thrower);
        entityareaeffectcloud.setRadius(3.0F);
        entityareaeffectcloud.setRadiusOnUse(-0.5F);
        entityareaeffectcloud.setWaitTime(10);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.setPotion(p_190542_2_);

        for (PotionEffect potioneffect : PotionUtils.getFullEffectsFromItem(p_190542_1_))
        {
            entityareaeffectcloud.addEffect(new PotionEffect(potioneffect));
        }

        world.spawnEntity(entityareaeffectcloud);
    }
    */
}
