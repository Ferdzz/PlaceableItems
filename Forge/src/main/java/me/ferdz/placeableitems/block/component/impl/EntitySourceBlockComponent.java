package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;

@WikiBlockComponentDefinition(description = "Right clicking this block will have a chance to spawn an entity")
public class EntitySourceBlockComponent extends AbstractBlockComponent {

    private final float chance;
    private final Function<Level, ? extends Entity> entitySupplier;

    public EntitySourceBlockComponent(float chance, Function<Level, ? extends Entity> entitySupplier) {
        Preconditions.checkArgument(entitySupplier != null, "Entity supplier must not be null");

        this.chance = chance;
        this.entitySupplier = entitySupplier;
    }

    public EntitySourceBlockComponent(Function<Level, ? extends Entity> entitySupplier) {
        this(1.0F, entitySupplier);
    }

    @Override
    public boolean use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide) {
            return true;
        }

        if (worldIn.random.nextFloat() < chance) {
            Entity entity = entitySupplier.apply(worldIn);
            if (entity != null) {
                entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                worldIn.addFreshEntity(entity);
            }
        }

        return true;
    }
}
