package tfcs.util;

import net.minecraft.world.World;
import tfcs.core.TFCSBlocks;
import tfcs.core.RotationPacket;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.tileentities.TileEntityLauncherBase;

public class RotationHelper {
	public static void rotateAdj(TileEntityGearFrame tileentity, RotationPacket packet) {

		int x = tileentity.xCoord;
		int y = tileentity.yCoord;
		int z = tileentity.zCoord;

		int c = 1;
		if (tileentity.getHasGear())
			c += (tileentity.getGearComponent().getSize() - 1) / 2;

		int count = getAdjBlockCount(tileentity.getWorldObj(), x, y, z, 1, c > 1);
		if (c > 1)
			count += getAdjBlockCount(tileentity.getWorldObj(), x, y, z, c, false);

		if (count > 0 || tileentity.getAxeLocation() != 0) {
			packet.splitTorque(count > 0 ? count : 1);
			rotate(tileentity, packet, x, y, z, true, c);
		}
	}

	private static void rotate(TileEntityGearFrame tileentity, RotationPacket packet, int x, int y, int z, boolean b, int c) {
		int a = (int) tileentity.getAxeLocation();
		int d = a != 0 ? 1 : 0;
		if (tileentity.getAxe(1) == 1) {
			rotateSub(tileentity, packet, x + a, y + c, z, b, -1 * d);
			rotateSub(tileentity, packet, x + a, y - c, z, b, 1 * d);
			rotateSub(tileentity, packet, x + a, y, z + c, b, 1 * d);
			rotateSub(tileentity, packet, x + a, y, z - c, b, -1 * d);
			rotateSub(tileentity, packet, x + 1, y, z, false, 0);
			rotateSub(tileentity, packet, x - 1, y, z, false, 0);
		}
		if (tileentity.getAxe(2) == 1) {
			rotateSub(tileentity, packet, x, y + c, z - a, b, -1 * d);
			rotateSub(tileentity, packet, x, y - c, z - a, b, 1 * d);
			rotateSub(tileentity, packet, x + c, y, z - a, b, -1 * d);
			rotateSub(tileentity, packet, x - c, y, z - a, b, 1 * d);
			rotateSub(tileentity, packet, x, y, z + 1, false, 0);
			rotateSub(tileentity, packet, x, y, z - 1, false, 0);
		}
		if (tileentity.getAxe(3) == 1) {
			rotateSub(tileentity, packet, x + c, y + a, z, b, -1 * d);
			rotateSub(tileentity, packet, x - c, y + a, z, b, 1 * d);
			rotateSub(tileentity, packet, x, y + a, z + c, b, 1 * d);
			rotateSub(tileentity, packet, x, y + a, z - c, b, -1 * d);
			rotateSub(tileentity, packet, x, y + 1, z, false, 0);
			rotateSub(tileentity, packet, x, y - 1, z, false, 0);
		}
	}

	private static void rotateSub(TileEntityGearFrame tileentity, RotationPacket packet, int x, int y, int z, boolean b, int a) {
		if (tileentity.getWorldObj().getTileEntity(x, y, z) != null) {
			if (tileentity.getWorldObj().getTileEntity(x, y, z) instanceof TileEntityGearFrame) {
				TileEntityGearFrame te = (TileEntityGearFrame) tileentity.getWorldObj().getTileEntity(x, y, z);
				if (!te.getHasRotated()) {
					if ((te.getHAsGearOrGostGear() && tileentity.getHasGear() && b) || (!b && te.getHasAxle() && tileentity.getHasAxle())) {
						if (te.sameAxeLocation(a) || (te.sameAxes(tileentity) && a == 0)) {
							if (te.minTorque >= packet.getTorque()) {
								tileentity.breakGear();
							} else if (te.maxRotation <= packet.getSpeed()) {
								te.breakGear();
							} else {
								if (b)
									packet.reverseSpeed();
								if (te.getHAsGearOrGostGear() && tileentity.getHasGear())
									packet.scaleRotation(te.getGearSize(), tileentity.getGearComponent().getSize());
								te.addRotation(packet);
								if (b)
									packet.reverseSpeed();
							}
						}
					} else if (te instanceof TileEntityLauncherBase) {
						if ((!b && tileentity.getHasAxle())) {
							if (tileentity.getAxe(3)==1) {
								if (te.minTorque >= packet.getTorque()) {
									tileentity.breakGear();
								} else {
									te.addRotation(packet);
								}
							}
						}
					}
				}
			}
		}
	}

	private static int getAdjBlockCount(World world, int x, int y, int z, int c, boolean b) {
		int i = 0;
		i += isBlockValid(world, x + c, y, z, b);
		i += isBlockValid(world, x - c, y, z, b);
		i += isBlockValid(world, x, y + c, z, b);
		i += isBlockValid(world, x, y - c, z, b);
		i += isBlockValid(world, x, y, z + c, b);
		i += isBlockValid(world, x, y, z - c, b);
		return i;
	}

	private static int isBlockValid(World world, int x, int y, int z, boolean b) {
		if (world.getBlock(x, y, z) == TFCSBlocks.gearFrame || world.getBlock(x, y, z).equals(TFCSBlocks.launcherBase))
			if (world.getTileEntity(x, y, z) != null)
				if (!((TileEntityGearFrame) world.getTileEntity(x, y, z)).getHasRotated())
					if ((b && !((TileEntityGearFrame) world.getTileEntity(x, y, z)).getHAsGearOrGostGear()) || !b)
						return 1;
		return 0;
	}

}
