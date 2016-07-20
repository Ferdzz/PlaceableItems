/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.ferdz.placeableitems;

import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * @author Ordinastie
 *
 */
public class AABBUtils
{
	private static int[] cos = { 1, 0, -1, 0 };
	private static int[] sin = { 0, 1, 0, -1 };

	/**
	 * Rotates the {@link AxisAlignedBB} around the Y axis based on the specified angle.<br>
	 *
	 * @param aabb the aabb
	 * @param angle the angle
	 * @return the axis aligned bb
	 */
	public static AxisAlignedBB rotate(AxisAlignedBB aabb, int angle)
	{
		return rotate(aabb, angle, Axis.Y);
	}

	/**
	 * Rotates the {@link AxisAlignedBB} around the axis based on the specified angle.<br>
	 *
	 * @param aabb the aabb
	 * @param angle the angle
	 * @param axis the axis
	 * @return the axis aligned bb
	 */
	public static AxisAlignedBB rotate(AxisAlignedBB aabb, int angle, Axis axis)
	{
		if (aabb == null || angle == 0 || axis == null)
			return aabb;

		int a = -angle & 3;
		int s = sin[a];
		int c = cos[a];

		aabb = aabb.offset(-0.5F, -0.5F, -0.5F);

		double minX = aabb.minX;
		double minY = aabb.minY;
		double minZ = aabb.minZ;
		double maxX = aabb.maxX;
		double maxY = aabb.maxY;
		double maxZ = aabb.maxZ;

		if (axis == Axis.X)
		{
			minY = (aabb.minY * c) - (aabb.minZ * s);
			maxY = (aabb.maxY * c) - (aabb.maxZ * s);
			minZ = (aabb.minY * s) + (aabb.minZ * c);
			maxZ = (aabb.maxY * s) + (aabb.maxZ * c);
		}
		
		if (axis == Axis.Y)
		{
			minX = (aabb.minX * c) - (aabb.minZ * s);
			maxX = (aabb.maxX * c) - (aabb.maxZ * s);
			minZ = (aabb.minX * s) + (aabb.minZ * c);
			maxZ = (aabb.maxX * s) + (aabb.maxZ * c);
		}

		if (axis == Axis.Z)
		{
			minX = (aabb.minX * c) - (aabb.minY * s);
			maxX = (aabb.maxX * c) - (aabb.maxY * s);
			minY = (aabb.minX * s) + (aabb.minY * c);
			maxY = (aabb.maxX * s) + (aabb.maxY * c);
		}

		aabb = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
		aabb = aabb.offset(0.5F, 0.5F, 0.5F);

		return aabb;
	}
}