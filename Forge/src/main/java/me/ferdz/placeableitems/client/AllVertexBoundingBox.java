package me.ferdz.placeableitems.client;

import net.minecraft.client.renderer.Vector3d;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Represents a box defined by 8 points in the world. Unlike {@link AxisAlignedBB}s, points
 * are not validated. Therefore, caution must be taken when creating instances of this box
 * as some points may not be properly named according to this class' member methods. The
 * methods are named according to their position in the world relative to the player when facing
 * the bounding box.
 *
 * @author Parker Hawke - Choco
 */
public class AllVertexBoundingBox {

    private final Vector3d frontBottomLeft, frontBottomRight;
    private final Vector3d frontTopLeft, frontTopRight;

    private final Vector3d backBottomLeft, backBottomRight;
    private final Vector3d backTopLeft, backTopRight;

    /**
     * Create an AllVertexBoundingBox with all 8 vertexes. It is recommended that a builder be
     * used instead. See {@link #create()} or {@link #fromAABB(AxisAlignedBB)}.
     *
     * @param frontBottomLeft the front, bottom left vertex
     * @param frontBottomRight the front, bottom right vertex
     * @param frontTopLeft the front, top left vertex
     * @param frontTopRight the front, top right vertex
     * @param backBottomLeft the back, bottom left vertex
     * @param backBottomRight the back, bottom right vertex
     * @param backTopLeft the back, top left vertex
     * @param backTopRight the back, top right vertex
     */
    public AllVertexBoundingBox(Vector3d frontBottomLeft, Vector3d frontBottomRight, Vector3d frontTopLeft, Vector3d frontTopRight, Vector3d backBottomLeft, Vector3d backBottomRight, Vector3d backTopLeft, Vector3d backTopRight) {
        this.frontBottomLeft = frontBottomLeft;
        this.frontBottomRight = frontBottomRight;
        this.frontTopLeft = frontTopLeft;
        this.frontTopRight = frontTopRight;
        this.backBottomLeft = backBottomLeft;
        this.backBottomRight = backBottomRight;
        this.backTopLeft = backTopLeft;
        this.backTopRight = backTopRight;
    }

    /**
     * Create an {@link AllVertexBoundingBox} based on an AxisAlignedBB. The missing vertices will
     * be calculated based on the min and max points from the provided bounds. The created bounding
     * box will therefore match the bounds of the one provided.
     *
     * @param bounds the bounding box from which to create an AllVertexBoundingBox
     *
     * @return the newly created AllVertexBoundingBox
     */
    public static AllVertexBoundingBox fromAABB(AxisAlignedBB bounds) {
        return fromAABB(bounds.minX, bounds.minY, bounds.minZ, bounds.maxX, bounds.maxY, bounds.maxZ);
    }

    /**
     * Create an {@link AllVertexBoundingBox} based on a set of coordinates. The missing vertices will
     * be calculated based on the min and max points from the provided bounds. The created bounding
     * box will therefore match the bounds of the one provided.
     *
     * @param x the first x coordinate
     * @param y the first y coordinate
     * @param z the first z coordinate
     * @param deltaX the second x coordinate
     * @param deltaY the second y coordinate
     * @param deltaZ the second z coordinate
     *
     * @return the newly created AllVertexBoundingBox
     */
    public static AllVertexBoundingBox fromAABB(double x, double y, double z, double deltaX, double deltaY, double deltaZ) {
        double minX = Math.min(x, deltaX);
        double minY = Math.min(y, deltaY);
        double minZ = Math.min(z, deltaZ);
        double maxX = Math.max(x, deltaX);
        double maxY = Math.max(y, deltaY);
        double maxZ = Math.max(z, deltaZ);

        return create().frontBottomLeft(minX, minY, minZ)
                .frontBottomRight(maxX, minY, minZ)
                .frontTopLeft(minX, maxY, minZ)
                .frontTopRight(maxX, maxY, minZ)
                .backBottomLeft(minX, minY, maxZ)
                .backBottomRight(maxX, minY, maxZ)
                .backTopLeft(minX, maxY, maxZ)
                .backTopRight(maxX, maxY, maxZ).build();
    }

    /**
     * Get a builder instance to create an {@link AllVertexBoundingBox}.
     *
     * @return the builder instance
     */
    public static AllVertexBoundingBox.Builder create() {
        return new AllVertexBoundingBox.Builder();
    }

    /**
     * Get the front, bottom left vertex of this bounding box.
     *
     * @return the front, bottom left vertex
     */
    public Vector3d getFrontBottomLeft() {
        return frontBottomLeft;
    }

    /**
     * Get the front, bottom right vertex of this bounding box.
     *
     * @return the front, bottom right vertex
     */
    public Vector3d getFrontBottomRight() {
        return frontBottomRight;
    }

    /**
     * Get the front, top left vertex of this bounding box.
     *
     * @return the front, top left vertex
     */
    public Vector3d getFrontTopLeft() {
        return frontTopLeft;
    }

    /**
     * Get the front, top right vertex of this bounding box.
     *
     * @return the front, top right vertex
     */
    public Vector3d getFrontTopRight() {
        return frontTopRight;
    }

    /**
     * Get the back, bottom left vertex of this bounding box.
     *
     * @return the back, bottom left vertex
     */
    public Vector3d getBackBottomLeft() {
        return backBottomLeft;
    }

