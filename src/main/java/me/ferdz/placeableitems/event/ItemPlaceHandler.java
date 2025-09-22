package me.ferdz.placeableitems.event;

import me.ferdz.placeableitems.block.PlaceableItemsBlock;
import me.ferdz.placeableitems.init.PlaceableItemsMap;
import me.ferdz.placeableitems.network.ServerKeyState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ItemPlaceHandler {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (!ServerKeyState.isHolding(player.getUUID())) {
            return;
        }

        // Only players in survival or creative are allowed to place blocks
        if (player instanceof ServerPlayer serverPlayer) {
            GameType gameMode = serverPlayer.gameMode.getGameModeForPlayer();
            if (gameMode != GameType.SURVIVAL && gameMode != GameType.CREATIVE) {
                return;
            }
        }

        ItemStack itemStack = event.getItemStack();
        PlaceableItemsBlock block = PlaceableItemsMap.instance().get(itemStack.getItem());
        // Check if item used has a block associated
        if (block == null) {
            return;
        }

        // TODO: Validate that this is better than creating a BlockItem for all blocks
        BlockPos placePos = event.getPos().relative(event.getFace());
        BlockPlaceContext placeContext = new BlockPlaceContext(
                new UseOnContext(player, event.getHand(),
                        new BlockHitResult(player.getLookAngle(), event.getFace(), event.getPos(), false)));
        // Validate that the placement is valid
        if (!placeContext.canPlace()) {
            return;
        }

        BlockState blockState = block.getStateForPlacement(placeContext);
        // Shouldn't happen
        if (blockState == null) {
            return;
        }

        Level level = event.getLevel();
        // Validate that we're not overlapping entities
        if (!level.isUnobstructed(blockState, placePos, CollisionContext.of(player))) {
            return;
        }
        // Grief prevention?
        if (!level.mayInteract(player, placePos)) {
            return;
        }

        event.getLevel().setBlock(placePos, blockState, Block.UPDATE_ALL);
        // Triggers the pipeline for stack NBT saving
        block.setPlacedBy(event.getLevel(), placePos, blockState, player, itemStack);
        if (!player.isCreative()) {
            itemStack.shrink(1);
        }
        event.setCancellationResult(InteractionResult.SUCCESS);
        event.setCanceled(true);
    }
}
