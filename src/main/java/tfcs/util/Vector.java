package tfcs.util;

import net.minecraft.entity.Entity;

public class Vector {

	private double motionX;
	private double motionY;
	private double motionZ;

	public Vector(double pitch, double yaw, double force) {
		this.setMotionY(force * CalculationHelper.sin(pitch));
		double f2 = force * CalculationHelper.cos(pitch);
		this.setMotionX(-f2 * CalculationHelper.sin(yaw));
		this.setMotionZ(-f2 * CalculationHelper.cos(yaw));
	}

	public Vector(double pitch, double roll, double yaw, double force) {
		this.setMotionX(force * CalculationHelper.cos(pitch));
		double f2 = force * CalculationHelper.sin(pitch);
		this.setMotionY(f2 * CalculationHelper.sin(roll));
		this.setMotionZ(-f2 * CalculationHelper.cos(roll));

	}

	public Vector(double motionX, double motionY, double motionZ, boolean b) {
		this.setMotionX(motionX);
		this.setMotionY(motionY);
		this.setMotionZ(motionZ);
	}

	public Vector addVector(Vector v) {
		this.motionX += v.getMotionX();
		this.motionY += v.getMotionY();
		this.motionZ += v.getMotionZ();
		return this;
	}

	public Vector multiplyVectorByVector(Vector v) {
		this.motionX *= v.getMotionX();
		this.motionY *= v.getMotionY();
		this.motionZ *= v.getMotionZ();
		return this;
	}

	public Vector scaleVector(double d) {
		this.motionX *= d;
		this.motionY *= d;
		this.motionZ *= d;
		return this;
	}

	public Vector inversVector() {
		return new Vector(-motionX, -motionY, -motionZ, false);
	}

	public double getMotionY() {
		return motionY;
	}

	public void setMotionY(double motionY) {
		this.motionY = motionY;
	}

	public double getMotionX() {
		return motionX;
	}

	public void setMotionX(double motionX) {
		this.motionX = motionX;
	}

	public double getMotionZ() {
		return motionZ;
	}

	public void setMotionZ(double motionZ) {
		this.motionZ = motionZ;
	}

	public void applyVectorToEntity(Entity entity) {
		entity.motionX = this.motionX;
		entity.motionY = this.motionY;
		entity.motionZ = this.motionZ;
	}

	public void addVectorToEntity(Entity entity) {
		entity.motionX += this.motionX;
		entity.motionY += this.motionY;
		entity.motionZ += this.motionZ;
	}

	public static Vector getGravityVector() {
		return new Vector(0, -0.07, 0, false);
	}

	public void setMagnitude(int i) {
		double d = i / CalculationHelper.pythagoreanTheorem3D(motionX, motionY, motionZ);
		this.scaleVector(d);
	}

	public double getMagnitude() {
		return CalculationHelper.pythagoreanTheorem3D(motionX, motionY, motionZ);
	}

	public void rotateVector(double pitch, double yaw) {
		double magnitude = this.getMagnitude();

		pitch += CalculationHelper.arcsin(this.motionY / magnitude);
		double f1 = magnitude * CalculationHelper.cos(pitch);
		if (motionX != 0) {
			yaw += CalculationHelper.arcsin(-this.motionX / f1);
		} else {
			yaw += CalculationHelper.arccos(-this.motionZ / f1);
		}

		this.setMotionY(magnitude * CalculationHelper.sin(pitch));
		double f2 = magnitude * CalculationHelper.cos(pitch);
		this.setMotionX(-f2 * CalculationHelper.sin(yaw));
		this.setMotionZ(-f2 * CalculationHelper.cos(yaw));
	}
}
