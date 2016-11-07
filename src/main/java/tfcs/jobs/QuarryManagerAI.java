package tfcs.jobs;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import tfcs.entity.EntitySettler;
import tfcs.util.BlockHelper;

public class QuarryManagerAI extends JobAIBase {

	EntitySettler es;
	int[] coord = new int[] { 0, 0, 0 };
	int size;
	int spotSize = 1;
	int maxSize = 9;
	int layer;
	int subLayer = 3;// max of 3
	int thickness = 1;
	int x = 0;
	int y = 0;

	boolean isActive;

	public QuarryManagerAI(EntitySettler es) {
		super(es);
		this.es = es;
		coord = new int[] { BlockHelper.shiftCoord(es.posX), BlockHelper.shiftCoord(es.posY), BlockHelper.shiftCoord(es.posZ) };
		this.layer = 1;
		this.subLayer = 3;
		this.thickness = 1;
		this.arm1 = -1;
		this.size = spotSize;
	}

	@Override
	public boolean shouldExecute() {
		for (int i = 1; i <= 5; i++) {
			Block block = es.worldObj.getBlock(BlockHelper.shiftCoord(es.posX), BlockHelper.shiftCoord(es.posY) - i, BlockHelper.shiftCoord(es.posZ));
			if (block == null || block.equals(Blocks.air))
				return false;
		}
		return true;
	}

	@Override
	public boolean continueExecuting() {
		return !es.isDead;
	}

	@Override
	public void startExecuting() {
		this.layer = 1;
		this.subLayer = 3;
		this.size = spotSize;
		this.arm1 = -1;
		isActive = true;
		this.coord = new int[] { BlockHelper.shiftCoord(es.posX), BlockHelper.shiftCoord(es.posY), BlockHelper.shiftCoord(es.posZ) };
	}

	@Override
	public void resetTask() {
		this.layer = 1;
		this.subLayer = 3;
		this.arm1 = -1;
		this.size = spotSize;
		isActive = true;
	}

	public void setQuaryPos(int x, int y, int z) {
		this.coord = new int[] { x, y, z };
	}

	@Override
	public void updateTask() {
		if (isActive) {
			mineRealBlock(0);
		}
	}

	private void mineRealBlock(int i) {
		int[] coord = selectBlock();
		if (es.worldObj.getBlock(coord[0] + this.coord[0], coord[1] + this.coord[1], coord[2] + this.coord[2]) != Blocks.air) {
			digBlock(coord[0], coord[1], coord[2]);
		} else {
			if (i > 100)
				mineRealBlock(i++);
		}
	}

	private int[] selectBlock() {
		int[] coord = null;
		if (arm1 > getSize() || arm1 == -1) {
			if (x > getSize() - 2) {
				coord = getNextCenterCoord();
				x = 0;
				if (y > getSize() - 2) {
					y = 0;
					if (arm1 > getSize()) {
						arm1 = arm2 = arm3 = -1;
						if (subLayer <= 0) {
							subLayer = 3;
							if (size <= 0) {
								layer = 0;
								if (size > maxSize) {
									this.isActive = false;
								} else {
									spotSize++;
									size = spotSize;
								}
							} else {
								size--;
							}
						} else {
							subLayer--;
							layer++;
						}
					} else {
						arm1 = arm2 = arm3 = 0;
					}
				} else {
					y++;
				}
			} else {
				coord = getNextCenterCoord();
				x++;
			}
		} else {
			coord = getNextArmCoord();
		}
		return coord;
	}

	private int[] getNextArmCoord() {
		switch (subLayer) {
		case 3:
			return getNextArm3Coord();
		case 2:
			return getNextArm2Coord();
		case 1:
			return getNextArm1Coord();
		default: {
			arm1 = arm2 = arm3 = getSize() + 1;
			return new int[] { 0, 0, 0 };
		}
		}
	}

	int arm3;

	private int[] getNextArm3Coord() {
		int c = -((getSize() - 1) / 2);
		int[] coord = new int[] { 0, 0, 0 };
		if (arm3 > getSize()) {
			coord = getNextArm2Coord();
		} else {
			coord = new int[] { arm3 + c, -layer, c };
			arm3++;
		}
		return coord;
	}

	int arm2;

	private int[] getNextArm2Coord() {
		int c = -((getSize() - 1) / 2);
		int[] coord = new int[] { 0, 0, 0 };
		if (arm2 > getSize()) {
			coord = getNextArm1Coord();
		} else {
			coord = new int[] { -c + 2, -layer, arm2 + c };
			arm2++;
		}
		return coord;
	}

	int arm1 = -1;

	private int[] getNextArm1Coord() {
		int c = -((getSize() - 1) / 2);
		int[] coord = new int[] { 0, 0, 0 };
		if (arm1 < getSize() + 1) {
			coord = new int[] { -c - arm1 + 2, -layer, -c + 2 };
			arm1++;
		}
		return coord;
	}

	private int[] getNextCenterCoord() {
		int c = -((getSize() - 3) / 2);
		return new int[] { x + c, -layer, y + c };
	}

	public boolean digBlock(int x, int y, int z) {
		x += coord[0];
		y += coord[1];
		z += coord[2];
		if (es.worldObj.getBlock(x, y, z) != null && !es.worldObj.getBlock(x, y, z).equals(Blocks.air)) {
			es.worldObj.setBlock(x, y, z, Blocks.air);

			return true;
		}
		return false;
	}

	private int getSize() {
		return (this.size * 2) + 1;
	}

}
