package me.ferdz.placeableitems.block;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.block.component.IBlockComponent;
import me.ferdz.placeableitems.init.PlaceableItemsItemRegistry;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PlaceableItemsBlock extends BaseEntityBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    @Nullable
    private Item placedItem;
    private BlockItem blockItem;
    private VoxelShape shape;
    private List<IBlockComponent> components;

    public PlaceableItemsBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.shape = Shapes.block();
        this.components = new ArrayList<>();
    }

    public PlaceableItemsBlock register(String name, IForgeRegistry<Block> registry) {
        this.setRegistryName(PlaceableItems.MODID, name);
        registry.register(this);
        for (IBlockComponent component : this.components) {
            component.register(this, name);
        }
        // Enables transparency & non full block models
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                ItemBlockRenderTypes.setRenderLayer(this.asBlock(), RenderType.cutout()));

        // Create & register the BlockItem
        PlaceableItemsItemRegistry.blocksRegistry.add(this);

        return this;
    }

    public PlaceableItemsBlock register(String name, Item item, IForgeRegistry<Block> registry) {
        this.placedItem = item;
        PlaceableItemsMap.instance().put(item, this);
        this.register(name, registry);
        return this;
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(worldIn, pos, state, placer, stack);
        for (IBlockComponent component : this.components) {
            component.setPlacedBy(worldIn, pos, state, placer, stack);
        }
    }

    // region State handling

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Calculates the angle & maps it to the rotation state
        BlockState blockState = this.defaultBlockState().setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15);
        for (IBlockComponent component : components) {
            blockState = component.getStateForPlacement(context, blockState);
        }
        return blockState;
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(ROTATION, rot.rotate(state.getValue(ROTATION), 16));
    }

    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(ROTATION, mirrorIn.mirror(state.getValue(ROTATION), 16));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }
    // endregion

    // region Item & drop management

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        // TODO: Rename
        // TODO: Fix potion pick block
        for (IBlockComponent component : this.components) {
            ItemStack itemStack = component.getCloneItemStack(state, target, level, pos, player);
            if (itemStack != null) {
                return itemStack;
            }
        }
        return new ItemStack(this.placedItem);
    }


    /// Used for the pick item & binding items in inventory
//    @Override
//    public Item asItem() {
//        for (IBlockComponent component : this.components) {
//            Item item = component.asItem();
//            if (item != null) {
//                return item;
//            }
//        }
//        return this.placedItem;
//    }

    /// Used for block placement in the ItemPlaceHandler
    public BlockItem getBlockItem() {
        return (BlockItem) this.asItem();
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
            itemStacks.add(new ItemStack(this.placedItem));
        }
        return itemStacks;
    }
    // endregion

    // region Bounding box
    @SuppressWarnings("deprecation") // This is fine to override
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
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

    // region BlockEntity
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        for (IBlockComponent component : this.components) {
            BlockEntity blockEntity = component.newBlockEntity(pos, state);
            if (blockEntity != null) {
                return blockEntity;
            }
        }
        return null;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        // Important, otherwise models are rendered invisible due to parent class
        return RenderShape.MODEL;
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
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.use(state, worldIn, pos, player, handIn, hit);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            return super.use(state, worldIn, pos, player, handIn, hit);
        } else {
            // TODO : Handle the result type
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter worldIn, Entity entityIn) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.updateEntityAfterFallOn(worldIn, entityIn);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.updateEntityAfterFallOn(worldIn, entityIn);
        }
    }

    @Override
    public void fallOn(Level worldIn, BlockState state, BlockPos pos, Entity entityIn, float fallDistance) {
        boolean hadAnImplementation = false;
        for (IBlockComponent component : this.components) {
            try {
                component.fallOn(worldIn, state, pos, entityIn, fallDistance);
                hadAnImplementation = true;
            } catch (AbstractBlockComponent.NotImplementedException e) {
                // There was no implementation in this component
            }
        }
        if (!hadAnImplementation) {
            super.fallOn(worldIn, state, pos, entityIn, fallDistance);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random random) {
        for (IBlockComponent component : this.components) {
            component.animateTick(stateIn, worldIn, pos, random);
        }
    }
    // endregion
}
