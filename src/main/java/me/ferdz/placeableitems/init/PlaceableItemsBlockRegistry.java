package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.block.*;
import me.ferdz.placeableitems.block.component.impl.*;
import me.ferdz.placeableitems.block.impl.HorseArmorStandBlock;
import me.ferdz.placeableitems.block.impl.SaddleStandBlock;
import me.ferdz.placeableitems.utils.VoxelShapesUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class PlaceableItemsBlockRegistry {
    // Create a Deferred Register to hold Blocks which will all be registered under the "placeableitems" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PlaceableItems.MODID);

    public static final List<String> ALL_PLACEABLE_ITEM_IDS = new ArrayList<>();

    // Define BOWL first because it's used later
    public static final DeferredBlock<PlaceableItemsBlock> BOWL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("bowl", Items.BOWL);

    public static final DeferredBlock<PlaceableItemsBlock> APPLE = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(7, 8, 7))
            .register("apple", Items.APPLE);
    public static final DeferredBlock<PlaceableItemsBlock> BAKED_POTATO = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("baked_potato", Items.BAKED_POTATO);
    public static final DeferredBlock<PlaceableItemsBlock> BEEF = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 3, 12))
            .register("beef", Items.BEEF);
    public static final DeferredBlock<PlaceableItemsBlock> BEETROOT = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 6, 10))
            .register("beetroot", Items.BEETROOT);
    public static final DeferredBlock<PlaceableItemsBlock> BEETROOT_SOUP = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent(BOWL))
            .setShape(VoxelShapesUtil.create(14, 4, 14))
            .register("beetroot_soup", Items.BEETROOT_SOUP);
    public static final DeferredBlock<PlaceableItemsBlock> BEETROOT_SEEDS = PlaceableItemsBlockBuilder.of()
            .setShape(VoxelShapesUtil.create(10, 2, 10))
            .register("beetroot_seeds", Items.BEETROOT_SEEDS);
    public static final DeferredBlock<PlaceableItemsBlock> BLAZE_POWDER = new PlaceableItemsBlockBuilder()
            .addComponent(new BlazePowderBlockComponent())
            .setLightLevel(10)
            .setShape(VoxelShapesUtil.create(14, 6, 14))
            .register("blaze_powder", Items.BLAZE_POWDER);
    public static final DeferredBlock<PlaceableItemsBlock> BOOK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 4, 10))
            .register("book", Items.BOOK);
    public static final DeferredBlock<PlaceableItemsBlock> BONE = new PlaceableItemsBlockBuilder()
            .addComponent(new FragileBlockComponent())
            .addComponent(new ItemStackSourceBlockComponent(() -> new ItemStack(Items.BONE_MEAL, 3)))
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("bone", Items.BONE);
    public static final DeferredBlock<PlaceableItemsBlock> BOW = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(3))
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("bow", Items.BOW);
    public static final DeferredBlock<PlaceableItemsBlock> BREAD = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 6, 12))
            .register("bread", Items.BREAD);
    public static final DeferredBlock<PlaceableItemsBlock> BRICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("brick", Items.BRICK);
    public static final DeferredBlock<PlaceableItemsBlock> CARROT = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .addComponent(new BiPositionBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("carrot", Items.CARROT);
    public static final DeferredBlock<PlaceableItemsBlock> CARROT_ON_A_STICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("carrot_on_a_stick", Items.CARROT_ON_A_STICK);
    public static final DeferredBlock<PlaceableItemsBlock> CHARCOAL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 7, 12))
            .register("charcoal", Items.CHARCOAL);
    public static final DeferredBlock<PlaceableItemsBlock> CHICKEN = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 8, 12))
            .register("chicken", Items.CHICKEN);
    public static final DeferredBlock<PlaceableItemsBlock> CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(8, 7, 8))
            .register("chorus_fruit", Items.CHORUS_FRUIT);
    public static final DeferredBlock<PlaceableItemsBlock> CLAY_BALL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 7, 12))
            .register("clay_ball", Items.CLAY_BALL);
    public static final DeferredBlock<PlaceableItemsBlock> COAL = PlaceableItemsBlockBuilder.of()
            .setShape(VoxelShapesUtil.create(10, 6, 10))
            .register("coal", Items.COAL);
    public static final DeferredBlock<PlaceableItemsBlock> COOKED_BEEF = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 3, 12))
            .register("cooked_beef", Items.COOKED_BEEF);
    public static final DeferredBlock<PlaceableItemsBlock> COOKED_CHICKEN = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 8, 12))
            .register("cooked_chicken", Items.COOKED_CHICKEN);
    public static final DeferredBlock<PlaceableItemsBlock> COOKED_MUTTON = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("cooked_mutton", Items.COOKED_MUTTON);
    public static final DeferredBlock<PlaceableItemsBlock> COOKED_PORKCHOP = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 3, 12))
            .register("cooked_porkchop", Items.COOKED_PORKCHOP);
    public static final DeferredBlock<PlaceableItemsBlock> COOKED_RABBIT = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 7, 10))
            .register("cooked_rabbit", Items.COOKED_RABBIT);
    public static final DeferredBlock<PlaceableItemsBlock> COOKIE = new PlaceableItemsBlockBuilder()
            .addComponent(new StackableBlockComponent(4))
            .setShape(VoxelShapesUtil.create(12, 6, 12))
            .register("cookie", Items.COOKIE);
    public static final DeferredBlock<PlaceableItemsBlock> COPPER_INGOT = new PlaceableItemsBlockBuilder()
            .addComponent(new StackableBlockComponent(6))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("copper_ingot", Items.COPPER_INGOT);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(7, 7, 7))
            .register("diamond", Items.DIAMOND);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("diamond_axe", Items.DIAMOND_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("diamond_hoe", Items.DIAMOND_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("diamond_pickaxe", Items.DIAMOND_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("diamond_shovel", Items.DIAMOND_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> DIAMOND_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("diamond_sword", Items.DIAMOND_SWORD);
    public static final DeferredBlock<PlaceableItemsBlock> DRAGON_BREATH = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(8, 12, 8))
            .register("dragon_breath", Items.DRAGON_BREATH);
    public static final DeferredBlock<PlaceableItemsBlock> EGG = new PlaceableItemsBlockBuilder()
            .addComponent(new FragileBlockComponent())
            .addComponent(new EntitySourceBlockComponent(0.125F, world -> {
                Chicken chicken = EntityType.CHICKEN.create(world);
                chicken.setAge(-24000);
                return chicken;
            }))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("egg", Items.EGG);
    public static final DeferredBlock<PlaceableItemsBlock> EMERALD = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(7, 7, 7))
            .register("emerald", Items.EMERALD);
    public static final DeferredBlock<PlaceableItemsBlock> ENCHANTED_BOOK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 4, 10))
            .register("enchanted_book", Items.ENCHANTED_BOOK);
    public static final DeferredBlock<PlaceableItemsBlock> ENDER_EYE = new PlaceableItemsBlockBuilder()
            .addComponent(new EnderEyeBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 9, 9))
            .register("ender_eye", Items.ENDER_EYE);
    public static final DeferredBlock<PlaceableItemsBlock> ENDER_PEARL = new PlaceableItemsBlockBuilder()
            .addComponent(new EnderEyeBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 9, 9))
            .register("ender_pearl", Items.ENDER_PEARL);
    public static final DeferredBlock<PlaceableItemsBlock> EXPERIENCE_BOTTLE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("experience_bottle", Items.EXPERIENCE_BOTTLE);
    public static final DeferredBlock<PlaceableItemsBlock> FEATHER = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 4, 10))
            .register("feather", Items.FEATHER);
    public static final DeferredBlock<PlaceableItemsBlock> FIRE_CHARGE = new PlaceableItemsBlockBuilder()
            .addComponent(new FireChargeBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 10, 10))
            .register("fire_charge", Items.FIRE_CHARGE);
    public static final DeferredBlock<PlaceableItemsBlock> FIREWORK_ROCKET = new PlaceableItemsBlockBuilder()
            .addComponent(new FireworkRocketBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 16, 10))
            .register("firework_rocket", Items.FIREWORK_ROCKET);
    public static final DeferredBlock<PlaceableItemsBlock> GLASS_BOTTLE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("glass_bottle", Items.GLASS_BOTTLE);
    public static final DeferredBlock<PlaceableItemsBlock> GLISTERING_MELON_SLICE = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(10, 10, 10))
            .register("glistering_melon_slice", Items.GLISTERING_MELON_SLICE);
    public static final DeferredBlock<PlaceableItemsBlock> GOLD_INGOT = new PlaceableItemsBlockBuilder()
            .addComponent(new StackableBlockComponent(6))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("gold_ingo", Items.GOLD_INGOT);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("golden_axe", Items.GOLDEN_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_CARROT = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("golden_carrot", Items.GOLDEN_CARROT);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("golden_hoe", Items.GOLDEN_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("golden_pickaxe", Items.GOLDEN_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("golden_shovel", Items.GOLDEN_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> GOLDEN_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("golden_sword", Items.GOLDEN_SWORD);
    public static final DeferredBlock<PlaceableItemsBlock> HEART_OF_THE_SEA = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("heart_of_the_sea", Items.HEART_OF_THE_SEA);
    public static final DeferredBlock<PlaceableItemsBlock> HONEY_BOTTLE = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent(GLASS_BOTTLE))
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("honey_bottle", Items.HONEY_BOTTLE);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
                .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("iron_axe", Items.IRON_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("iron_hoe", Items.IRON_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_INGOT = new PlaceableItemsBlockBuilder()
            .addComponent(new StackableBlockComponent(6))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("iron_ingot", Items.IRON_INGOT);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("iron_pickaxe", Items.IRON_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("iron_shovel", Items.IRON_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> IRON_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("iron_sword", Items.IRON_SWORD);
    public static final DeferredBlock<PlaceableItemsBlock> LAVA_BUCKET = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new FilledBucketBlockComponent())
            .addComponent(new LavaBucketBlockComponent())
            .setLightLevel(12)
            .setShape(VoxelShapesUtil.create(12, 12, 12))
            .register("lava_bucket", Items.LAVA_BUCKET);
    public static final DeferredBlock<PlaceableItemsBlock> LINGERING_POTION = new PlaceableItemsBlockBuilder()
            .addComponent(new ThrowablePotionBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("lingering_potion", Items.LINGERING_POTION);
    public static final DeferredBlock<PlaceableItemsBlock> LEATHER = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 3, 16))
            .register("leather", Items.LEATHER);
    public static final DeferredBlock<PlaceableItemsBlock> MAGMA_CREAM = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .setLightLevel(10)
            .setShape(VoxelShapesUtil.create(16, 10, 16))
            .register("magma_cream", Items.MAGMA_CREAM);
    public static final DeferredBlock<PlaceableItemsBlock> MELON_SLICE = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 10, 10))
            .register("melon_slice", Items.MELON_SLICE);
    public static final DeferredBlock<PlaceableItemsBlock> MILK_BUCKET = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new FilledBucketBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 12, 12))
            .register("milk_bucket", Items.MILK_BUCKET);
    public static final DeferredBlock<PlaceableItemsBlock> MUSHROOM_STEW = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent(BOWL))
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("mushroom_stew", Items.MUSHROOM_STEW);
    public static final DeferredBlock<PlaceableItemsBlock> MUSIC_DISC = new PlaceableItemsBlockBuilder()
            .addComponent(new MusicDiscBlockComponent())
            .setShape(VoxelShapesUtil.create(13, 3, 13))
            .register(
                    "music_disc",
                    Arrays.stream(MusicDiscBlockComponent.MusicDiscType.values())
                            .map(MusicDiscBlockComponent.MusicDiscType::getItem)
                            .toArray(Item[]::new)
            );
    public static final DeferredBlock<PlaceableItemsBlock> MUTTON = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("mutton", Items.MUTTON);
    public static final DeferredBlock<PlaceableItemsBlock> NETHER_BRICK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 4, 12))
            .register("nether_brick", Items.NETHER_BRICK);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("netherite_axe", Items.NETHERITE_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("netherite_hoe", Items.NETHERITE_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("netherite_pickaxe", Items.NETHERITE_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("netherite_shovel", Items.NETHERITE_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("netherite_sword", Items.NETHERITE_SWORD);
    public static final DeferredBlock<PlaceableItemsBlock> NETHERITE_INGOT = new PlaceableItemsBlockBuilder()
            .addComponent(new StackableBlockComponent(6))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("netherite_ingot", Items.NETHERITE_INGOT);
    public static final DeferredBlock<PlaceableItemsBlock> POISONOUS_POTATO = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("poisonous_potato", Items.POISONOUS_POTATO);
    public static final DeferredBlock<PlaceableItemsBlock> POPPED_CHORUS_FRUIT = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(8, 7, 8))
            .register("popped_chorus_fruit", Items.POPPED_CHORUS_FRUIT);
    public static final DeferredBlock<PlaceableItemsBlock> PORKCHOP = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 3, 12))
            .register("porkchop", Items.PORKCHOP);
    public static final DeferredBlock<PlaceableItemsBlock> POTION = new PlaceableItemsBlockBuilder()
            .addComponent(new PotionBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("potion", Items.POTION);
    public static final DeferredBlock<PlaceableItemsBlock> POTATO = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("potato", Items.POTATO);
    public static final DeferredBlock<PlaceableItemsBlock> PUMPKIN_PIE = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 5, 10))
            .register("pumpkin_pie", Items.PUMPKIN_PIE);
    public static final DeferredBlock<PlaceableItemsBlock> RABBIT = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .addComponent(new BiPositionBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 7, 10))
            .register("rabbit", Items.RABBIT);
    public static final DeferredBlock<PlaceableItemsBlock> RABBIT_STEW = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent(BOWL))
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("rabbit_stew", Items.RABBIT_STEW);
    public static final DeferredBlock<PlaceableItemsBlock> ROTTEN_FLESH = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("rotten_flesh", Items.ROTTEN_FLESH);
    public static final DeferredBlock<PlaceableItemsBlock> SLIMEBALL = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new SlimeBlockComponent())
            .setShape(VoxelShapesUtil.create(14, 9, 14))
            .register("slime_ball", Items.SLIME_BALL);
    public static final DeferredBlock<PlaceableItemsBlock> SNOWBALL = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 9, 12))
            .register("snowball", Items.SNOWBALL);
    public static final DeferredBlock<PlaceableItemsBlock> SPIDER_EYE = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent())
            .setShape(VoxelShapesUtil.create(10, 6, 10))
            .register("spider_eye", Items.SPIDER_EYE);
    public static final DeferredBlock<PlaceableItemsBlock> SPLASH_POTION = new PlaceableItemsBlockBuilder()
            .addComponent(new ThrowablePotionBlockComponent())
            .setShape(VoxelShapesUtil.create(9, 6, 9))
            .register("splash_potion", Items.SPLASH_POTION);
    public static final DeferredBlock<PlaceableItemsBlock> SUSPICIOUS_STEW = new PlaceableItemsBlockBuilder()
            .addComponent(new EdibleBlockComponent(BOWL))
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("suspicious_stew", Items.SUSPICIOUS_STEW);
    public static final DeferredBlock<PlaceableItemsBlock> STONE_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("stone_axe", Items.STONE_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> STONE_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("stone_hoe", Items.STONE_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> STONE_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("stone_pickaxe", Items.STONE_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> STONE_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("stone_shovel", Items.STONE_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> STONE_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("stone_sword", Items.STONE_SWORD);
    public static final DeferredBlock<PlaceableItemsBlock> WATER_BUCKET = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new FilledBucketBlockComponent())
            .setShape(VoxelShapesUtil.create(12, 12, 12))
            .register("water_bucket", Items.WATER_BUCKET);
    public static final DeferredBlock<PlaceableItemsBlock> WHEAT = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(12, 8, 12))
            .register("wheat", Items.WHEAT);
    public static final DeferredBlock<PlaceableItemsBlock> WOODEN_AXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("wooden_axe", Items.WOODEN_AXE);
    public static final DeferredBlock<PlaceableItemsBlock> WOODEN_HOE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("wooden_hoe", Items.WOODEN_HOE);
    public static final DeferredBlock<PlaceableItemsBlock> WOODEN_PICKAXE = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 8, 8))
            .register("wooden_pickaxe", Items.WOODEN_PICKAXE);
    public static final DeferredBlock<PlaceableItemsBlock> WOODEN_SHOVEL = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(8, 3, 8))
            .register("wooden_shovel", Items.WOODEN_SHOVEL);
    public static final DeferredBlock<PlaceableItemsBlock> WOODEN_SWORD = new PlaceableItemsBlockBuilder()
            .addComponent(new MultiModelBlockComponent(5))
            .setShape(VoxelShapesUtil.create(16, 6, 16))
            .register("wooden_sword", Items.WOODEN_SWORD);
    // TODO: Allow for writing to the book when placed?
    public static final DeferredBlock<PlaceableItemsBlock> WRITABLE_BOOK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("writable_boo", Items.WRITABLE_BOOK);
    public static final DeferredBlock<PlaceableItemsBlock> WRITTEN_BOOK = new PlaceableItemsBlockBuilder()
            .setShape(VoxelShapesUtil.create(16, 4, 16))
            .register("written_book", Items.WRITTEN_BOOK);

    // Register at the end for reference with the other buckets
    public static final DeferredBlock<PlaceableItemsBlock> BUCKET = new PlaceableItemsBlockBuilder()
            .addComponent(new BiPositionBlockComponent())
            .addComponent(new EmptyBucketBlockComponent(new HashMap() {
                {
                    put(Items.LAVA_BUCKET, PlaceableItemsBlockRegistry.LAVA_BUCKET);
                    put(Items.MILK_BUCKET, PlaceableItemsBlockRegistry.MILK_BUCKET);
                    put(Items.WATER_BUCKET, PlaceableItemsBlockRegistry.WATER_BUCKET);
                }
            }))
            .setShape(VoxelShapesUtil.create(12, 12, 12))
            .register("bucket", Items.BUCKET);

    public static final DeferredBlock<RotationBlock> HORSE_ARMOR_STAND = BLOCKS.register("horse_armor_stand",
            () -> {
                return new HorseArmorStandBlock(
                        BlockBehaviour.Properties
                                .of() // starts with a blank slate
                                .noOcclusion()
                                .isViewBlocking((state, worlds, pos) -> false)
                                .isSuffocating((state, world, pos) -> false)
                                .isRedstoneConductor((state, world, pos) -> false)
                );
            });

    public static final DeferredBlock<RotationBlock> SADDLE_STAND = BLOCKS.register("saddle_stand",
            () -> {
                return new SaddleStandBlock(
                        BlockBehaviour.Properties
                                .of() // starts with a blank slate
                                .noOcclusion()
                                .isViewBlocking((state, worlds, pos) -> false)
                                .isSuffocating((state, world, pos) -> false)
                                .isRedstoneConductor((state, world, pos) -> false)
                );
            });
}
