package tfcs.util;

import com.bioxx.tfc.Blocks.Terrain.BlockSmooth;
import com.bioxx.tfc.api.TFCBlocks;

import tfcs.blocks.BlockAqueductPart;
import tfcs.blocks.devices.BlockAqueduct;
import tfcs.blocks.devices.BlockWall;
import tfcs.tileentities.TileEntityAqueduct;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WaterPacket {

	static final int maxDrop = 10;
	static final int maxWater = 40;

	private int drop = 0;
	private int water = 0;
	private int motionX = 0;
	private int motionY = 0;
	private int motionZ = 0;

	public WaterPacket(int water, int drop) {
		this.water = water;
		this.drop = drop;
	}

	public int getDrop() {
		return drop;
	}

	public static int getMaxDrop() {
		return maxDrop;
	}

	public int getWater() {
		return water;
	}

	public static int getMaxWater() {
		return maxWater;
	}

	public void setDrop(int drop) {
		this.drop = drop;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public WaterPacket dividePacket(int i) {
		this.water /= i;
		return this;
	}

	public void addWater(int water) {
		this.water += water;
	}

	public int getMotionX() {
		return motionX;
	}

	public void setMotionX(int motionX) {
		this.motionX = motionX;
	}

	public int getMotionY() {
		return motionY;
	}

	public void setMotionY(int motionY) {
		this.motionY = motionY;
	}

	public int getMotionZ() {
		return motionZ;
	}

	public void setMotionZ(int motionZ) {
		this.motionZ = motionZ;
	}

	public void setPeference(int x, int y, int z) {
		this.setMotionX(x);
		this.setMotionY(y);
		this.setMotionZ(z);
	}

	public boolean hasPeference() {
		return this.getMotionX() != 0 || this.getMotionY() != 0 || this.getMotionZ() != 0;
	}

	public static void moveWater(World world, int x, int y, int z, TileEntityAqueduct te, WaterPacket packet) {
		int[] count = new int[] { 0, 0, 0, 10000 };
		int[] count2;

		if (packet.hasPeference()) {
			count2 = isValidAqueduct(world, x + packet.getMotionX(), y + packet.getMotionY(), +packet.getMotionZ());
			if (count2 != null && count2[3] < count[3]) {
				count[3] = 0;
				count = count2;
			}
			count2 = isValidAqueduct(world, x + packet.getMotionX(), y + packet.getMotionY() - 1, +packet.getMotionZ());
			if (count2 != null && count2[3] < count[3]) {
				count[3] = 0;
				count = count2;
			}
		}

		if (count[0] == 0 && count[1] == 0 && count[2] == 0) {
			count2 = isValidAqueduct(world, x + 1, y - 1, z);
			if (count2 != null && count2[3] < count[3]) {
				count = count2;
				packet.setPeference(1, 0, 0);
			}
			count2 = isValidAqueduct(world, x - 1, y - 1, z);
			if (count2 != null && count2[3] < count[3]) {
				count = count2;
				packet.setPeference(-1, 0, 0);
			}
			count2 = isValidAqueduct(world, x, y - 1, z + 1);
			if (count2 != null && count2[3] < count[3]) {
				count = count2;
				packet.setPeference(0, 0, 1);
			}
			count2 = isValidAqueduct(world, x, y - 1, z - 1);
			if (count2 != null && count2[3] < count[3]) {
				count = count2;
				packet.setPeference(0, 0, -1);
			}
			if (count[0] == 0 && count[1] == 0 && count[2] == 0) {
				count2 = isValidAqueduct(world, x + 1, y, z);
				if (count2 != null && count2[3] < count[3]) {
					count = count2;
					packet.setPeference(1, 0, 0);
				}
				count2 = isValidAqueduct(world, x - 1, y, z);
				if (count2 != null && count2[3] < count[3]) {
					count = count2;
					packet.setPeference(-1, 0, 0);
				}
				count2 = isValidAqueduct(world, x, y, z + 1);
				if (count2 != null && count2[3] < count[3]) {
					count = count2;
					packet.setPeference(0, 0, 1);
				}
				count2 = isValidAqueduct(world, x, y, z - 1);
				if (count2 != null && count2[3] < count[3]) {
					count = count2;
					packet.setPeference(0, 0, -1);
				}
			}
		}
		if (!(count[0] == 0 && count[1] == 0 && count[2] == 0)) {
			sendWater(world, count[0], count[1], count[2], packet, te.yCoord);
			te.removeWater(packet);
		}
	}

	private static void sendWater(World world, int x, int y, int z, WaterPacket packet, int yCoord) {
		TileEntityAqueduct te = (TileEntityAqueduct) world.getTileEntity(x, y, z);
		te.addWater(new WaterPacket(packet.getWater(), y != yCoord ? WaterPacket.getMaxDrop() : packet.getDrop()));
	}

	private static int[] isValidAqueduct(World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null)
			if (te instanceof TileEntityAqueduct)
				if (isCompletAqueduct(world, x, y, z))
					return new int[] { x, y, z, ((TileEntityAqueduct) te).getWaterPacket().getWater() };
		return null;
	}

	private static boolean isCompletAqueduct(World world, int x, int y, int z) {
		int count = 0;
		count += isAqueductPart(world, x + 1, y, z);
		count += isAqueductPart(world, x - 1, y, z);
		count += isAqueductPart(world, x, y, z + 1);
		count += isAqueductPart(world, x, y, z - 1);
		return count >= 4;
	}

	private static byte isAqueductPart(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof BlockAqueduct || world.getBlock(x, y - 1, z) != null && world.getBlock(x, y - 1, z) instanceof BlockAqueduct
				|| world.getBlock(x, y + 1, z) != null && world.getBlock(x, y + 1, z) instanceof BlockAqueduct || world.getBlock(x, y + 1, z) instanceof BlockSmooth
				|| (world.getBlock(x, y + 1, z) instanceof BlockWall && world.getBlock(x, y + 1, z).getMaterial().equals(Material.rock)) || world.getBlock(x, y + 1, z) != null
				&& world.getBlock(x, y + 1, z) instanceof BlockAqueductPart || world.getBlock(x, y - 1, z) != null && world.getBlock(x, y - 1, z) instanceof BlockAqueductPart
				|| world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof BlockAqueductPart)
			return 1;
		return 0;
	}

	public static boolean hasWater(World world, int x, int y, int z) {
		// if (world.getBlock(x, y + 1,
		// z).equals(TFCBlocks.freshWaterStationary))
		// return true;
		if (world.getBlock(x + 1, y + 1, z).equals(TFCBlocks.freshWaterStationary))
			return true;
		if (world.getBlock(x - 1, y + 1, z).equals(TFCBlocks.freshWaterStationary))
			return true;
		if (world.getBlock(x, y + 1, z + 1).equals(TFCBlocks.freshWaterStationary))
			return true;
		if (world.getBlock(x, y + 1, z - 1).equals(TFCBlocks.freshWaterStationary))
			return true;
		return false;
	}

}
