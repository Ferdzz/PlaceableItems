package me.ferdz.placeableitems.init;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import me.ferdz.placeableitems.PlaceableItems;
import me.ferdz.placeableitems.tileentity.StackHolderBlockEntity;
import me.ferdz.placeableitems.tileentity.SyncedStackHolderBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Collection;
import java.util.Stack;
import java.util.function.Supplier;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class PlaceableItemsTileEntityTypeRegistry {

    private static final Multimap<Class<? extends BlockEntity>, Block> VALID_BLOCKS = MultimapBuilder.hashKeys().arrayListValues().build();
    public static BlockEntityType<StackHolderBlockEntity> STACK_HOLDER;
    public static BlockEntityType<SyncedStackHolderBlockEntity> SYNCED_STACK_HOLDER;

    private PlaceableItemsTileEntityTypeRegistry() { }

    @SubscribeEvent
    public static void registerTE(RegistryEvent.Register<BlockEntityType<?>> e) {
        // TODO: Cleanup the type casting here
        STACK_HOLDER = (BlockEntityType<StackHolderBlockEntity>) BlockEntityType.Builder.of(
                StackHolderBlockEntity::new,
                VALID_BLOCKS.removeAll(StackHolderBlockEntity.class)
                        .toArray(new Block[VALID_BLOCKS.size()]))
                .build(null)
                .setRegistryName(PlaceableItems.MODID, "stack_holder_entity");
        SYNCED_STACK_HOLDER = (BlockEntityType<SyncedStackHolderBlockEntity>) BlockEntityType.Builder.of(
                        SyncedStackHolderBlockEntity::new,
                        VALID_BLOCKS.removeAll(SyncedStackHolderBlockEntity.class)
                                .toArray(new Block[VALID_BLOCKS.size()]))
                .build(null)
                .setRegistryName(PlaceableItems.MODID, "synced_stack_holder_entity");
        e.getRegistry().registerAll(STACK_HOLDER, SYNCED_STACK_HOLDER);
    }

    public static void assignTo(Class<? extends BlockEntity> tileEntity, Block block) {
        VALID_BLOCKS.put(tileEntity, block);
    }
}
