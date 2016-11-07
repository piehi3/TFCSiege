package tfcs.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class HeatTransferManager {

	public static final float BLOCK_AREA = 1.0F;
	public static final float TIME_PER_TRANSFER = 0.2F;
	public static final float BLOCK_LEIGHT = 0.5F;
	public static final float RADIADTED_HEAT = 0.01F;
	public static final float BLOCK_TRANSFER_CONSTANT = (BLOCK_AREA * TIME_PER_TRANSFER) / BLOCK_LEIGHT;

	public static void conductHeat(World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		transferHeat(te, world.getTileEntity(x, y + 1, z));
		transferHeat(te, world.getTileEntity(x, y - 1, z));
		transferHeat(te, world.getTileEntity(x + 1, y, z));
		transferHeat(te, world.getTileEntity(x - 1, y, z));
		transferHeat(te, world.getTileEntity(x, y, z + 1));
		transferHeat(te, world.getTileEntity(x, y, z - 1));

		if (te instanceof IHeated)
			radiateHeat((IHeated) te, world, x, y, z);
	}

	private static void radiateHeat(IHeated block, World world, int x, int y, int z) {
		float heat = getRadiadtedHeat(block);
		block.addHeat(getChangeInTemperature(block, -(heat * 6)));

		sendHeatOverDistance(heat, world, x, y, z, 0, 1, 0);
		sendHeatOverDistance(heat, world, x, y, z, 0, -1, 0);
		sendHeatOverDistance(heat, world, x, y, z, 1, 0, 0);
		sendHeatOverDistance(heat, world, x, y, z, -1, 0, 0);
		sendHeatOverDistance(heat, world, x, y, z, 0, 0, 1);
		sendHeatOverDistance(heat, world, x, y, z, 0, 0, -1);
	}

	private static void sendHeatOverDistance(float heat, World world, int x, int y, int z, int i, int k, int j) {
		if (world.isAirBlock(x + i, y + k, z + j))
			if (world.getTileEntity(x + i + i, y + k + k, z + j + j) instanceof IHeated)
				((IHeated) world.getTileEntity(x + i + i, y + k + k, z + j + j)).addHeat(heat);
	}

	private static float getRadiadtedHeat(IHeated block) {
		return block.getNonAnbiantTemperature() * RADIADTED_HEAT;
	}

	public static void transferHeat(TileEntity te1, TileEntity te2) {
		if (te1 == null || te2 == null)
			return;
		if (!(te1 instanceof IHeated) || !(te2 instanceof IHeated))
			return;
		float heat = getHeatTranfered((IHeated) te1, (IHeated) te2);
		((IHeated) te1).addHeat(-getChangeInTemperature(((IHeated) te1), heat));
		((IHeated) te2).addHeat(getChangeInTemperature(((IHeated) te2), heat));

	}

	private static float getChangeInTemperature(IHeated block, float heat) {
		return heat / block.getHeatCapacity();
	}

	public static float getHeatTranfered(IHeated block1, IHeated block2) {
		return getTemperatureDifference(block1, block2) * getThermalConductivity(block1, block2) * BLOCK_TRANSFER_CONSTANT;
	}

	private static float getThermalConductivity(IHeated block1, IHeated block2) {
		return (block1.getThermalConductivity()); // +
													// block2.getThermalConductivity());
	}

	private static float getTemperatureDifference(IHeated block1, IHeated block2) {
		return block1.getHeat() - block2.getHeat();
	}

}
