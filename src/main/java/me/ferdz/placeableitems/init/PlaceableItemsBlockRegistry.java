package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.PlaceableItems;
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
    public static PlaceableItemsBlock BEEF;
    public static PlaceableItemsBlock BEEF_COOKED;
    public static PlaceableItemsBlock BEETROOT;
    public static PlaceableItemsBlock BEETROOT_SEEDS;
    public static PlaceableItemsBlock BEETROOT_SOUP;
    public static PlaceableItemsBlock BLAZE_POWDER;
    public static PlaceableItemsBlock BOOK;
    public static PlaceableItemsBlock BOWL;
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
                .setShape(VoxelShapesUtil.create(5, 9, 5))
                .register("apple_block", Items.APPLE);
        // TODO: Make edible
        BEEF = new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(6, 3, 6))
                .register("beef_block", Items.BEEF);
        // TODO: Make edible
        BEEF_COOKED =  new PlaceableItemsBlockBuilder()
                .addComponent(new BiPositionBlockComponent())
                .build()
                .setShape(VoxelShapesUtil.create(6, 3, 6))
                .register("beef_cooked_block", Items.COOKED_BEEF);
        // TODO: Make edible
        BEETROOT = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(5, 6, 5))
                .register("beetroot_block", Items.BEETROOT);
        BEETROOT_SEEDS =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(5, 2, 5))
                .register("beetroot_seeds_block", Items.BEETROOT_SEEDS);
        // TODO: Make edible
        BEETROOT_SOUP =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 4, 7))
                .register("beetroot_soup_block", Items.BEETROOT_SOUP);
        // TODO: Add animated particles
        BLAZE_POWDER =  new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 6, 7))
                .register("blaze_powder_block", Items.BLAZE_POWDER);
        BOOK = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(7, 6, 7))
                .register("book_block", Items.BOOK);
        BOWL = new PlaceableItemsBlockBuilder()
                .build()
                .setShape(VoxelShapesUtil.create(8, 4, 8))
                .register("bowl_block", Items.BOWL);
        


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
