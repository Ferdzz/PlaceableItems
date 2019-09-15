package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.block.component.impl.BoneBlockComponent;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsBlockRegistry {

    public static PlaceableItemsBlock BONE;
    public static PlaceableItemsBlock APPLE;
    public static PlaceableItemsBlock BAKED_POTATO;
    public static PlaceableItemsBlock BEEF;
    public static PlaceableItemsBlock BEETROOT;
    public static PlaceableItemsBlock BEETROOT_SEEDS;
    public static PlaceableItemsBlock BEETROOT_SOUP;
    public static PlaceableItemsBlock BLAZE_POWDER;
    public static PlaceableItemsBlock BOOK;
    public static PlaceableItemsBlock BOWL;
    public static PlaceableItemsBlock BREAD;
    public static PlaceableItemsBlock BRICK;
    public static PlaceableItemsBlock CARROT;
    public static PlaceableItemsBlock CHARCOAL;
    public static PlaceableItemsBlock CARROT_ON_A_STICK;
    public static PlaceableItemsBlock CHICKEN;
    public static PlaceableItemsBlock CHORUS_FRUIT;
    public static PlaceableItemsBlock CLAY_BALL;
    public static PlaceableItemsBlock COAL;
    public static PlaceableItemsBlock COOKED_BEEF;
    public static PlaceableItemsBlock COOKED_CHICKEN;
    public static PlaceableItemsBlock COOKED_MUTTON;
    public static PlaceableItemsBlock DIAMOND;
    public static PlaceableItemsBlock DRAGON_BREATH;
    public static PlaceableItemsBlock EGG;
    public static PlaceableItemsBlock EMERALD;
    public static PlaceableItemsBlock ENDER_EYE;
    public static PlaceableItemsBlock ENDER_PEARL;
    public static PlaceableItemsBlock FEATHER;
    public static PlaceableItemsBlock FIRE_CHARGE;
    public static PlaceableItemsBlock GLISTERING_MELON_SLICE;
    public static PlaceableItemsBlock GOLDEN_CARROT;
    public static PlaceableItemsBlock LEATHER;
    public static PlaceableItemsBlock MAGMA_CREAM;
    public static PlaceableItemsBlock MELON_SLICE;
    public static PlaceableItemsBlock MUSHROOM_STEW;
    public static PlaceableItemsBlock MUTTON;
    public static PlaceableItemsBlock POISONOUS_POTATO;
    public static PlaceableItemsBlock POPPED_CHORUS_FRUIT;
    public static PlaceableItemsBlock POTATO;

    public static PlaceableItemsBlock WRITABLE_BOOK;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        BONE = new PlaceableItemsBlockBuilder()
                .addComponent(new BoneBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bone_block", Items.BONE);
        // TODO: Make edible
        APPLE = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 9, 10))
                .register("apple_block", Items.APPLE);
        // TODO: Make edible
        BAKED_POTATO = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("baked_potato_block", Items.BAKED_POTATO);
        // TODO: Make edible
        BEEF = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("beef_block", Items.BEEF);
        // TODO: Make edible
        BEETROOT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("beetroot_block", Items.BEETROOT);
        BEETROOT_SEEDS =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 2, 10))
                .register("beetroot_seeds_block", Items.BEETROOT_SEEDS);
        // TODO: Make edible
        BEETROOT_SOUP =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(14, 4, 14))
                .register("beetroot_soup_block", Items.BEETROOT_SOUP);
        // TODO: Add animated particles
        BLAZE_POWDER =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(14, 6, 14))
                .register("blaze_powder_block", Items.BLAZE_POWDER);
        BOOK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(14, 6, 14))
                .register("book_block", Items.BOOK);
        BOWL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bowl_block", Items.BOWL);
        // TODO: Make edible
        BREAD = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 6, 12))
                .register("bread_block", Items.BREAD);
        BRICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("brick_block", Items.BRICK);
        // TODO: Make edible
        CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("carrot_block", Items.CARROT);
        CARROT_ON_A_STICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("carrot_on_a_stick_block", Items.CARROT_ON_A_STICK);
        CHARCOAL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 7, 12))
                .register("charcoal_block", Items.CHARCOAL);
        // TODO: Make edible
        CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("chicken_block", Items.CHICKEN);
        // TODO: Make edible & teleport the player when eaten
        CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(8, 7, 8))
                .register("chorus_fruit_block", Items.CHORUS_FRUIT);
        CLAY_BALL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 7, 12))
                .register("clay_ball_block", Items.CLAY_BALL);
        COAL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("coal_block", Items.COAL);
        // TODO: Make edible
        COOKED_BEEF =  new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("cooked_beef_block", Items.COOKED_BEEF);
        // TODO: Make edible
        COOKED_CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("cooked_chicken_block", Items.COOKED_CHICKEN);
        // TODO: Make edible
        COOKED_MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("cooked_mutton_block", Items.COOKED_MUTTON);
        DIAMOND = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("diamond_block", Items.DIAMOND);
        DRAGON_BREATH = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(8, 12, 8))
                .register("dragon_breath_block", Items.DRAGON_BREATH);
        // TODO: Breaks with a 1/8 chance of spawning a chicken when right-clicked
        // FIXME: The egg is thrown when placed, that interaction needs to be stopped somehow
        EGG = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("egg_block", Items.EGG);
        EMERALD = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(6, 6, 6))
                .register("emerald_block", Items.EMERALD);
        // FIXME: The ender eye is thrown when placed, that interaction needs to be stopped somehow
        ENDER_EYE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("ender_eye_block", Items.ENDER_EYE);
        // FIXME: The ender pearl is thrown when placed, that interaction needs to be stopped somehow
        ENDER_PEARL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("ender_pearl_block", Items.ENDER_PEARL);
        FEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 4, 10))
                .register("feather_block", Items.FEATHER);
        // FIXME: The fire charge is lit when placed, that interaction needs to be stopped somehow
        FIRE_CHARGE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("fire_charge_block", Items.FIRE_CHARGE);
        GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("glistering_melon_slice_block", Items.GLISTERING_MELON_SLICE);
        GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("golden_carrot_block", Items.GOLDEN_CARROT);
        LEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 3, 16))
                .register("leather_block", Items.LEATHER);
        MAGMA_CREAM = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 10, 16))
                .register("magma_cream_block", Items.MAGMA_CREAM);
        // TODO: Make edible
        MELON_SLICE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("melon_slice_block", Items.MELON_SLICE);
        // TODO: Make edible
        MUSHROOM_STEW = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mushroom_stew_block", Items.MUSHROOM_STEW);
        // TODO: Make edible
        MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mutton_block", Items.MUTTON);
        // TODO: Make edible
        POISONOUS_POTATO = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("poisonous_potato_block", Items.POISONOUS_POTATO);
        POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 7, 8))
                .register("popped_chorus_fruit_block", Items.POPPED_CHORUS_FRUIT);
        // TODO: Make edible
        POTATO = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("potato_block", Items.POTATO);

        // TODO: Allow for writing to the book when placed?
        // FIXME: The book is opened when placed, that interaction needs to be stopped somehow
        WRITABLE_BOOK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 5, 8))
                .register("writable_book_block", Items.WRITABLE_BOOK);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // Keep this for debugging purposes to use an ItemBlock
        // event.getRegistry().register(new BlockItem(BONE, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(BONE.getRegistryName()));
    }
}
