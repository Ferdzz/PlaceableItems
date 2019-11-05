package me.ferdz.placeableitems.init;

import java.lang.ref.WeakReference;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.block.component.impl.BiPositionBlockComponent;
import me.ferdz.placeableitems.block.component.impl.BoneBlockComponent;
import me.ferdz.placeableitems.block.component.impl.EdibleBlockComponent;
import me.ferdz.placeableitems.block.component.impl.FireChargeBlockComponent;
import me.ferdz.placeableitems.block.component.impl.MusicDiscBlockComponent;
import me.ferdz.placeableitems.block.component.impl.SaddleStandBlockComponent;
import me.ferdz.placeableitems.block.component.impl.StackHolderBlockComponent;
import me.ferdz.placeableitems.block.component.impl.StackableBlockComponent;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import net.minecraft.item.Items;
import net.minecraft.util.shape.VoxelShapes;


//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsBlockRegistry {

    public static PlaceableItemsBlock BONE;
    public static PlaceableItemsBlock APPLE;
    public static PlaceableItemsBlock BAKED_POTATO;
    public static PlaceableItemsBlock BEEF;
    public static PlaceableItemsBlock BEETROOT;
    public static PlaceableItemsBlock BEETROOT_SEEDS;
    public static PlaceableItemsBlock BEETROOT_SOUP;
    public static PlaceableItemsBlock BLAZE_POWDER;
    public static PlaceableItemsBlock BOOK;
    public static PlaceableItemsBlock BOW;
    public static PlaceableItemsBlock BOWL;
    public static PlaceableItemsBlock BREAD;
    public static PlaceableItemsBlock BRICK;
    public static PlaceableItemsBlock BUCKET;
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
    public static PlaceableItemsBlock COOKED_PORKCHOP;
    public static PlaceableItemsBlock COOKED_RABBIT;
    public static PlaceableItemsBlock COOKIE;
    public static PlaceableItemsBlock DIAMOND;
    public static PlaceableItemsBlock DRAGON_BREATH;
    public static PlaceableItemsBlock EGG;
    public static PlaceableItemsBlock EMERALD;
    public static PlaceableItemsBlock ENCHANTED_BOOK;
    public static PlaceableItemsBlock ENDER_EYE;
    public static PlaceableItemsBlock ENDER_PEARL;
    public static PlaceableItemsBlock EXPERIENCE_BOTTLE;
    public static PlaceableItemsBlock FEATHER;
    public static PlaceableItemsBlock FIRE_CHARGE;
    public static PlaceableItemsBlock GLASS_BOTTLE;
    public static PlaceableItemsBlock GLISTERING_MELON_SLICE;
    public static PlaceableItemsBlock GOLD_INGOT;
    public static PlaceableItemsBlock GOLDEN_CARROT;
    public static PlaceableItemsBlock IRON_INGOT;
    public static PlaceableItemsBlock LAVA_BUCKET;
    public static PlaceableItemsBlock LEATHER;
    public static PlaceableItemsBlock MAGMA_CREAM;
    public static PlaceableItemsBlock MELON_SLICE;
    public static PlaceableItemsBlock MILK_BUCKET;
    public static PlaceableItemsBlock MUSHROOM_STEW;
    public static PlaceableItemsBlock MUSIC_DISC;
    public static PlaceableItemsBlock MUTTON;
    public static PlaceableItemsBlock NETHER_BRICK;
    public static PlaceableItemsBlock POISONOUS_POTATO;
    public static PlaceableItemsBlock POPPED_CHORUS_FRUIT;
    public static PlaceableItemsBlock PORKCHOP;
    public static PlaceableItemsBlock POTATO;
    public static PlaceableItemsBlock PUMPKIN_PIE;
    public static PlaceableItemsBlock RABBIT;
    public static PlaceableItemsBlock RABBIT_STEW;
    public static PlaceableItemsBlock ROTTEN_FLESH;
    public static PlaceableItemsBlock SLIMEBALL;
    public static PlaceableItemsBlock SNOWBALL;
    public static PlaceableItemsBlock SPIDER_EYE;
    public static PlaceableItemsBlock WATER_BUCKET;
    public static PlaceableItemsBlock WHEAT;
    public static PlaceableItemsBlock WRITABLE_BOOK;

    public static PlaceableItemsBlock SADDLE_STAND;

    public static WeakReference<PlaceableItemsBlock[]> blocks;
    public static WeakReference<PlaceableItemsBlock[]> stackHoldingBlocks;
    //public static PlaceableItemsBlock[] blocks;
    //public static PlaceableItemsBlock[] stackHoldingBlocks;
    
    //@SubscribeEvent
    public static void onBlocksRegistry() {

        blocks = new WeakReference<PlaceableItemsBlock[]>(

        new PlaceableItemsBlock[] {
                        //#region
                BONE = new PlaceableItemsBlockBuilder()
                        .addComponent(new BoneBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("bone_block", Items.BONE),
                APPLE = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 9, 10))
                        .register("apple_block", Items.APPLE),
                BAKED_POTATO = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(9, 6, 9))
                        .register("baked_potato_block", Items.BAKED_POTATO),
                BEEF = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 3, 12))
                        .register("beef_block", Items.BEEF),
                BEETROOT = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 6, 10))
                        .register("beetroot_block", Items.BEETROOT),
                BEETROOT_SEEDS =  new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 2, 10))
                        .register("beetroot_seeds_block", Items.BEETROOT_SEEDS),
                BEETROOT_SOUP =  new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent(true))
                        .build()
                        .setShape(VoxelShapesUtil.create(14, 4, 14))
                        .register("beetroot_soup_block", Items.BEETROOT_SOUP),
                // TODO: Add animated particles
                BLAZE_POWDER =  new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(14, 6, 14))
                        .register("blaze_powder_block", Items.BLAZE_POWDER),
                BOOK = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(14, 6, 14))
                        .register("book_block", Items.BOOK),
                BOWL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("bowl_block", Items.BOWL),
                BREAD = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 6, 12))
                        .register("bread_block", Items.BREAD),
                BRICK = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 4, 12))
                        .register("brick_block", Items.BRICK),
                CARROT = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 4, 12))
                        .register("carrot_block", Items.CARROT),
                CARROT_ON_A_STICK = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("carrot_on_a_stick_block", Items.CARROT_ON_A_STICK),
                CHARCOAL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 7, 12))
                        .register("charcoal_block", Items.CHARCOAL),
                CHICKEN = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 8, 12))
                        .register("chicken_block", Items.CHICKEN),
                CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(8, 7, 8))
                        .register("chorus_fruit_block", Items.CHORUS_FRUIT),
                CLAY_BALL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 7, 12))
                        .register("clay_ball_block", Items.CLAY_BALL),
                COAL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 6, 10))
                        .register("coal_block", Items.COAL),
                COOKED_BEEF =  new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 3, 12))
                        .register("cooked_beef_block", Items.COOKED_BEEF),
                COOKED_CHICKEN = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 8, 12))
                        .register("cooked_chicken_block", Items.COOKED_CHICKEN),
                COOKED_MUTTON = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("cooked_mutton_block", Items.COOKED_MUTTON),
                COOKED_PORKCHOP = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 3, 12))
                        .register("cooked_porkchop_block", Items.COOKED_PORKCHOP),
                COOKED_RABBIT = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 7, 10))
                        .register("cooked_rabbit_block", Items.COOKED_RABBIT),
                DIAMOND = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(8, 8, 8))
                        .register("diamond_block", Items.DIAMOND),
                DRAGON_BREATH = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(8, 12, 8))
                        .register("dragon_breath_block", Items.DRAGON_BREATH),
                // TODO: Breaks with a 1/8 chance of spawning a chicken when right-clicked
                EGG = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(8, 8, 8))
                        .register("egg_block", Items.EGG),
                EMERALD = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(6, 6, 6))
                        .register("emerald_block", Items.EMERALD),
                ENDER_EYE = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(7, 7, 7))
                        .register("ender_eye_block", Items.ENDER_EYE),
                ENDER_PEARL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(7, 7, 7))
                        .register("ender_pearl_block", Items.ENDER_PEARL),
                FEATHER = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 4, 10))
                        .register("feather_block", Items.FEATHER),
                FIRE_CHARGE = new PlaceableItemsBlockBuilder()
                        .addComponent(new FireChargeBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 10, 10))
                        .register("fire_charge_block", Items.FIRE_CHARGE),
                GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 10, 10))
                        .register("glistering_melon_slice_block", Items.GLISTERING_MELON_SLICE),
                GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 4, 12))
                        .register("golden_carrot_block", Items.GOLDEN_CARROT),
                LEATHER = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 3, 16))
                        .register("leather_block", Items.LEATHER),
                MAGMA_CREAM = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 10, 16))
                        .register("magma_cream_block", Items.MAGMA_CREAM),
                MELON_SLICE = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 10, 10))
                        .register("melon_slice_block", Items.MELON_SLICE),
                MUSIC_DISC = new PlaceableItemsBlockBuilder()
                        .addComponent(new MusicDiscBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(13, 3, 13))
                        .register("music_disc_block"),
                MUSHROOM_STEW = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent(true))
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("mushroom_stew_block", Items.MUSHROOM_STEW),
                MUTTON = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("mutton_block", Items.MUTTON),
                POISONOUS_POTATO = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(9, 6, 9))
                        .register("poisonous_potato_block", Items.POISONOUS_POTATO),
                POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(8, 7, 8))
                        .register("popped_chorus_fruit_block", Items.POPPED_CHORUS_FRUIT),
                PORKCHOP = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 3, 12))
                        .register("porkchop_block", Items.PORKCHOP),
                POTATO = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(9, 6, 9))
                        .register("potato_block", Items.POTATO),
                PUMPKIN_PIE = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 7, 16))
                        .register("pumpkin_pie_block", Items.PUMPKIN_PIE),
                RABBIT = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 7, 10))
                        .register("rabbit_block", Items.RABBIT),
                RABBIT_STEW = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent(true))
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("rabbit_stew_block", Items.RABBIT_STEW),
                ROTTEN_FLESH = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 4, 16))
                        .register("rotten_flesh_block", Items.ROTTEN_FLESH),
                // TODO: Make bouncy
                SLIMEBALL = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(14, 9, 14))
                        .register("slimeball_block", Items.SLIME_BALL),
                SNOWBALL = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 9, 12))
                        .register("snowball_block", Items.SNOWBALL),
                SPIDER_EYE = new PlaceableItemsBlockBuilder()
                        .addComponent(new EdibleBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 6, 10))
                        .register("spider_eye_block", Items.SPIDER_EYE),
                WHEAT = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 8, 12))
                        .register("wheat_block", Items.WHEAT),
                // TODO: Allow for writing to the book when placed?
                WRITABLE_BOOK = new PlaceableItemsBlockBuilder()
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 5, 16))
                        .register("writable_book_block", Items.WRITABLE_BOOK),

                // TODO: Add a crafting recipe
                SADDLE_STAND = new PlaceableItemsBlockBuilder()
                        .addComponent(new SaddleStandBlockComponent())
                        .build()
                        .setShape(VoxelShapes.fullCube())
                        .register("saddle_stand_block"),

                COOKIE = new PlaceableItemsBlockBuilder()
                        .addComponent(new StackableBlockComponent(4))
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 6, 12))
                        .register("cookie_block", Items.COOKIE),

                EXPERIENCE_BOTTLE = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 12, 10))
                        .register("experience_bottle_block", Items.EXPERIENCE_BOTTLE),

                GLASS_BOTTLE = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(10, 12, 10))
                        .register("glass_bottle_block", Items.GLASS_BOTTLE),

                GOLD_INGOT = new PlaceableItemsBlockBuilder()
                        .addComponent(new StackableBlockComponent(6))
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 6, 16))
                        .register("gold_ingot_block", Items.GOLD_INGOT),
                IRON_INGOT = new PlaceableItemsBlockBuilder()
                        .addComponent(new StackableBlockComponent(6))
                        .build()
                        .setShape(VoxelShapesUtil.create(16, 6, 16))
                        .register("iron_ingot_block", Items.IRON_INGOT),
                // TODO: fill/empty buckets
                LAVA_BUCKET = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 12, 12))
                        .register("lava_bucket_block", Items.LAVA_BUCKET),

                MILK_BUCKET = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 12, 12))
                        .register("milk_bucket_block", Items.MILK_BUCKET),
                WATER_BUCKET = new PlaceableItemsBlockBuilder()
                        .addComponent(new BiPositionBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(12, 12, 12))
                        .register("water_bucket_block", Items.WATER_BUCKET)
                //#endregion
        });

        stackHoldingBlocks = new WeakReference<PlaceableItemsBlock[]>( new PlaceableItemsBlock[]
        {
                ENCHANTED_BOOK = new PlaceableItemsBlockBuilder()
                        .addComponent(new StackHolderBlockComponent())
                        .build()
                        .setShape(VoxelShapesUtil.create(14, 8, 14))
                        .register("enchanted_book_block", Items.ENCHANTED_BOOK)
        });

        }

}
