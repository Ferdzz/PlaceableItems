package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.block.PlaceableItemsBlockBuilder;
import me.ferdz.placeableitems.block.component.impl.*;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import me.ferdz.placeableitems.wiki.WikiDefinition;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsBlockRegistry {

    @WikiDefinition(model = "apple_down")
    public static PlaceableItemsBlock APPLE;
    @WikiDefinition(model = "potato", textures = {
            @WikiDefinition.Texture(name = "potato", texture = "baked_potato")
    })
    public static PlaceableItemsBlock BAKED_POTATO;
    @WikiDefinition(model = "beef_down")
    public static PlaceableItemsBlock BEEF;
    @WikiDefinition public static PlaceableItemsBlock BEETROOT;
    @WikiDefinition public static PlaceableItemsBlock BEETROOT_SEEDS;
    @WikiDefinition public static PlaceableItemsBlock BEETROOT_SOUP;
    @WikiDefinition public static PlaceableItemsBlock BLAZE_POWDER;
    @WikiDefinition(description = "When right clicked, 3 bone meal will be dropped")
    public static PlaceableItemsBlock BONE;
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
    @WikiDefinition(model = "chicken_down")
    public static PlaceableItemsBlock CHICKEN;
    @WikiDefinition public static PlaceableItemsBlock CHORUS_FRUIT;
    @WikiDefinition public static PlaceableItemsBlock CLAY_BALL;
    @WikiDefinition public static PlaceableItemsBlock COAL;
    @WikiDefinition(model = "beef_down", textures = {
            @WikiDefinition.Texture(name = "beef", texture = "cooked_beef")
    })
    public static PlaceableItemsBlock COOKED_BEEF;
    @WikiDefinition(model = "chicken_down", textures = {
            @WikiDefinition.Texture(name = "chicken", texture = "cooked_chicken")
    })
    public static PlaceableItemsBlock COOKED_CHICKEN;
    @WikiDefinition(model = "mutton_down", textures = {
            @WikiDefinition.Texture(name = "mutton", texture = "cooked_mutton")
    })
    public static PlaceableItemsBlock COOKED_MUTTON;
    @WikiDefinition(model = "beef_down", textures = {
            @WikiDefinition.Texture(name = "beef", texture = "cooked_porkchop")
    })
    public static PlaceableItemsBlock COOKED_PORKCHOP;
    @WikiDefinition(model = "rabbit_down", textures = {
            @WikiDefinition.Texture(name = "rabbit", texture = "cooked_rabbit")
    })
    public static PlaceableItemsBlock COOKED_RABBIT;
    @WikiDefinition(model = "cookie/cookie_4")
    public static PlaceableItemsBlock COOKIE;
    @WikiDefinition public static PlaceableItemsBlock DIAMOND;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/diamond_axxe")
    })
    public static PlaceableItemsBlock DIAMOND_AXE;
    @WikiDefinition(model = "hoe/wooden_hoe", textures = {
            @WikiDefinition.Texture(name = "wooden_hoe", texture = "hoe/diamond_hoe")
    })
    public static PlaceableItemsBlock DIAMOND_HOE;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/diamond_pickaxe")
    })
    public static PlaceableItemsBlock DIAMOND_PICKAXE;
    @WikiDefinition(model = "shovel/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "shovel/diamond_shovel")
    })
    public static PlaceableItemsBlock DIAMOND_SHOVEL;
    @WikiDefinition(model = "sword/diamond_sword_0")
    public static PlaceableItemsBlock DIAMOND_SWORD;
    @WikiDefinition(model = "dragon_breath_down", textures = {
            @WikiDefinition.Texture(name = "glass_bottle", texture = "glass_bottle"),
            @WikiDefinition.Texture(name = "dragon_breath", texture = "dragon_breath"),
            @WikiDefinition.Texture(name = "dragon_breath1", texture = "dragon_breath1"),
            @WikiDefinition.Texture(name = "dragon_breath2", texture = "dragon_breath2"),
            @WikiDefinition.Texture(name = "dragon_breath3", texture = "dragon_breath3"),
            @WikiDefinition.Texture(name = "dragon_breath4", texture = "dragon_breath4"),
            @WikiDefinition.Texture(name = "dragon_breath", texture = "dragon_breath")
    })
    public static PlaceableItemsBlock DRAGON_BREATH;
    @WikiDefinition(description = "When right clicked, a baby chicken has a 1/8 chance to spawn")
    public static PlaceableItemsBlock EGG;
    @WikiDefinition public static PlaceableItemsBlock EMERALD;
    @WikiDefinition public static PlaceableItemsBlock ENCHANTED_BOOK;
    @WikiDefinition public static PlaceableItemsBlock ENDER_EYE;
    @WikiDefinition public static PlaceableItemsBlock ENDER_PEARL;
    @WikiDefinition public static PlaceableItemsBlock EXPERIENCE_BOTTLE;
    @WikiDefinition public static PlaceableItemsBlock FEATHER;
    @WikiDefinition public static PlaceableItemsBlock FIRE_CHARGE;
    @WikiDefinition public static PlaceableItemsBlock FIREWORK_ROCKET;
    @WikiDefinition public static PlaceableItemsBlock GLASS_BOTTLE;
    @WikiDefinition(model = "melon_slice", textures = {
            @WikiDefinition.Texture(name = "melon_slice", texture = "glistering_melon_slice")
    })
    public static PlaceableItemsBlock GLISTERING_MELON_SLICE;
    @WikiDefinition(model = "ingot/gold_ingot_6")
    public static PlaceableItemsBlock GOLD_INGOT;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/golden_axe")
    })
    public static PlaceableItemsBlock GOLDEN_AXE;
    @WikiDefinition(model = "carrot_down", textures = {
            @WikiDefinition.Texture(name = "carrot", texture = "golden_carrot")
    })
    public static PlaceableItemsBlock GOLDEN_CARROT;
    @WikiDefinition(model = "hoe/wooden_hoe", textures = {
            @WikiDefinition.Texture(name = "wooden_hoe", texture = "hoe/golden_hoe")
    })
    public static PlaceableItemsBlock GOLDEN_HOE;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/golden_pickaxe")
    })
    public static PlaceableItemsBlock GOLDEN_PICKAXE;
    @WikiDefinition(model = "shovel/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "shovel/golden_shovel")
    })
    public static PlaceableItemsBlock GOLDEN_SHOVEL;
    @WikiDefinition(model = "sword/sword_0", textures = {
            @WikiDefinition.Texture(name = "diamond_sword", texture = "sword/golden_sword")
    })
    public static PlaceableItemsBlock GOLDEN_SWORD;
    @WikiDefinition public static PlaceableItemsBlock HEART_OF_THE_SEA;
    @WikiDefinition public static PlaceableItemsBlock HONEY_BOTTLE;
    @WikiDefinition(model = "ingot/iron_ingot_6")
    public static PlaceableItemsBlock IRON_INGOT;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/iron_axe")
    })
    public static PlaceableItemsBlock IRON_AXE;
    @WikiDefinition(model = "hoe/wooden_hoe", textures = {
            @WikiDefinition.Texture(name = "wooden_hoe", texture = "hoe/iron_hoe")
    })
    public static PlaceableItemsBlock IRON_HOE;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/iron_pickaxe")
    })
    public static PlaceableItemsBlock IRON_PICKAXE;
    @WikiDefinition(model = "pickaxe/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "pickaxe/iron_shovel")
    })
    public static PlaceableItemsBlock IRON_SHOVEL;
    @WikiDefinition(model = "sword/sword_0", textures = {
            @WikiDefinition.Texture(name = "diamond_sword", texture = "sword/iron_sword")
    })
    public static PlaceableItemsBlock IRON_SWORD;
    @WikiDefinition(model = "bucket_filled_down", textures = {
            @WikiDefinition.Texture(name = "bucket", texture = "bucket"),
            @WikiDefinition.Texture(name = "lava_still", texture = "lava_still")
    })
    public static PlaceableItemsBlock LAVA_BUCKET;
    @WikiDefinition public static PlaceableItemsBlock LEATHER;
    @WikiDefinition public static PlaceableItemsBlock LINGERING_POTION;
    @WikiDefinition(model = "magma_cream_down")
    public static PlaceableItemsBlock MAGMA_CREAM;
    @WikiDefinition public static PlaceableItemsBlock MELON_SLICE;
    @WikiDefinition(model = "bucket_filled_down", textures = {
            @WikiDefinition.Texture(name = "bucket", texture = "bucket"),
            @WikiDefinition.Texture(name = "lava_still", texture = "milk_still")
    })
    public static PlaceableItemsBlock MILK_BUCKET;
    @WikiDefinition(model = "mushroom_stew", textures = {
            @WikiDefinition.Texture(name = "mushroom_stew", texture = "mushroom_stew"),
            @WikiDefinition.Texture(name = "bowl", texture = "bowl")
    })
    public static PlaceableItemsBlock MUSHROOM_STEW;
    @WikiDefinition(name = "Music disc", model = "disc/disc", textures = {
            @WikiDefinition.Texture(name = "blocks", texture = "disc/blocks")
    })
    public static PlaceableItemsBlock MUSIC_DISC;
    @WikiDefinition(model = "mutton_down")
    public static PlaceableItemsBlock MUTTON;
    @WikiDefinition(model = "brick", textures = {
            @WikiDefinition.Texture(name = "brick", texture = "nether_brick")
    })
    public static PlaceableItemsBlock NETHER_BRICK;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/netherite_axe")
    })
    public static PlaceableItemsBlock NETHERITE_AXE;
    @WikiDefinition(model = "hoe/wooden_hoe", textures = {
            @WikiDefinition.Texture(name = "wooden_hoe", texture = "hoe/netherite_hoe")
    })
    public static PlaceableItemsBlock NETHERITE_HOE;
    @WikiDefinition(model = "ingot/netherite_ingot_6")
    public static PlaceableItemsBlock NETHERITE_INGOT;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/netherite_pickaxe")
    })
    public static PlaceableItemsBlock NETHERITE_PICKAXE;
    @WikiDefinition(model = "shovel/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "shovel/netherite_shovel")
    })
    public static PlaceableItemsBlock NETHERITE_SHOVEL;
    @WikiDefinition(model = "sword/sword_0", textures = {
            @WikiDefinition.Texture(name = "diamond_sword", texture = "sword/netherite_sword")
    })
    public static PlaceableItemsBlock NETHERITE_SWORD;
    @WikiDefinition(model = "potato", textures = {
            @WikiDefinition.Texture(name = "potato", texture = "poisonous_potato")
    })
    public static PlaceableItemsBlock POISONOUS_POTATO;
    @WikiDefinition public static PlaceableItemsBlock POPPED_CHORUS_FRUIT;
    @WikiDefinition(model = "beef_down", textures = {
            @WikiDefinition.Texture(name = "beef", texture = "porkchop")
    })
    public static PlaceableItemsBlock PORKCHOP;
    @WikiDefinition public static PlaceableItemsBlock POTION;
    @WikiDefinition public static PlaceableItemsBlock POTATO;
    @WikiDefinition public static PlaceableItemsBlock PUMPKIN_PIE;
    @WikiDefinition(model = "rabbit_down")
    public static PlaceableItemsBlock RABBIT;
    @WikiDefinition(model = "rabbit_stew", textures = {
            @WikiDefinition.Texture(name = "rabbit_stew", texture = "rabbit_stew"),
            @WikiDefinition.Texture(name = "mushroom_stew", texture = "mushroom_stew"),
            @WikiDefinition.Texture(name = "bowl", texture = "bowl")
    })
    public static PlaceableItemsBlock RABBIT_STEW;
    @WikiDefinition public static PlaceableItemsBlock ROTTEN_FLESH;
    @WikiDefinition(model = "slimeball_down", textures = {
            @WikiDefinition.Texture(name = "slimeball", texture = "slimeball")
    })
    public static PlaceableItemsBlock SLIMEBALL;
    @WikiDefinition public static PlaceableItemsBlock SNOWBALL;
    @WikiDefinition public static PlaceableItemsBlock SPIDER_EYE;
    @WikiDefinition public static PlaceableItemsBlock SPLASH_POTION;
    @WikiDefinition public static PlaceableItemsBlock SUSPICIOUS_STEW;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/stone_axe")
    })
    public static PlaceableItemsBlock STONE_AXE;
    @WikiDefinition(model = "hoe/wooden_hoe", textures = {
            @WikiDefinition.Texture(name = "wooden_hoe", texture = "hoe/stone_hoe")
    })
    public static PlaceableItemsBlock STONE_HOE;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/stone_pickaxe")
    })
    public static PlaceableItemsBlock STONE_PICKAXE;
    @WikiDefinition(model = "shovel/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "shovel/stone_shovel")
    })
    public static PlaceableItemsBlock STONE_SHOVEL;
    @WikiDefinition(model = "sword/wooden_sword_0")
    public static PlaceableItemsBlock STONE_SWORD;
    @WikiDefinition(model = "bucket_filled_down", textures = {
            @WikiDefinition.Texture(name = "bucket", texture = "bucket"),
            @WikiDefinition.Texture(name = "lava_still", texture = "water_still")
    })
    public static PlaceableItemsBlock WATER_BUCKET;
    @WikiDefinition public static PlaceableItemsBlock WHEAT;
    @WikiDefinition(model = "axe/wooden_axe", textures = {
            @WikiDefinition.Texture(name = "wooden_axe", texture = "axe/wooden_axe")
    })
    public static PlaceableItemsBlock WOODEN_AXE;
    @WikiDefinition(model = "hoe/wooden_hoe")
    public static PlaceableItemsBlock WOODEN_HOE;
    @WikiDefinition(model = "pickaxe/wooden_pickaxe", textures = {
            @WikiDefinition.Texture(name = "wooden_pickaxe", texture = "pickaxe/wooden_pickaxe")
    })
    public static PlaceableItemsBlock WOODEN_PICKAXE;
    @WikiDefinition(model = "shovel/wooden_shovel", textures = {
            @WikiDefinition.Texture(name = "wooden_shovel", texture = "pickaxe/wooden_shovel")
    })
    public static PlaceableItemsBlock WOODEN_SHOVEL;
    @WikiDefinition(model = "sword/wooden_sword_0", textures = {
            @WikiDefinition.Texture(name = "wooden_sword", texture = "sword/wooden_sword")
    })
    public static PlaceableItemsBlock WOODEN_SWORD;
    @WikiDefinition public static PlaceableItemsBlock WRITABLE_BOOK;
    @WikiDefinition public static PlaceableItemsBlock WRITTEN_BOOK;

    public static PlaceableItemsBlock HORSE_ARMOR_STAND;
    public static PlaceableItemsBlock SADDLE_STAND;

    private PlaceableItemsBlockRegistry() {}

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        // Define BOWL first because it's used later
        BOWL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bowl_block", Items.BOWL, registry);

        APPLE = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(7, 8, 7))
                .register("apple_block", Items.APPLE, registry);
        BAKED_POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("baked_potato_block", Items.BAKED_POTATO, registry);
        BEEF = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("beef_block", Items.BEEF, registry);
        BEETROOT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("beetroot_block", Items.BEETROOT, registry);
        BEETROOT_SEEDS = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 2, 10))
                .register("beetroot_seeds_block", Items.BEETROOT_SEEDS, registry);
        BEETROOT_SOUP = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(BOWL))
                .build()
                .setShape(VoxelShapesUtil.create(14, 4, 14))
                .register("beetroot_soup_block", Items.BEETROOT_SOUP, registry);
        BLAZE_POWDER = new PlaceableItemsBlockBuilder()
                .addComponent(new BlazePowderBlockComponent())
                .setLightLevel(10)
                .build()
                .setShape(VoxelShapesUtil.create(14, 6, 14))
                .register("blaze_powder_block", Items.BLAZE_POWDER, registry);
        BOOK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 4, 10))
                .register("book_block", Items.BOOK, registry);
        BONE = new PlaceableItemsBlockBuilder()
                .addComponent(new FragileBlockComponent())
                .addComponent(new ItemStackSourceBlockComponent(() -> new ItemStack(Items.BONE_MEAL, 3)))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bone_block", Items.BONE, registry);
        BOW = new PlaceableItemsBlockBuilder()
                .addComponent(new MultiModelBlockComponent(3))
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("bow_block", Items.BOW, registry);
        BREAD = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 6, 12))
                .register("bread_block", Items.BREAD, registry);
        BRICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("brick_block", Items.BRICK, registry);
        CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("carrot_block", Items.CARROT, registry);
        CARROT_ON_A_STICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("carrot_on_a_stick_block", Items.CARROT_ON_A_STICK, registry);
        CHARCOAL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 7, 12))
                .register("charcoal_block", Items.CHARCOAL, registry);
        CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("chicken_block", Items.CHICKEN, registry);
        CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(8, 7, 8))
                .register("chorus_fruit_block", Items.CHORUS_FRUIT, registry);
        CLAY_BALL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 7, 12))
                .register("clay_ball_block", Items.CLAY_BALL, registry);
        COAL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("coal_block", Items.COAL, registry);
        COOKED_BEEF =  new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("cooked_beef_block", Items.COOKED_BEEF, registry);
        COOKED_CHICKEN = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("cooked_chicken_block", Items.COOKED_CHICKEN, registry);
        COOKED_MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("cooked_mutton_block", Items.COOKED_MUTTON, registry);
        COOKED_PORKCHOP = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("cooked_porkchop_block", Items.COOKED_PORKCHOP, registry);
        COOKED_RABBIT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 7, 10))
                .register("cooked_rabbit_block", Items.COOKED_RABBIT, registry);
        COOKIE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(4))
                .build()
                .setShape(VoxelShapesUtil.create(12, 6, 12))
                .register("cookie_block", Items.COOKIE, registry);
        DIAMOND = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("diamond_block", Items.DIAMOND, registry);
        DIAMOND_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("diamond_axe_block", Items.DIAMOND_AXE, registry);
        DIAMOND_HOE =  new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("diamond_hoe_block", Items.DIAMOND_HOE, registry);
        DIAMOND_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("diamond_pickaxe_block", Items.DIAMOND_PICKAXE, registry);
        DIAMOND_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("diamond_shovel_block", Items.DIAMOND_SHOVEL, registry);
        DIAMOND_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("diamond_sword_block", Items.DIAMOND_SWORD, registry);
        DRAGON_BREATH = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(8, 12, 8))
                .register("dragon_breath_block", Items.DRAGON_BREATH, registry);
        EGG = new PlaceableItemsBlockBuilder()
                .addComponent(new FragileBlockComponent())
                .addComponent(new EntitySourceBlockComponent(0.125F, world -> {
                    Chicken chicken = EntityType.CHICKEN.create(world);
                    chicken.setAge(-24000);
                    return chicken;
                }))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("egg_block", Items.EGG, registry);
        EMERALD = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 7, 7))
                .register("emerald_block", Items.EMERALD, registry);
        ENCHANTED_BOOK = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 4, 10))
                .register("enchanted_book_block", Items.ENCHANTED_BOOK, registry);
        ENDER_EYE = new PlaceableItemsBlockBuilder()
                .addComponent(new EnderEyeBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 9, 9))
                .register("ender_eye_block", Items.ENDER_EYE, registry);
        ENDER_PEARL = new PlaceableItemsBlockBuilder()
                .addComponent(new EnderEyeBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 9, 9))
                .register("ender_pearl_block", Items.ENDER_PEARL, registry);
        EXPERIENCE_BOTTLE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("experience_bottle_block", Items.EXPERIENCE_BOTTLE, registry);
        FEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 4, 10))
                .register("feather_block", Items.FEATHER, registry);
        FIRE_CHARGE = new PlaceableItemsBlockBuilder()
                .addComponent(new FireChargeBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("fire_charge_block", Items.FIRE_CHARGE, registry);
        FIREWORK_ROCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new FireworkRocketBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 16, 10))
                .register("firework_rocket_block", Items.FIREWORK_ROCKET, registry);
        GLASS_BOTTLE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("glass_bottle_block", Items.GLASS_BOTTLE, registry);
        GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("glistering_melon_slice_block", Items.GLISTERING_MELON_SLICE, registry);
        GOLD_INGOT = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(6))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("gold_ingot_block", Items.GOLD_INGOT, registry);
        GOLDEN_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("golden_axe_block", Items.GOLDEN_AXE, registry);
        GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("golden_carrot_block", Items.GOLDEN_CARROT, registry);
        GOLDEN_HOE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("golden_hoe_block", Items.GOLDEN_HOE, registry);
        GOLDEN_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("golden_pickaxe_block", Items.GOLDEN_PICKAXE, registry);
        GOLDEN_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("golden_shovel_block", Items.GOLDEN_SHOVEL, registry);
        GOLDEN_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("golden_sword_block", Items.GOLDEN_SWORD, registry);
        HEART_OF_THE_SEA = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("heart_of_the_sea_block", Items.HEART_OF_THE_SEA, registry);
        HONEY_BOTTLE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(GLASS_BOTTLE))
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("honey_bottle_block", Items.HONEY_BOTTLE, registry);
        IRON_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("iron_axe_block", Items.IRON_AXE, registry);
        IRON_HOE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("iron_hoe_block", Items.IRON_HOE, registry);
        IRON_INGOT = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(6))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("iron_ingot_block", Items.IRON_INGOT, registry);
        IRON_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("iron_pickaxe_block", Items.IRON_PICKAXE, registry);
        IRON_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("iron_shovel_block", Items.IRON_SHOVEL, registry);
        IRON_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("iron_sword_block", Items.IRON_SWORD, registry);
        LAVA_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new FilledBucketBlockComponent())
                .addComponent(new LavaBucketBlockComponent())
                .setLightLevel(12)
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("lava_bucket_block", Items.LAVA_BUCKET, registry);
        LEATHER = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(16, 3, 16))
                .register("leather_block", Items.LEATHER, registry);
        LINGERING_POTION = new PlaceableItemsBlockBuilder()
                .addComponent(new ThrowablePotionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("lingering_potion_block", Items.LINGERING_POTION, registry);
        MAGMA_CREAM = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .setLightLevel(10)
                .build()
                .setShape(VoxelShapesUtil.create(16, 10, 16))
                .register("magma_cream_block", Items.MAGMA_CREAM, registry);
        MELON_SLICE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 10, 10))
                .register("melon_slice_block", Items.MELON_SLICE, registry);
        MILK_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new FilledBucketBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("milk_bucket_block", Items.MILK_BUCKET, registry);
        MUSHROOM_STEW = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(BOWL))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mushroom_stew_block", Items.MUSHROOM_STEW, registry);
        MUSIC_DISC = new PlaceableItemsBlockBuilder()
                .addComponent(new MusicDiscBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(13, 3, 13))
                .register("music_disc_block", registry);
        MUTTON = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("mutton_block", Items.MUTTON, registry);
        NETHER_BRICK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 4, 12))
                .register("nether_brick_block", Items.NETHER_BRICK, registry);
        NETHERITE_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("netherite_axe_block", Items.NETHERITE_AXE, registry);
        NETHERITE_HOE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("netherite_hoe_block", Items.NETHERITE_HOE, registry);
        NETHERITE_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("netherite_pickaxe_block", Items.NETHERITE_PICKAXE, registry);
        NETHERITE_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("netherite_shovel_block", Items.NETHERITE_SHOVEL, registry);
        NETHERITE_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("netherite_sword_block", Items.NETHERITE_SWORD, registry);
        NETHERITE_INGOT = new PlaceableItemsBlockBuilder()
                .addComponent(new StackableBlockComponent(6))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("netherite_ingot_block", Items.NETHERITE_INGOT, registry);
        POISONOUS_POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("poisonous_potato_block", Items.POISONOUS_POTATO, registry);
        POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 7, 8))
                .register("popped_chorus_fruit_block", Items.POPPED_CHORUS_FRUIT, registry);
        PORKCHOP = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 3, 12))
                .register("porkchop_block", Items.PORKCHOP, registry);
        POTION = new PlaceableItemsBlockBuilder()
                .addComponent(new PotionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("potion_block", Items.POTION, registry);
        POTATO = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("potato_block", Items.POTATO, registry);
        PUMPKIN_PIE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 5, 10))
                .register("pumpkin_pie_block", Items.PUMPKIN_PIE, registry);
        RABBIT = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 7, 10))
                .register("rabbit_block", Items.RABBIT, registry);
        RABBIT_STEW = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(BOWL))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("rabbit_stew_block", Items.RABBIT_STEW, registry);
        ROTTEN_FLESH = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("rotten_flesh_block", Items.ROTTEN_FLESH, registry);
        SLIMEBALL = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new SlimeBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(14, 9, 14))
                .register("slimeball_block", Items.SLIME_BALL, registry);
        SNOWBALL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 9, 12))
                .register("snowball_block", Items.SNOWBALL, registry);
        SPIDER_EYE = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(10, 6, 10))
                .register("spider_eye_block", Items.SPIDER_EYE, registry);
        SPLASH_POTION = new PlaceableItemsBlockBuilder()
                .addComponent(new ThrowablePotionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(9, 6, 9))
                .register("splash_potion_block", Items.SPLASH_POTION, registry);
        SUSPICIOUS_STEW = new PlaceableItemsBlockBuilder()
                .addComponent(new EdibleBlockComponent(BOWL))
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("suspicious_stew_block", Items.SUSPICIOUS_STEW, registry);
        STONE_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("stone_axe_block", Items.STONE_AXE, registry);
        STONE_HOE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("stone_hoe_block", Items.STONE_HOE, registry);
        STONE_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("stone_pickaxe_block", Items.STONE_PICKAXE, registry);
        STONE_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("stone_shovel_block", Items.STONE_SHOVEL, registry);
        STONE_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("stone_sword_block", Items.STONE_SWORD, registry);
        WATER_BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new FilledBucketBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("water_bucket_block", Items.WATER_BUCKET, registry);
        WHEAT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(12, 8, 12))
                .register("wheat_block", Items.WHEAT, registry);
        WOODEN_AXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("wooden_axe_block", Items.WOODEN_AXE, registry);
        WOODEN_HOE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("wooden_hoe_block", Items.WOODEN_HOE, registry);
        WOODEN_PICKAXE = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 8, 8))
                .register("wooden_pickaxe_block", Items.WOODEN_PICKAXE, registry);
        WOODEN_SHOVEL = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(8, 16, 8))
                .register("wooden_shovel_block", Items.WOODEN_SHOVEL, registry);
        WOODEN_SWORD = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .addComponent(new MultiModelBlockComponent(5))
                .build()
                .setShape(VoxelShapesUtil.create(16, 6, 16))
                .register("wooden_sword_block", Items.WOODEN_SWORD, registry);
        // TODO: Allow for writing to the book when placed?
        WRITABLE_BOOK = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("writable_book_block", Items.WRITABLE_BOOK, registry);
        WRITTEN_BOOK = new PlaceableItemsBlockBuilder()
                .addComponent(new StackHolderBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(16, 4, 16))
                .register("written_book_block", Items.WRITTEN_BOOK, registry);

        // Register at the end for reference with the other buckets
        BUCKET = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .addComponent(new EmptyBucketBlockComponent(new HashMap<Item, PlaceableItemsBlock>() {
                    {
                        put(Items.LAVA_BUCKET, PlaceableItemsBlockRegistry.LAVA_BUCKET);
                        put(Items.MILK_BUCKET, PlaceableItemsBlockRegistry.MILK_BUCKET);
                        put(Items.WATER_BUCKET, PlaceableItemsBlockRegistry.WATER_BUCKET);
                    }
                }))
                .build()
                .setShape(VoxelShapesUtil.create(12, 12, 12))
                .register("bucket_block", Items.BUCKET, registry);

        // TODO: Fix the shadow issues
        HORSE_ARMOR_STAND = new PlaceableItemsBlockBuilder()
                .addComponent(new HorseArmorStandBlockComponent())
                .build()
                .setShape(Shapes.block())
                .register("horse_armor_stand_block", registry);
        PlaceableItemsItemRegistry.blocksRegistry.remove(HORSE_ARMOR_STAND); // Skip creating the BlockItem since it's manually created elsewhere
        SADDLE_STAND = new PlaceableItemsBlockBuilder()
                .addComponent(new SaddleStandBlockComponent())
                .build()
                .setShape(Shapes.block())
                .register("saddle_stand_block", registry);
        PlaceableItemsItemRegistry.blocksRegistry.remove(SADDLE_STAND);
    }

}
