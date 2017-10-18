package me.ferdz.placeableitems.block;

import java.util.List;
import java.util.Random;

import me.ferdz.placeableitems.init.ModBlocks;
import me.ferdz.placeableitems.state.EnumPotionType;
import me.ferdz.placeableitems.tileentity.ITEStackHolder;
import me.ferdz.placeableitems.tileentity.TEPotion;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPotion extends BlockBiPositionDrinkable implements ITileEntityProvider, IBlockBiPosition {
	
	public static final PropertyEnum<EnumPotionType> TYPE = PropertyEnum.create("type", EnumPotionType.class);
	
	public BlockPotion(String name) {
		super(name, 0, 0);
		
		this.isBlockContainer = true;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof ITEStackHolder) {
			ItemStack is = stack.copy();
			is.setCount(1);
			((ITEStackHolder) te).setStack(is);
		}
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean b = super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
		
		if (b) {
			List<PotionEffect> effects = PotionUtils.getEffectsFromStack(((ITEStackHolder) worldIn.getTileEntity(pos)).getStack());
			for (PotionEffect effect : effects) {
				if(effect.getDuration() == 0 || effect.getDuration() == 1) // this is to avoid the fact that a tick goes by during the player drinking and therefore cancelling the effect of harming potions
					effect = new PotionEffect(effect.getPotion(), 1, effect.getAmplifier());
				
				playerIn.addPotionEffect(effect);
			}
			worldIn.setBlockState(pos, ModBlocks.blockBottleEmpty.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(BlockBiPosition.POSITION, state.getValue(BlockBiPosition.POSITION)));
		}
		
		return true;
	}
	
	// handled in BlockBreakHandler
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		ITEStackHolder te = (ITEStackHolder) world.getTileEntity(pos);
		return te.getStack();
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		ITEStackHolder te = (ITEStackHolder) worldIn.getTileEntity(pos);
		if (te.getStack().getItem().equals(Items.POTIONITEM)) {
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
		return new TEPotion();
	}
}
