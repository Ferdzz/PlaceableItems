package me.ferdz.placeableitems.init;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;

// TODO: Make this class streamlined for registration

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlaceableItemsBlockRegistry {

    public static PlaceableItemsBlock BONE;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        BONE = new PlaceableItemsBlock().register("bone_block", Items.BONE, Items.APPLE);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        // Keep this for debugging purposes to use an ItemBlock
        // event.getRegistry().register(new BlockItem(BONE, new Item.Properties().group(ItemGroup.COMBAT)).setRegistryName(BONE.getRegistryName()));
    }
}
