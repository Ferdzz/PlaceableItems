package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.state.EnumCookieStackSize;
import me.ferdz.placeableitems.state.EnumIngotStackSize;
import me.ferdz.placeableitems.tileentity.TECookie;
import me.ferdz.placeableitems.tileentity.TEIngot;
import me.ferdz.placeableitems.tileentity.TEStack;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCookie extends BlockEdible {

    public static final PropertyEnum<EnumCookieStackSize> STACK = PropertyEnum.create("stack", EnumCookieStackSize.class);

    public BlockCookie(String name, int foodLevel, float saturation) {
        super(name, foodLevel, saturation);

        setDefaultState(super.getDefaultState().withProperty(STACK, EnumCookieStackSize._1));
        isBlockContainer = true;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote)
            return true;

        TileEntity te = worldIn.getTileEntity(pos);

        // to remove a cookie
        if(playerIn.isSneaking()) {
            ItemStack stack = new ItemStack(Items.COOKIE, 1);
            if(te instanceof TECookie) {
                EntityItem drop = new EntityItem(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, stack);
                worldIn.spawnEntity(drop);
                if(--((TECookie) te).stackSize == 0)
                    worldIn.setBlockToAir(pos);

                worldIn.notifyBlockUpdate(pos, state, state, 2);
                te.markDirty();
            }
            return true;
        }

        // to add a cookie
        if(heldItem == null || heldItem.getItem() != Items.COOKIE) {
            return false;
        }

        if(te instanceof TECookie) {
            int stackSize = ((TECookie) te).stackSize;
            if(stackSize == 4) return false;

            if(!playerIn.isCreative())
                heldItem.grow(-1);

            ((TECookie)te).stackSize++;
            worldIn.notifyBlockUpdate(pos, state, state, 2);
            te.markDirty();
        }
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Items.COOKIE, 1);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if(te instanceof TECookie) {
            return state.withProperty(STACK, EnumCookieStackSize.values()[((TECookie) te).stackSize - 1]);
        }
        return state;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { PLATED, FACING, STACK });
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TECookie();
    }
}
