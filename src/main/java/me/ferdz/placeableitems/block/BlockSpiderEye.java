package me.ferdz.placeableitems.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSpiderEye extends BlockBiPositionEdible {

	public BlockSpiderEye(String name, int foodLevel, float saturation) {
		super(name, foodLevel, saturation);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		boolean b = super.onBlockActivated(worldIn, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
		
		if (worldIn.getBlockState(pos).getBlock() == Blocks.AIR) {
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 4 * 20));
		}
		return b;
	}
}
