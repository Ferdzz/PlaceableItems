package me.ferdz.placeableitems.init;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.tileentity.FluidHolderTileEntity;
import me.ferdz.placeableitems.tileentity.StackHolderTileEntity;
import me.ferdz.placeableitems.tileentity.SyncedStackHolderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collection;
import java.util.function.Supplier;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsTileEntityTypeRegistry {

    private static final Multimap<Class<? extends TileEntity>, Block> VALID_BLOCKS = MultimapBuilder.hashKeys().arrayListValues().build();

    private PlaceableItemsTileEntityTypeRegistry() { }

    @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<TileEntityType<?>> e) {
        e.getRegistry().registerAll(
                registerWithBlocks(StackHolderTileEntity::new, StackHolderTileEntity.class).setRegistryName(PlaceableItems.MODID, "writable_book_block"),
                registerWithBlocks(SyncedStackHolderTileEntity::new, SyncedStackHolderTileEntity.class).setRegistryName(PlaceableItems.MODID, "placeableitems_synced_stackholder"),
                registerWithBlocks(FluidHolderTileEntity::new, FluidHolderTileEntity.class).setRegistryName(PlaceableItems.MODID, "fluid_holder")
        );
    }

    public static void assignTo(Class<? extends TileEntity> tileEntity, Block block) {
        VALID_BLOCKS.put(tileEntity, block);
    }

    private static <T extends TileEntity> TileEntityType<T> registerWithBlocks(Supplier<T> factory, Class<T> tileClass) {
        Collection<Block> validBlocks = VALID_BLOCKS.removeAll(tileClass); // removeAll() because we won't need these values again
        TileEntityType<T> type = TileEntityType.Builder.of(factory, validBlocks.toArray(new Block[validBlocks.size()])).build(null);
        return type;
    }

}
