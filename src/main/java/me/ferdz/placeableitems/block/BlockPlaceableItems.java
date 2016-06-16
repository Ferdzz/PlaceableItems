package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.block.state.EnumPreciseFacing;
import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPlaceableItems extends Block {

	public static final PropertyEnum<EnumPreciseFacing> FACING = PropertyDirection.create("facing", EnumPreciseFacing.class);
	
	private AxisAlignedBB boundingBox;
	private Item placedItem;
	
	public BlockPlaceableItems(Material material, String name) {
		super(material);
		
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumPreciseFacing.D0));
		setUnlocalizedName(name);
		setRegistryName(name);
		GameRegistry.register(this);
	}

	/**
	 * Will set the bounding box. Parameters are in pixels  
	 * 1 = 1 / 16 of a block
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 * @return
	 */
	public BlockPlaceableItems setBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.boundingBox = new AxisAlignedBB(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
		return this;
	}
	
	public BlockPlaceableItems setItem(Item item) {
		this.placedItem = item;
		ModBlocks.blockMap.put(item, this);
		return this;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return this.boundingBox != null ? this.boundingBox : new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(placedItem, 1);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return placedItem;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		float rotation = Math.abs(placer.rotationYaw);
		if (rotation <= 22.5 || rotation >= 337.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D270);
		else if (rotation > 22.5 && rotation <= 67.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D135);
		else if (rotation > 67.5 && rotation <= 112.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D180);
		else if (rotation > 112.5 && rotation <= 157.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D225);
		else if (rotation > 157.5 && rotation <= 202.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D90);
		else if (rotation > 202.5 && rotation <= 247.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D315);
		else if (rotation > 247.5 && rotation <= 292.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D0);
		else if (rotation > 292.5 && rotation <= 337.5)
			return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D45);
		return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.D0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumPreciseFacing.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).ordinal();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}
}