package me.ferdz.placeableitems.init;

import java.util.HashMap;

import me.ferdz.placeableitems.block.BlockApple;
import me.ferdz.placeableitems.block.BlockAppleGolden;
import me.ferdz.placeableitems.block.BlockArrow;
import me.ferdz.placeableitems.block.BlockBeetrootSoup;
import me.ferdz.placeableitems.block.BlockBiEdible;
import me.ferdz.placeableitems.block.BlockBookAndQuill;
import me.ferdz.placeableitems.block.BlockCarrot;
import me.ferdz.placeableitems.block.BlockEdible;
import me.ferdz.placeableitems.block.BlockEmptyBucket;
import me.ferdz.placeableitems.block.BlockFilledBucket;
import me.ferdz.placeableitems.block.BlockPlaceableItems;
import me.ferdz.placeableitems.block.BlockPotatoPoisoned;
import me.ferdz.placeableitems.block.BlockPotion;
import me.ferdz.placeableitems.block.BlockSalmon;
import me.ferdz.placeableitems.block.BlockTool;
import me.ferdz.placeableitems.tileentity.TEArrow;
import me.ferdz.placeableitems.tileentity.TEBook;
import me.ferdz.placeableitems.tileentity.TEEdible;
import me.ferdz.placeableitems.tileentity.TEPotion;
import me.ferdz.placeableitems.tileentity.TETool;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static HashMap<Item, BlockPlaceableItems> blockMap;
	
	public static Block blockLavaBucket, blockWaterBucket, blockMilkBucket, blockEmptyBucket;
	public static Block blockBook;
	public static Block blockBowl;
	public static Block blockBread;
	public static Block blockCarrot;
	public static Block blockApple, blockGoldenApple;
	public static Block blockArrow;
	public static Block blockPotato, blockPoisonedPotato;
	public static Block blockBeetroot, blockBeetrootSoup, blockBeetrootSeeds;
	public static Block blockBookAndQuill;
	public static Block blockBone;
	public static BlockPlaceableItems blockFish, blockSalmon;
	public static Block blockBottleEmpty, blockPotion, blockExperienceBottle;
	public static Block blockBow;
	public static Block blockCarrotOnStick;
	public static Block blockCookie;
	public static Block blockEgg;
	
	
	public static void init() {
		blockMap = new HashMap<Item, BlockPlaceableItems>();
		
		blockLavaBucket = new BlockFilledBucket("block_lava_bucket").setBucketItem(Items.LAVA_BUCKET).setItem(Items.LAVA_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockWaterBucket = new BlockFilledBucket("block_water_bucket").setBucketItem(Items.WATER_BUCKET).setItem(Items.WATER_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockMilkBucket = new BlockFilledBucket("block_milk_bucket").setBucketItem(Items.MILK_BUCKET).setItem(Items.MILK_BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockEmptyBucket = new BlockEmptyBucket("block_empty_bucket").setItem(Items.BUCKET).setBoundingBox(1, 0, 1, 15, 14, 15);
		blockBook = new BlockPlaceableItems("block_book").setItem(Items.BOOK).setBoundingBox(2, 0, 2, 14, 3, 14);
		blockBowl = new BlockPlaceableItems("block_bowl").setItem(Items.BOWL).setBoundingBox(4, 0, 4, 12, 3, 12);
		blockBread = new BlockEdible("block_bread", 5, 1.2F).setItem(Items.BREAD).setBoundingBox(2, 0, 2, 14, 5, 14);
		blockCarrot = new BlockCarrot("block_carrot").setItem(Items.CARROT).setItem(Items.GOLDEN_CARROT).setBoundingBox(2, 0, 2, 14, 5, 14);
		blockApple = new BlockApple("block_apple", 4, 0.6F).setItem(Items.APPLE);
		blockGoldenApple = new BlockAppleGolden("block_apple_golden", 4, 2.4F).setItem(Items.GOLDEN_APPLE);
		blockArrow = new BlockArrow("block_arrow").setItem(Items.ARROW).setItem(Items.TIPPED_ARROW).setItem(Items.SPECTRAL_ARROW).setBoundingBox(1, 0, 1, 15, 4, 15);
		blockPotato = new BlockBiEdible("block_potato", Items.POTATO, 1, 0.6F, Items.BAKED_POTATO, 5, 1.44F).setItem(Items.POTATO).setItem(Items.BAKED_POTATO).setBoundingBox(3, 0, 3, 13, 7, 13);
		blockPoisonedPotato = new BlockPotatoPoisoned("block_potato_poisoned", 2, 0.6F).setItem(Items.POISONOUS_POTATO).setBoundingBox(3, 0, 3, 13, 7, 13);
		blockBeetroot = new BlockEdible("block_beetroot", 1, 1.2F).setItem(Items.BEETROOT).setBoundingBox(3, 0, 3, 13, 5, 13);
		blockBeetrootSoup = new BlockBeetrootSoup("block_beetroot_soup", 6, 1.2F).setItem(Items.BEETROOT_SOUP).setBoundingBox(2, 0, 2, 14, 3, 14);
		blockBeetrootSeeds = new BlockPlaceableItems("block_beetroot_seeds").setItem(Items.BEETROOT_SEEDS).setBoundingBox(2, 0, 2, 14, 3, 14);
		blockBookAndQuill = new BlockBookAndQuill("block_book_and_quill").setItem(Items.WRITABLE_BOOK).setBoundingBox(0, 0, 0, 16, 5, 16);
		blockBone = new BlockPlaceableItems("block_bone").setItem(Items.BONE).setBoundingBox(0, 0, 0, 16, 3, 16);
		blockFish = new BlockBiEdible("block_fish", Items.FISH, 2, 0.2F, Items.COOKED_FISH, 5, 1.2F).setItem(Items.FISH).setItem(Items.COOKED_FISH).setBoundingBox(0, 0, 0, 16, 3, 16);
		blockSalmon = new BlockSalmon("block_salmon", Items.FISH, 2, 0.2F, Items.COOKED_FISH, 6, 1.6F).setItem(Items.FISH).setItem(Items.COOKED_FISH).setBoundingBox(0, 0, 0, 16, 3, 16);
		blockBottleEmpty = new BlockPlaceableItems("block_bottle_empty").setItem(Items.GLASS_BOTTLE).setBoundingBox(4, 0, 4, 12, 10, 12);
		blockPotion = new BlockPotion("block_potion").setItem(Items.POTIONITEM).setBoundingBox(4, 0, 4, 12, 10, 12);
		blockExperienceBottle = new BlockPlaceableItems("block_bottle_experience").setItem(Items.EXPERIENCE_BOTTLE).setBoundingBox(4, 0, 4, 12, 10, 12);
		blockBow = new BlockTool("block_bow").setItem(Items.BOW).setBoundingBox(0, 0, 0, 16, 4, 16);
		blockCarrotOnStick = new BlockTool("block_carrot_on_stick").setItem(Items.CARROT_ON_A_STICK).setBoundingBox(0, 0, 0, 16, 4, 16);
		blockCookie = new BlockEdible("block_cookie", 2, 0.2F).setItem(Items.COOKIE).setBoundingBox(3, 0, 3, 13, 4, 13);
		blockEgg = new BlockPlaceableItems("block_egg").setItem(Items.EGG).setBoundingBox(3, 0, 3, 13, 7, 13);
		
		
		GameRegistry.registerTileEntity(TEArrow.class, "te_arrow");
		GameRegistry.registerTileEntity(TEEdible.class, "te_edible");
		GameRegistry.registerTileEntity(TEBook.class, "te_book");
		GameRegistry.registerTileEntity(TEPotion.class, "te_potion");
		GameRegistry.registerTileEntity(TETool.class, "te_tool");
	}
}
