package me.ferdz.placeableitems.utils;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class VoxelShapesUtil {

    /**
     * Creates a VoxelShape with pixel dimensions (dimensions are divided by 16)
     */
    public static VoxelShape create(double x1, double y1, double z1, double x2, double y2, double z2) {
        return VoxelShapes.box(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
    }

    public static VoxelShape create(double width, double height, double depth) {
        double halfBlock = 16 / 2;
        double halfWidth = width / 2;
        double halfDepth = depth / 2;
        return VoxelShapesUtil.create(
                halfBlock - halfWidth, 0, halfBlock - halfDepth,
                halfBlock + halfWidth, height, halfBlock + halfDepth);
    }
}
