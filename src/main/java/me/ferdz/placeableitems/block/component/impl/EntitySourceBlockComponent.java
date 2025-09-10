package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class EntitySourceBlockComponent extends AbstractBlockComponent {

    private final float chance;
    private final Function<Level, ? extends Entity> entitySupplier;

    public EntitySourceBlockComponent(float chance, Function<Level, ? extends Entity> entitySupplier) {
        Preconditions.checkArgument(entitySupplier != null, "Entity supplier must not be null");

        this.chance = chance;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) throws NotImplementedException {
        if (level.isClientSide) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (level.random.nextFloat() < chance) {
            Entity entity = entitySupplier.apply(level);
            if (entity != null) {
                entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                level.addFreshEntity(entity);
            }
        }

        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }
}
