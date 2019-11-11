package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.block.component.impl.*;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import me.ferdz.placeableitems.wiki.WikiDefinition;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsBlockRegistry {

    @WikiDefinition(model = "apple_down" )
    public static PlaceableItemsBlock APPLE;
    @WikiDefinition(model = "potato", textures = {
            @WikiDefinition.Texture(name = "potato", texture = "baked_potato")
    })
    public static PlaceableItemsBlock BAKED_POTATO;
    @WikiDefinition public static PlaceableItemsBlock BEEF;
    @WikiDefinition public static PlaceableItemsBlock BEETROOT;
    @WikiDefinition public static PlaceableItemsBlock BEETROOT_SEEDS;
    @WikiDefinition(model = "beetroot_soup", textures = {
            @WikiDefinition.Texture(name = "beetroot_soup_still", texture = "beetroot_soup_still"),
            @WikiDefinition.Texture(name = "bowl", texture = "bowl")
    })
    public static PlaceableItemsBlock BEETROOT_SOUP;
    @WikiDefinition public static PlaceableItemsBlock BLAZE_POWDER;
    @WikiDefinition public static PlaceableItemsBlock BONE;
    @WikiDefinition public static PlaceableItemsBlock BOOK;
    @WikiDefinition public static PlaceableItemsBlock BOW;
    @WikiDefinition public static PlaceableItemsBlock BOWL;
    @WikiDefinition public static PlaceableItemsBlock BREAD;
    @WikiDefinition public static PlaceableItemsBlock BRICK;
    @WikiDefinition(model = "bucket_down")
    public static PlaceableItemsBlock BUCKET;
    @WikiDefinition(model = "carrot_down")
    public static PlaceableItemsBlock CARROT;
    @WikiDefinition public static PlaceableItemsBlock CHARCOAL;
    @WikiDefinition public static PlaceableItemsBlock CARROT_ON_A_STICK;
    @WikiDefinition public static PlaceableItemsBlock CHICKEN;
    @WikiDefinition public static PlaceableItemsBlock CHORUS_FRUIT;
    @WikiDefinition public static PlaceableItemsBlock CLAY_BALL;
    @WikiDefinition public static PlaceableItemsBlock COAL;
    @WikiDefinition public static PlaceableItemsBlock COOKED_BEEF;
    @WikiDefinition public static PlaceableItemsBlock COOKED_CHICKEN;
    @WikiDefinition public static PlaceableItemsBlock COOKED_MUTTON;
    @WikiDefinition public static PlaceableItemsBlock COOKED_PORKCHOP;
    @WikiDefinition public static PlaceableItemsBlock COOKED_RABBIT;
    @WikiDefinition public static PlaceableItemsBlock COOKIE;
    @WikiDefinition public static PlaceableItemsBlock DIAMOND;
    @WikiDefinition public static PlaceableItemsBlock DRAGON_BREATH;
    @WikiDefinition public static PlaceableItemsBlock EGG;
    @WikiDefinition public static PlaceableItemsBlock EMERALD;
    @WikiDefinition public static PlaceableItemsBlock ENCHANTED_BOOK;
    @WikiDefinition public static PlaceableItemsBlock ENDER_EYE;
    @WikiDefinition public static PlaceableItemsBlock ENDER_PEARL;
    @WikiDefinition(model = "potion_down", textures = {
            @WikiDefinition.Texture(name = "texture", texture = "experience_bottle"),
            @WikiDefinition.Texture(name = "potion", texture = "experience_bottle_still")
            // TODO: Investigate why this doesn't work
    })
    public static PlaceableItemsBlock EXPERIENCE_BOTTLE;
    @WikiDefinition public static PlaceableItemsBlock FEATHER;
    @WikiDefinition public static PlaceableItemsBlock FIRE_CHARGE;
    @WikiDefinition public static PlaceableItemsBlock FIREWORK;
    @WikiDefinition public static PlaceableItemsBlock GLASS_BOTTLE;
    @WikiDefinition public static PlaceableItemsBlock GLISTERING_MELON_SLICE;
    @WikiDefinition public static PlaceableItemsBlock GOLD_INGOT;
    @WikiDefinition public static PlaceableItemsBlock GOLDEN_CARROT;
    @WikiDefinition public static PlaceableItemsBlock IRON_INGOT;
    @WikiDefinition public static PlaceableItemsBlock LAVA_BUCKET;
    @WikiDefinition public static PlaceableItemsBlock LEATHER;
    @WikiDefinition public static PlaceableItemsBlock MAGMA_CREAM;
    @WikiDefinition public static PlaceableItemsBlock MELON_SLICE;
    @WikiDefinition public static PlaceableItemsBlock MILK_BUCKET;
    @WikiDefinition public static PlaceableItemsBlock MUSHROOM_STEW;
    @WikiDefinition(name = "Music disc", model = "disc/blocks", textures = { @WikiDefinition.Texture(name = "texture", texture = "disc/blocks")})
    public static PlaceableItemsBlock MUSIC_DISC;
    @WikiDefinition public static PlaceableItemsBlock MUTTON;
    @WikiDefinition public static PlaceableItemsBlock NETHER_BRICK;
    @WikiDefinition public static PlaceableItemsBlock POISONOUS_POTATO;
    @WikiDefinition public static PlaceableItemsBlock POPPED_CHORUS_FRUIT;
    @WikiDefinition public static PlaceableItemsBlock PORKCHOP;
    @WikiDefinition public static PlaceableItemsBlock POTATO;
    @WikiDefinition public static PlaceableItemsBlock PUMPKIN_PIE;
    @WikiDefinition public static PlaceableItemsBlock RABBIT;
    @WikiDefinition public static PlaceableItemsBlock RABBIT_STEW;
    @WikiDefinition public static PlaceableItemsBlock ROTTEN_FLESH;
    @WikiDefinition public static PlaceableItemsBlock SLIMEBALL;
    @WikiDefinition public static PlaceableItemsBlock SNOWBALL;
    @WikiDefinition public static PlaceableItemsBlock SPIDER_EYE;
    @WikiDefinition public static PlaceableItemsBlock WATER_BUCKET;
    @WikiDefinition public static PlaceableItemsBlock WHEAT;
    @WikiDefinition public static PlaceableItemsBlock WRITABLE_BOOK;

    public static PlaceableItemsBlock SADDLE_STAND;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        APPLE = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 9, 10))
                .register("apple_block", Items.APPLE);
        BAKED_POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("baked_potato_block", Items.BAKED_POTATO);
        BEEF = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("beef_block", Items.BEEF);
        BEETROOT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("beetroot_block", Items.BEETROOT);
        BEETROOT_SEEDS =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 2, 10))
                .register("beetroot_seeds_block", Items.BEETROOT_SEEDS);
        BEETROOT_SOUP =  new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(true))
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
        BONE = new PlaceableItemsBlockBuilder()
                .addComponent(new BoneBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bone_block", Items.BONE);
        BOW = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bowl_block", Items.BOWL);
        BOWL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bow_block", Items.BOW);
        BREAD = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 6, 12))
                .register("bread_block", Items.BREAD);
        BRICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("brick_block", Items.BRICK);
        BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("bucket_block", Items.BUCKET);
        CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
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
        CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("chicken_block", Items.CHICKEN);
        CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
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
        COOKED_BEEF =  new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("cooked_beef_block", Items.COOKED_BEEF);
        COOKED_CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("cooked_chicken_block", Items.COOKED_CHICKEN);
        COOKED_MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("cooked_mutton_block", Items.COOKED_MUTTON);
        COOKED_PORKCHOP = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("cooked_porkchop_block", Items.COOKED_PORKCHOP);
        COOKED_RABBIT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 7, 10))
                .register("cooked_rabbit_block", Items.COOKED_RABBIT);
        COOKIE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(4))
                .build()
                .setShape(VoxelShapesUtil.create(12, 6, 12))
                .register("cookie_block", Items.COOKIE);
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
        EGG = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("egg_block", Items.EGG);
        EMERALD = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(6, 6, 6))
                .register("emerald_block", Items.EMERALD);
        ENCHANTED_BOOK = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(14, 8, 14))
                .register("enchanted_book_block", Items.ENCHANTED_BOOK);
        ENDER_EYE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("ender_eye_block", Items.ENDER_EYE);
        ENDER_PEARL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("ender_pearl_block", Items.ENDER_PEARL);
        EXPERIENCE_BOTTLE = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 12, 10))
                .register("experience_bottle_block", Items.EXPERIENCE_BOTTLE);
        FEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 4, 10))
                .register("feather_block", Items.FEATHER);
        FIRE_CHARGE = new PlaceableItemsBlockBuilder()
                .addComponent(new FireChargeBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("fire_charge_block", Items.FIRE_CHARGE);
        FIREWORK = new PlaceableItemsBlockBuilder()
                .addComponent(new FireworkBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 16, 10))
                .register("firework_block", Items.FIREWORK_ROCKET);
        GLASS_BOTTLE = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 12, 10))
                .register("glass_bottle_block", Items.GLASS_BOTTLE);
        GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("glistering_melon_slice_block", Items.GLISTERING_MELON_SLICE);
        GOLD_INGOT = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(6))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("gold_ingot_block", Items.GOLD_INGOT);
        GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("golden_carrot_block", Items.GOLDEN_CARROT);
        IRON_INGOT = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(6))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("iron_ingot_block", Items.IRON_INGOT);
        // TODO: fill/empty buckets
        LAVA_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("lava_bucket_block", Items.LAVA_BUCKET);
        LEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 3, 16))
                .register("leather_block", Items.LEATHER);
        MAGMA_CREAM = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 10, 16))
                .register("magma_cream_block", Items.MAGMA_CREAM);
        MELON_SLICE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("melon_slice_block", Items.MELON_SLICE);
        MILK_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("milk_bucket_block", Items.MILK_BUCKET);
        MUSHROOM_STEW = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(true))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mushroom_stew_block", Items.MUSHROOM_STEW);
        MUSIC_DISC = new PlaceableItemsBlockBuilder()
                .addComponent(new MusicDiscBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(13, 3, 13))
                .register("music_disc_block");
        MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mutton_block", Items.MUTTON);
        NETHER_BRICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("nether_brick_block", Items.NETHER_BRICK);
        POISONOUS_POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("poisonous_potato_block", Items.POISONOUS_POTATO);
        POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 7, 8))
                .register("popped_chorus_fruit_block", Items.POPPED_CHORUS_FRUIT);
        PORKCHOP = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("porkchop_block", Items.PORKCHOP);
        POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("potato_block", Items.POTATO);
        PUMPKIN_PIE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 7, 16))
                .register("pumpkin_pie_block", Items.PUMPKIN_PIE);
        RABBIT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 7, 10))
                .register("rabbit_block", Items.RABBIT);
        RABBIT_STEW = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(true))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("rabbit_stew_block", Items.RABBIT_STEW);
        ROTTEN_FLESH = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("rotten_flesh_block", Items.ROTTEN_FLESH);
        // TODO: Make bouncy
        SLIMEBALL = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(14, 9, 14))
                .register("slimeball_block", Items.SLIME_BALL);
        SNOWBALL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 9, 12))
                .register("snowball_block", Items.SNOWBALL);
        SPIDER_EYE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("spider_eye_block", Items.SPIDER_EYE);
        WATER_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("water_bucket_block", Items.WATER_BUCKET);
        WHEAT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("wheat_block", Items.WHEAT);
        // TODO: Allow for writing to the book when placed?
        WRITABLE_BOOK = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 5, 16))
                .register("writable_book_block", Items.WRITABLE_BOOK);
        // TODO: Add a crafting recipe
        SADDLE_STAND = new PlaceableItemsBlockBuilder()
                .addComponent(new SaddleStandBlockComponent())
                .build()
                .setShape(VoxelShapes.fullCube())
                .register("saddle_stand_block");
    }

    // TODO: Move this section to a TE registry

    public static TileEntityType<?> WRITABLE_BOOK_TILE_ENTITY;

    @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> e) {
        WRITABLE_BOOK_TILE_ENTITY = TileEntityType.Builder
                .create(StackHolderTileEntity::new, WRITABLE_BOOK)
                .build(null)
                .setRegistryName(PlaceableItems.MODID, "writable_book_block");
        e.getRegistry().register(WRITABLE_BOOK_TILE_ENTITY);
    }
}
