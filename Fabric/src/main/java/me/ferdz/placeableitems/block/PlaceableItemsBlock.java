package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.blockentities.PlaceableItemBlockEntity;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PlaceableItemsBlock extends BlockWithEntity {

    public static final IntProperty ROTATION = Properties.ROTATION;

    //private static Map<Block, RenderLayer> renderLayerMap;

    @Nullable
    private Item item;
    private VoxelShape shape;
    private List<IBlockComponent> components;
    private BlockItem blockItem;

    public PlaceableItemsBlock() {
        // super(Block.Properties.create(Material.MISCELLANEOUS));
        //Blocks
        super(FabricBlockSettings.of(Material.PART).build());
        
        //BlockModelRenderer
        //BlockEntityRenderDispatcher
        this.shape = VoxelShapes.fullCube();
        this.components = new ArrayList<>();
    }


    @Override
    public boolean isOpaque(BlockState blockState_1) {
       return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.INVISIBLE;
    }

    public PlaceableItemsBlock register(String name) {
        Registry.register(Registry.BLOCK, new Identifier(PlaceableItems.MODID, name), this);
        //BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
        // if (renderLayerMap == null) {
        //     Field field;
        //     try {
        //         field = class_4696.class.getDeclaredFields()[0];
        //         field.setAccessible(true);
        //     } catch (SecurityException e) {
                
        //         field = null;
        //         e.printStackTrace();
        //     }
        //     if (field != null) {
        //         try {
        //             renderLayerMap = (Map<Block, RenderLayer>) field.get(null);
        //         } catch (IllegalArgumentException | IllegalAccessException e) {
        //             PlaceableItems.logger.warning("Failed to asign renderLayerMap\n");
        //             e.printStackTrace();
        //         }
        //     }
        // }
        // renderLayerMap.put(this, RenderLayer.getCutout());
        //GameRegistry.findRegistry(Block.class).register(this);
        for (IBlockComponent component : this.components) {
            component.register(this, name);
        }
        return this;
    }

    public PlaceableItemsBlock register(String name, Item item) {
        this.item = item;
        PlaceableItemsMap.instance().put(item, this);
        return this.register(name);
    }

    @Override
    //Block
    public void onPlaced(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onPlaced(worldIn, pos, state, placer, stack);
        for (IBlockComponent component : this.components) {
            component.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        }
    }

    // @Override
    // public RenderLayer getRenderLayer() {
    //     //EndRodBlock
    //     return RenderLayer.getCutout();
    //     //return BlockRenderLayer.CUTOUT; // Enables transparency & non full block models
    // }

    /// Returning 1 here will disable shadow
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    @Environment(EnvType.CLIENT)
    //@OnlyIn(Dist.CLIENT)
    public float getHardness(BlockState state, BlockView blockReader, BlockPos pos) {
        return 1.0F;
    }

    // endregion

    // region State handling

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        // Calculates the angle & maps it to the rotation state
        BlockState blockState = this.getDefaultState().with(ROTATION, MathHelper.floor((double) (context.getPlayerYaw() * 16.0F / 360.0F) + 0.5D) & 15);
        for (IBlockComponent component : components) {
            blockState = component.getStateForPlacement(context, blockState);
        }
        return blockState;
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(ROTATION, rot.rotate(state.get(ROTATION), 16));
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.with(ROTATION, mirrorIn.mirror(state.get(ROTATION), 16));
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
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

    // Used for block placement in the ItemPlaceHandler
    public BlockItem getBlockItem() {
        return getDefaultBlockItem();
    }

    protected BlockItem getDefaultBlockItem() {
        if (blockItem == null) {
            this.blockItem = new BlockItem(this, new Item.Settings());
        }

        return blockItem;
    }

    /// Used to get the itemstacks dropped when breaking the block
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
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

    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, EntityContext context) {
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

    // @Override
    // public boolean hasBlockEntity(BlockState state) {
    //     for (IBlockComponent component : this.components) {
    //         if (component.hasTileEntity(state)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // @Override
    // public BlockEntity createTileEntity(BlockState state, BlockView world) {
    //     for (IBlockComponent component : this.components) {
    //         BlockEntity BlockEntity = component.createTileEntity(state, world);
    //         if (BlockEntity != null) {
    //             return tileEntity;
    //         }
    //     }
    //     return null;
    // }

    // endregion

    // region Components

    @Override
    public boolean hasBlockEntity() {
        return true;
    }


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
    public boolean activate(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        for (IBlockComponent component : this.components) {
            try {
                if(component.onBlockActivated(state, worldIn, pos, player, handIn, hit))
                {
                                    return true;
                }
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        return super.activate(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        for (IBlockComponent component : this.components) {
            if(component.hasTileEntity())
            {
                return component.createTileEntity(blockView);
            }
        }
        return new PlaceableItemBlockEntity();
    }

    // endregion
}