    /**
     * Get the back, bottom right vertex of this bounding box.
     *
     * @return the back, bottom right vertex
     */
    public Vector3d getBackBottomRight() {
        return backBottomRight;
    }

    /**
     * Get the back, top left vertex of this bounding box.
     *
     * @return the back, top left vertex
     */
    public Vector3d getBackTopLeft() {
        return backTopLeft;
    }

    /**
     * Get the back, top right vertex of this bounding box.
     *
     * @return the back, top right vertex
     */
    public Vector3d getBackTopRight() {
        return backTopRight;
    }

    /**
     * Rotate this bounding box clockwise around the y axis.
     *
     * @param radians the amount (in radians) by which to rotate this bounding box.
     *
     * @return a new {@link AllVertexBoundingBox} rotated clockwise by the specified radians
     */
    public AllVertexBoundingBox rotateAroundY(double radians) {
        double sin = Math.sin(radians), cos = Math.cos(radians);

        double frontBottomLeftRotatedX = (frontBottomLeft.x * cos) + (frontBottomLeft.z * sin);
        double frontBottomLeftRotatedZ = (-frontBottomLeft.x * sin) + (frontBottomLeft.z * cos);
        double frontBottomRightRotatedX = (frontBottomRight.x * cos) + (frontBottomRight.z * sin);
        double frontBottomRightRotatedZ = (-frontBottomRight.x * sin) + (frontBottomRight.z * cos);
        double frontTopLeftRotatedX = (frontTopLeft.x * cos) + (frontTopLeft.z * sin);
        double frontTopLeftRotatedZ = (-frontTopLeft.x * sin) + (frontTopLeft.z * cos);
        double frontTopRightRotatedX = (frontTopRight.x * cos) + (frontTopRight.z * sin);
        double frontTopRightRotatedZ = (-frontTopRight.x * sin) + (frontTopRight.z * cos);

        double backBottomLeftRotatedX = (backBottomLeft.x * cos) + (backBottomLeft.z * sin);
        double backBottomLeftRotatedZ = (-backBottomLeft.x * sin) + (backBottomLeft.z * cos);
        double backBottomRightRotatedX = (backBottomRight.x * cos) + (backBottomRight.z * sin);
        double backBottomRightRotatedZ = (-backBottomRight.x * sin) + (backBottomRight.z * cos);
        double backTopLeftRotatedX = (backTopLeft.x * cos) + (backTopLeft.z * sin);
        double backTopLeftRotatedZ = (-backTopLeft.x * sin) + (backTopLeft.z * cos);
        double backTopRightRotatedX = (backTopRight.x * cos) + (backTopRight.z * sin);
        double backTopRightRotatedZ = (-backTopRight.x * sin) + (backTopRight.z * cos);

        return create().frontBottomLeft(frontBottomLeftRotatedX, frontBottomLeft.y, frontBottomLeftRotatedZ)
                .frontBottomRight(frontBottomRightRotatedX, frontBottomRight.y, frontBottomRightRotatedZ)
                .frontTopLeft(frontTopLeftRotatedX, frontTopLeft.y, frontTopLeftRotatedZ)
                .frontTopRight(frontTopRightRotatedX, frontTopRight.y, frontTopRightRotatedZ)
                .backBottomLeft(backBottomLeftRotatedX, backBottomLeft.y, backBottomLeftRotatedZ)
                .backBottomRight(backBottomRightRotatedX, backBottomRight.y, backBottomRightRotatedZ)
                .backTopLeft(backTopLeftRotatedX, backTopLeft.y, backTopLeftRotatedZ)
                .backTopRight(backTopRightRotatedX, backTopRight.y, backTopRightRotatedZ).build();
    }

    public static class Builder {

        private Vector3d frontBottomLeft, frontBottomRight;
        private Vector3d frontTopLeft, frontTopRight;

        private Vector3d backBottomLeft, backBottomRight;
        private Vector3d backTopLeft, backTopRight;

        private Builder() { }

        public Builder frontBottomLeft(double x, double y, double z) {
            this.frontBottomLeft = new Vector3d(x, y, z);
            return this;
        }

        public Builder frontBottomRight(double x, double y, double z) {
            this.frontBottomRight = new Vector3d(x, y, z);
            return this;
        }

        public Builder frontTopLeft(double x, double y, double z) {
            this.frontTopLeft = new Vector3d(x, y, z);
            return this;
        }

        public Builder frontTopRight(double x, double y, double z) {
            this.frontTopRight = new Vector3d(x, y, z);
            return this;
        }

        public Builder backBottomLeft(double x, double y, double z) {
            this.backBottomLeft = new Vector3d(x, y, z);
            return this;
        }

        public Builder backBottomRight(double x, double y, double z) {
            this.backBottomRight = new Vector3d(x, y, z);
            return this;
        }

        public Builder backTopLeft(double x, double y, double z) {
            this.backTopLeft = new Vector3d(x, y, z);
            return this;
        }

        public Builder backTopRight(double x, double y, double z) {
            this.backTopRight = new Vector3d(x, y, z);
            return this;
        }

        public AllVertexBoundingBox build() {
            return new AllVertexBoundingBox(frontBottomLeft, frontBottomRight, frontTopLeft, frontTopRight, backBottomLeft, backBottomRight, backTopLeft, backTopRight);
        }

    }

}
