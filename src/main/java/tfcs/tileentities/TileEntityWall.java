package tfcs.tileentities;

import tfcs.util.ResistanceHelper;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityWall extends TileEntityTFCS {

	private int maxHitPoints = -1;
	private int hitPoints = 0;

	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = maxHitPoints;
	}

	public short getRenderIcon() {
		short s = (short) (10 - ((10 * hitPoints) / maxHitPoints));
		return s < 0 ? 0 : s < 11 ? s : 10;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public void setWallHitPoints(int maxHitPoints, int hitPoints) {
		this.maxHitPoints = maxHitPoints;
		this.hitPoints = hitPoints;
	}

	public boolean damageWall(float damage) {
		hitPoints -= (int) (damage / getResistance());
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return hitPoints <= 0;
	}

	private float getResistance() {
		float i = 1;
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord + 1, yCoord, zCoord);
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord - 1, yCoord, zCoord);
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord, yCoord + 1, zCoord);
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord, yCoord - 1, zCoord);
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord, yCoord, zCoord + 1);
		i += ResistanceHelper.getResistanceFromBlock(worldObj, xCoord, yCoord, zCoord - 1);
		return i;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getMaxHitPoints() {
		return maxHitPoints;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("HIT_POINT", hitPoints);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		hitPoints = nbt.getInteger("HIT_POINT");
	}
}
