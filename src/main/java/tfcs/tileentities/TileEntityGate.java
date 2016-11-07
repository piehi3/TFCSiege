package tfcs.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGate extends TileEntityWall {
	private double gatePostion = 0;
	private byte texture = 0;

	public boolean onGateMove(double rotation) {
		this.gatePostion += rotation;
		if (Math.abs(gatePostion) >= 360) {
			gatePostion = 0;
			return true;
		}
		return false;
	}

	public void shiftGate(int motionX, int motionY, int motionZ) {
		worldObj.setBlock(xCoord - motionX, yCoord - motionY, zCoord - motionZ, worldObj.getBlock(xCoord, yCoord, zCoord));
		TileEntity te = worldObj.getTileEntity(xCoord - motionX, yCoord - motionY, zCoord - motionZ);
		if (te != null && te instanceof TileEntityGate)
			((TileEntityGate) te).setHitPoints(this.getHitPoints());
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean damageWall(float damage) {
		return super.damageWall(damage);
	}
	
	public double getGatePostion() {
		return gatePostion;
	}

	public void setGatePostion(double gatePostion) {
		this.gatePostion = gatePostion;
	}

	public float getRenderPostion() {
		return (float) (gatePostion / 360);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		texture = nbt.getByte("TEXTURE");
		gatePostion = nbt.getDouble("POSTION");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("TEXTURE", texture);
		nbt.setDouble("POSTION", gatePostion);
	}

	public byte getTexture() {
		return texture;
	}

	public TileEntityGate setTexture(byte texture) {
		this.texture = texture;
		return this;
	}

}
