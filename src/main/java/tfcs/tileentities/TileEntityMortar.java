package tfcs.tileentities;

import tfcs.core.EntityRegistryTFCS;
import tfcs.entity.ProjectileTFCS;
import tfcs.util.Vector;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMortar extends TileEntityTFCS {

	private double yaw = 90;
	private double pitch = 90;
	private int fuse = 20;
	private boolean shouldFire = false;
	private double gunPowder = 0;
	private double maxGunPowder = 2.5;

	ProjectileTFCS entity = null;

	@Override
	public void updateEntity() {
		if (shouldFire) {
			fuse--;
			doParticles(fuse);
			if (fuse == 0)
				explode();
		}
	}

	private void doParticles(int i) {
		if (i == 20 - 1) {
			this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "game.tnt.primed", (float) fuse - 1, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 1.0F);
		} else if (i > 0) {
			Vector v = new Vector(yaw, pitch, 0, 0.1);
			this.worldObj.spawnParticle("smoke", this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5, -v.getMotionX(), v.getMotionY(), -v.getMotionZ());
		} else if (i == 0) {
			this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "random.explode", (float) gunPowder / 2, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 1.0F);
			this.worldObj.spawnParticle("largeexplode", this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5, 0, 0, 0);
		}
	}

	public void explode() {
		this.shouldFire = false;
		this.fuse = 20;
		entity.setPosition(this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5);
		Vector v = new Vector(yaw, pitch, 0, gunPowder);
		this.gunPowder = 0;
		v.applyVectorToEntity(entity);
		if(!this.getWorldObj().isRemote)
			this.worldObj.spawnEntityInWorld(entity);
		this.entity = null;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.yaw = tag.getDouble("YAW");
		this.pitch = tag.getDouble("PITCH");
		this.fuse = tag.getInteger("FUSE");
		this.shouldFire = tag.getBoolean("SHOULD_FIRE");
		this.gunPowder = tag.getDouble("GUN_POWDER");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("YAW", this.yaw);
		tag.setDouble("PITCH", this.pitch);
		tag.setInteger("FUSE", this.fuse);
		tag.setBoolean("SHOULD_FIRE", this.shouldFire);
		tag.setDouble("GUN_POWDER", this.gunPowder);
	}

	public double getRenderYaw() {
		return yaw - 90;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public double getRenderPitch() {
		return pitch - 90;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public void addYaw(int i) {
		this.yaw += i;
	}

	public void addPitch(int i) {
		this.pitch += i;
	}

	public boolean addGunPowder(double d) {
		if (!this.hasMaxGunPowder()) {
			this.gunPowder += (d / 5);
			return true;
		}
		return false;
	}

	private boolean hasMaxGunPowder() {
		return this.gunPowder >= this.maxGunPowder;
	}

	private boolean hasFuse() {
		return this.fuse > 0;
	}

	private boolean hasGunPowder() {
		return this.gunPowder > 0;
	}

	public void setSohuldFire(boolean b) {
		if (this.hasFuse())
			if (this.hasGunPowder())
				if (this.entity != null)
					this.shouldFire = b;
	}

	public void setPogectile(ProjectileTFCS projectile) {
		if (this.entity == null)
			this.entity = projectile;
	}

	public double getMaxGunPowder() {
		return maxGunPowder;
	}
	
	public double getGunPowder() {
		return gunPowder;
	}
	
}
