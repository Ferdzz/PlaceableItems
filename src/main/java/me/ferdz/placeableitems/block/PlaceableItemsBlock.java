package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceableItemsBlock extends Block {
    private static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;

    private Item item;
    private VoxelShape shape;
    private List<IBlockComponent> components;

    public PlaceableItemsBlock() {
        super(Block.Properties.create(Material.MISCELLANEOUS));
        this.shape = VoxelShapes.fullCube();
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlock register(String name, Item item) {
        this.item = item;
        this.setRegistryName(PlaceableItems.MODID, name);
        GameRegistry.findRegistry(Block.class).register(this);
        PlaceableItemsMap.instance().put(item, this);
        return this;
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        // TODO: Decide if we want to disable placement in odd places
        // return worldIn.getBlockState(pos.offset(Direction.DOWN)).isSolid();
        return super.isValidPosition(state, worldIn, pos);
    }

    // region Rendering

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT; // Enables transparency & non full block models
    }

    /// Returning 1 here will disable shadow
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    @OnlyIn(Dist.CLIENT)
    public float func_220080_a(BlockState state, IBlockReader blockReader, BlockPos pos) {
        return 1.0F;
    }

    // endregion

    // region State handling

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        // Calculates the angle & maps it to the rotation state
        BlockState blockState = this.getDefaultState().with(ROTATION, MathHelper.floor((double) (context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15);
        for (IBlockComponent component : components) {
            blockState = component.getStateForPlacement(context, blockState);
        }
        return blockState;
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, rot.rotate(state.get(ROTATION), 16));
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ROTATION, mirrorIn.mirrorRotation(state.get(ROTATION), 16));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }
    // endregion

    // region Item & drop management

    /// Used for the pick item
    @Override
    public Item asItem() {
        return this.item;
    }

    /// Used to get the itemstacks dropped when breaking the block
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Arrays.asList(new ItemStack(this.asItem()));
    }

    // endregion

    // region Bounding box

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        for (IBlockComponent component : this.components) {
            VoxelShape shape = component.getShape(this.shape, state, worldIn, pos, context);
            if (shape != null) {
                return shape;
            }
        }
        return this.shape;
    }

    public PlaceableItemsBlock setShape(VoxelShape shape) {
        this.shape = shape;
        return this;
    }

    // endregion

    // region Components

    public PlaceableItemsBlock addComponent(IBlockComponent component) {
        this.components.add(component);
        return this;
    }

    public PlaceableItemsBlock addComponents(Iterable<IBlockComponent> components) {
        for (IBlockComponent component : components) {
            this.addComponent(component);
        }
        return this;
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        boolean result = false;
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                result |= component.onBlockActivated(state, worldIn, pos, player, handIn, hit);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        } else {
            return result;
        }
    }

    // endregion
}
