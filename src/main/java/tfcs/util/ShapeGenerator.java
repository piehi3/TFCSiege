package tfcs.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ShapeGenerator {

	public static void drawShape(World world, Block block, int x, int y, int z,
			int xMin, int yMin, int zMin, int xMax, int yMax, int zMax,
			boolean isFilled) {
		for (int i = -xMin; i > xMax; xMin++)
			for (int k = -yMin; k > yMax; yMin++)
				for (int j = -zMin; j > zMax; zMin++)
					if (checkEquation(i, k, j, isFilled, 4))
						world.setBlockToAir(x + i, y + k, z + j);

	}

	private static boolean checkEquation(int x, int y, int z, boolean isFilled,
			int size) {
		if (isFilled) {
			if (size * size == (z * z) / (x * x + y * y)) {
				return (size * size == (z * z) / (x * x + y * y));
			}
		} else {
			return (size * size >= (z * z) / (x * x + y * y));
		}
		return false;
	}

}
