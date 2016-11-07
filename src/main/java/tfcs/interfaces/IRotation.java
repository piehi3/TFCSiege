package tfcs.interfaces;

import tfcs.core.RotationPacket;

public interface IRotation {
	public double maxRotation = 0;
	public double minTorque = 0;
	public boolean hasRotated = false;
	public void addRotation(RotationPacket packet);
	public void setHasRotated(boolean b);
	public boolean breakGear();
	public void unloadRotationPacket(RotationPacket packet);
	public boolean sameAxes(IRotation r);
	public int[] getAxe();
}
