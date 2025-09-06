package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PlaceableItemsBlockRegistry {
    // Create a Deferred Register to hold Blocks which will all be registered under the "placeableitems" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlaceableItems.MODID);

    public static final DeferredBlock<PlaceableItemsBlock> BEETROOT_SEEDS = PlaceableItemsBlockBuilder.of()
            .setShape(VoxelShapesUtil.create(10, 2, 10))
            .register("beetroot_seeds", Items.BEETROOT_SEEDS);
    public static final DeferredBlock<PlaceableItemsBlock> BOOK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 4, 10))
            .register("book", Items.BOOK);
    public static final DeferredBlock<PlaceableItemsBlock> BRICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("brick", Items.BRICK);
    public static final DeferredBlock<PlaceableItemsBlock> CARROT_ON_A_STICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("carrot_on_a_stick", Items.CARROT_ON_A_STICK);
    public static final DeferredBlock<PlaceableItemsBlock> CHARCOAL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 7, 12))
            .register("charcoal", Items.CHARCOAL);
    public static final DeferredBlock<PlaceableItemsBlock> CLAY_BALL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 7, 12))
            .register("clay_ball", Items.CLAY_BALL);
    public static final DeferredBlock<PlaceableItemsBlock> COAL = PlaceableItemsBlockBuilder.of()
            .setShape(VoxelShapesUtil.create(10, 6, 10))
            .register("coal", Items.COAL);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(7, 7, 7))
            .register("diamond", Items.DIAMOND);
    public static final DeferredBlock<PlaceableItemsBlock> EMERALD = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(7, 7, 7))
            .register("emerald", Items.EMERALD);
    public static final DeferredBlock<PlaceableItemsBlock> EXPERIENCE_BOTTLE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("experience_bottle", Items.EXPERIENCE_BOTTLE);
    public static final DeferredBlock<PlaceableItemsBlock> FEATHER = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 4, 10))
            .register("feather", Items.FEATHER);
    public static final DeferredBlock<PlaceableItemsBlock> GLASS_BOTTLE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("glass_bottle", Items.GLASS_BOTTLE);
    public static final DeferredBlock<PlaceableItemsBlock> GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 10, 10))
            .register("glistering_melon_slice", Items.GLISTERING_MELON_SLICE);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("golden_carrot", Items.GOLDEN_CARROT);
    public static final DeferredBlock<PlaceableItemsBlock> HEART_OF_THE_SEA = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("heart_of_the_sea", Items.HEART_OF_THE_SEA);
    public static final DeferredBlock<PlaceableItemsBlock> LEATHER = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 3, 16))
            .register("leather", Items.LEATHER);
    public static final DeferredBlock<PlaceableItemsBlock> NETHER_BRICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("nether_brick", Items.NETHER_BRICK);
    public static final DeferredBlock<PlaceableItemsBlock> POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(8, 7, 8))
            .register("popped_chorus_fruit", Items.POPPED_CHORUS_FRUIT);
    public static final DeferredBlock<PlaceableItemsBlock> SNOWBALL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 9, 12))
            .register("snowball", Items.SNOWBALL);
    public static final DeferredBlock<PlaceableItemsBlock> WHEAT = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 8, 12))
            .register("wheat", Items.WHEAT);
}
