package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsBlock extends Block {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;

    @Nullable
    private Item item;
    private BlockItem blockItem;
    private VoxelShape shape;
    private List<IBlockComponent> components;

    public PlaceableItemsBlock() {
        super(Block.Properties.create(Material.MISCELLANEOUS));
        this.shape = VoxelShapes.fullCube();
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlock register(String name, IForgeRegistry<Block> registry) {
        this.setRegistryName(PlaceableItems.MODID, name);
        registry.register(this);
        for (IBlockComponent component : this.components) {
            component.register(this, name);
        }
        return this;
    }

    public PlaceableItemsBlock register(String name, Item item, IForgeRegistry<Block> registry) {
        this.item = item;
        PlaceableItemsMap.instance().put(item, this);
        return this.register(name, registry);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        for (IBlockComponent component : this.components) {
            component.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        }
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

    /// Used for the pick item & binding items in inventory
    @Override
    public Item asItem() {
        for (IBlockComponent component : this.components) {
            Item item = component.asItem();
            if (item != null) {
                return item;
            }
        }
        return this.item;
    }

    /// Used for block placement in the ItemPlaceHandler
    public BlockItem getBlockItem() {
        if (blockItem == null) {
            this.blockItem = new BlockItem(this, new Item.Properties());
        }

        return blockItem;
    }

    /// Used to get the itemstacks dropped when breaking the block
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (IBlockComponent component : this.components) {
            itemStacks.addAll(component.getDrops(state, builder));
        }
        if (!itemStacks.isEmpty()) {
            return itemStacks;
        }

        if (this.asItem() != null) {
            itemStacks.add(new ItemStack(this.asItem()));
        }
        return itemStacks;
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

    public VoxelShape getShape() {
        return shape;
    }

    // endregion

    // region TileEntity

    @Override
    public boolean hasTileEntity(BlockState state) {
        for (IBlockComponent component : this.components) {
            if (component.hasTileEntity(state)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        for (IBlockComponent component : this.components) {
            TileEntity tileEntity = component.createTileEntity(state, world);
            if (tileEntity != null) {
                return tileEntity;
            }
        }
        return null;
    }

    // endregion

    // region Components

    private PlaceableItemsBlock addComponent(IBlockComponent component) {
        this.components.add(component);
        return this;
    }

    PlaceableItemsBlock addComponents(Iterable<IBlockComponent> components) {
        for (IBlockComponent component : components) {
            this.addComponent(component);
        }
        return this;
    }

    public List<IBlockComponent> getComponents() {
        return components;
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

    @Override
    public void onLanded(IBlockReader worldIn, Entity entityIn) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.onLanded(worldIn, entityIn);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.onLanded(worldIn, entityIn);
        }
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.onFallenUpon(worldIn, pos, entityIn, fallDistance);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        }
    }

    // endregion
}
