package me.ferdz.placeableitems.block;

import java.util.Random;

import me.ferdz.placeableitems.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockPlaceableItems extends Block implements IBlockPlaceableItems {
	
	protected AxisAlignedBB boundingBox;
	protected Item placedItem;
	
	public BlockPlaceableItems(String name) {
		this(name, Material.WOOD);
	}
	
	public BlockPlaceableItems(String name, Material material) {
		super(material);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		GameRegistry.register(this);
	}

	/**
	 * Will set the bounding box. Parameters are in pixels  
	 * 1 = 1 / 16 of a block
	 * @return instance of the block
	 */
	public BlockPlaceableItems setBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.boundingBox = new AxisAlignedBB(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
		return this;
	}
	
	/**
	 * Will set the bounding box. Parameters are in pixels  
	 * 1 = 1 / 16 of a block
	 * @return instance of the block
	 */
	public BlockPlaceableItems setBoundingBox(double x, double y, double z) {
		return setBoundingBox(x, 0, z, (16 - x), y, (16 - z));
	}
	
	public BlockPlaceableItems setBoundingBox(AxisAlignedBB a) {
		this.boundingBox = a;
		return this;
	}
	
	public BlockPlaceableItems setItem(Item item) {
		this.placedItem = item;
		ModBlocks.blockMap.put(item, this);
		return this;
	}
	
	public BlockPlaceableItems setItems(Item... items) {
		for (Item item : items) {
			setItem(item);
		}
		return this;
	}
	
	/**
	 * Overriden if the block needs post-te logic with accessible side
	 * 
	 */
	public void onBlockPlacedBySide(EnumFacing side, BlockPos pos, EntityPlayer player, World world) { }
	
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		pos = pos.subtract(new Vec3i(0, 1, 0)); 
		return !worldIn.isAirBlock(pos) && !(worldIn.getBlockState(pos).getBlock() instanceof BlockPlaceableItems);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return this.boundingBox != null ? this.boundingBox : FULL_BLOCK_AABB;
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isVisuallyOpaque() {
		return false;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return 0;
	}
	
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.BLOCK;
	}
}