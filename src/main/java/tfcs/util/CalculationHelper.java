package tfcs.util;

import java.util.List;




import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CalculationHelper {

	public static double pythagoreanTheorem2D(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	public static double pythagoreanTheorem3D(double a, double b, double c) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
	}

	public static double logOfBase(int base, double num) {
		return Math.log(num) / Math.log(base);
	}

	public static double sin(double i) {
		return Math.sin(i * Math.PI / 180);
	}

	public static double cos(double i) {
		return Math.cos(i * Math.PI / 180);
	}

	public static double tan(double i) {
		return Math.tan(i * Math.PI / 180);
	}

	public static double csc(double i) {
		return 1 / Math.sin(i * Math.PI / 180);
	}

	public static double sec(double i) {
		return 1 / Math.cos(i * Math.PI / 180);
	}

	public static double cot(double i) {
		return 1 / Math.tan(i * Math.PI / 180);
	}

	public static double arcsin(double i) {
		return 180 * (Math.asin(i) / Math.PI);
	}

	public static double arccos(double i) {
		return 180 * (Math.acos(i) / Math.PI);
	}

	public static double arctan(double i) {
		return 180 * (Math.atan(i) / Math.PI);
	}

	public static double arccsc(double i) {
		return 180 * (1 / Math.asin(i) / Math.PI);
	}

	public static double arcsec(double i) {
		return 180 * (1 / Math.acos(i) / Math.PI);
	}

	public static double arccot(double i) {
		return 180 * (1 / Math.atan(i) / Math.PI);
	}

	public static double cosineLaw(double a, double b, double c) {
		return Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2) - 2 * b * c * CalculationHelper.cos(a));
	}

	public static double arcCosineLaw(double a, double b, double c) {
		return CalculationHelper.arccos((Math.pow(b, 2) + Math.pow(c, 2) - Math.pow(a, 2)) / (2 * b * c));
	}

	public static double pptodmYaw(double x1, double y1, double x2, double y2) {
		return (y2 - y1) >= 0 ? pptodm2DSub1(x1, y1, x2, y2) : pptodm2DSub1(x1, y1, x2, y2) + 180;
	}

	private static double pptodm2DSub1(double x1, double y1, double x2, double y2) {
		return y2 - y1 == 0 ? pptodm2DSub2(x1, y1, x2, y2) : CalculationHelper.arctan((x2 - x1) / (y2 - y1));
	}

	private static double pptodm2DSub2(double x1, double y1, double x2, double y2) {
		return x2 - x1 > 0 ? 90 : 270;
	}

	public static double pptodmPitch(double x1, double y1, double z1, double x2, double y2, double z2, double yaw) {
		if (yaw == 0) {
			return CalculationHelper.arctan((y2 - y1) / (z2 - z1));
		} else if (yaw == 180) {
			return CalculationHelper.arctan((y2 - y1) / (z1 - z2));
		} else if (x2 - x1 == 0 && z2 - z1 == 0) {
			return y2 - y1 > 0 ? 90 : 270;
		} else {
			return CalculationHelper.sin(yaw) == 0 ? CalculationHelper.arctan((y2 - y1) / (z2 - z1)) : CalculationHelper.arctan(((y2 - y1) * CalculationHelper.sin(yaw)) / (x2 - x1));
		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotOnList(Object o, List list) {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(o)) {
					return false;
				}
			}
		}
		return true;
	}
	public static boolean isBlockNotOnArray(int[] block, int[][] blocks) {
		if (blocks != null && block != null) {
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i][0] == block[0] && blocks[i][1] == block[1] && blocks[i][2] == block[2]) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}

	public static int[][] addBlockToArray(int[] block, int[][] blocks) {
		int k = blocks != null ? blocks.length : 0;
		int[][] blocks2 = new int[k + 1][2];
		if (blocks != null) {
			for (int i = 0; i < blocks.length; i++) {
				blocks2[i] = blocks[i];
			}
		}
		blocks2[k] = block;
		return blocks2;
	}
	
	public static EntityLivingBase[] addEntityToEntityArray(EntityLivingBase[] entityArray, EntityLivingBase entity) {
		int k = entityArray != null ? entityArray.length : 0;
		EntityLivingBase[] entityArray2 = new EntityLivingBase[k + 1];
		if (entityArray != null)
			for (int i = 0; i < entityArray.length; i++)
				entityArray2[i] = entityArray[i];
		entityArray2[k] = entity;
		return entityArray2;
	}

	public static double getScaledMotion(double x,double y, double r){
		return Math.sqrt((x*x)+(y*y)-(r*r));
	}
	
	private static boolean isInlineOfSight(double c, World world, double[] e, double radius, double x1, double y1, double z1) {
		if (isBlockValid(world, (int) x1, (int) y1, (int) z1)) {
			if (radius <= 0) {
				return true;
			} else {
				return isInlineOfSight(c, world, e, radius - 1, x1 + e[0], y1 + e[1], z1 + e[2]);
			}
		}
		return false;
	}

	public static boolean isInlineOfSight(World world, double x1, double y1, double z1, double x2, double y2, double z2) {
		double c = 0.5;

		x1 += c;
		y1 += c;
		z1 += c;
		x2 += c;
		y2 += c;
		z2 += c;

		Vector v = new Vector(x2 - x1, y2 - y1, z2 - z1);
		v.setMagnitude(1);
		double[] e = new double[] { v.getMotionX(), v.getMotionY(), v.getMotionZ() };
		return isInlineOfSight(c, world, e, CalculationHelper.pythagoreanTheorem3D(x2 - x1, y2 - y1, z2 - z1) - 2, x1 + e[0], y1 + e[1], z1 + e[2]);
	}
	
	public static boolean isBlockValid(World world, int x, int y, int z) {
		if (world.getTileEntity((int) x, (int) y, (int) z) != null) {
			//if (world.getTileEntity((int) x, (int) y, (int) z) instanceof IArmor) {
				return false;
			//}
		}
		if (world.getBlock((int) x, (int) y, (int) z) != null) {
			if (world.getBlock((int) x, (int) y, (int) z).equals(Blocks.air) || world.getBlock((int) x, (int) y, (int) z).getMaterial().equals(Material.glass)
					|| world.getBlock((int) x, (int) y, (int) z).getMaterial().equals(Material.plants) || world.getBlock((int) x, (int) y, (int) z).getMaterial().equals(Material.snow)) {																																							// z).isBlockNormalCube()
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
}
