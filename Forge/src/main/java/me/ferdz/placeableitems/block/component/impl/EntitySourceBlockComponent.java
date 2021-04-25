package me.ferdz.placeableitems.block.component.impl;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import me.ferdz.placeableitems.block.component.AbstractBlockComponent;
import me.ferdz.placeableitems.wiki.WikiBlockComponentDefinition;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@WikiBlockComponentDefinition(description = "Right clicking this block will have a chance to spawn an entity")
public class EntitySourceBlockComponent extends AbstractBlockComponent {

    private final float chance;
    private final Function<World, ? extends Entity> entitySupplier;

    public EntitySourceBlockComponent(float chance, Function<World, ? extends Entity> entitySupplier) {
        Preconditions.checkArgument(entitySupplier != null, "Entity supplier must not be null");

        this.chance = chance;
        this.entitySupplier = entitySupplier;
    }

    public EntitySourceBlockComponent(Function<World, ? extends Entity> entitySupplier) {
        this(1.0F, entitySupplier);
    }

    @Override
    public boolean use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
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
