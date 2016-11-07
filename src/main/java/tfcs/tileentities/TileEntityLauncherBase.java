package tfcs.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import tfcs.core.EntityRegistryTFCS;
import tfcs.core.GearRegistry;
import tfcs.core.RotationPacket;
import tfcs.entity.ProjectileCannonBall;
import tfcs.entity.ProjectileTFCS;
import tfcs.gears.FrameComponent;
import tfcs.gears.ToolComponent;
import tfcs.launcherComponent.CannonBarrelComponent;
import tfcs.launcherComponent.LauncherComponent;
import tfcs.util.Vector;

public class TileEntityLauncherBase extends TileEntityGearFrame {

	private CannonBarrelComponent component;

	private int maxFuse = 21;
	private int fuse = maxFuse;
	private double pitch = 0;
	private double yaw = 0;
	private double gunPowder = 0;

	private ProjectileTFCS entity;

	public TileEntityLauncherBase() {
		this.axe = new int[] { 0, 0, 1 };
	}

	@Override
	public void updateEntity() {
		if (hasFuse() && hasGunPowder()) {
			if (fuse <= 0) {
				this.fuse = maxFuse;
				if (this.entity != null)
					explode();
				this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "random.explode", (float) gunPowder / 2,
						(1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 1.0F);
				this.worldObj.spawnParticle("largeexplode", this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5, 0, 0, 0);
			} else {
				fuse--;
				Vector v = new Vector(this.pitch, this.yaw, 0.1);
				this.worldObj.spawnParticle("smoke", this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5, -v.getMotionX(), v.getMotionY(), -v.getMotionZ());
			}
		}
	}

	public void explode() {
		this.entity = new ProjectileCannonBall(worldObj);
		entity.setPosition(this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5);
		Vector v = new Vector(this.pitch, this.yaw, this.gunPowder);
		this.gunPowder = 0;
		v.applyVectorToEntity(entity);
		if (!this.getWorldObj().isRemote)
			this.worldObj.spawnEntityInWorld(entity);
		this.entity = null;
	}

	@Override
	public void setFrameComponent(FrameComponent frameComponent, boolean override) {

	}

	@Override
	public void setToolComponent(ToolComponent toolComponent) {

	}

	public boolean setComponent(String string) {
		if (this.component != null)
			return false;
		this.component = (CannonBarrelComponent) GearRegistry.instance.getFrameComponentFromString(string);
		if(!this.worldObj.isRemote)
			this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		return true;
	}

	@Override
	public void addRotation(RotationPacket packet) {
		this.yaw += packet.getSpeed();
	}

	public LauncherComponent getComponent() {
		return component;
	}

	public boolean hasComponent() {
		return getComponent() != null;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public void addPitch(double d) {
		this.pitch += d;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public void addYaw(double d) {
		this.yaw += d;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("YAW", this.yaw);
		tag.setDouble("PITCH", this.pitch);
		tag.setDouble("GUNPOWDER", this.gunPowder);
		tag.setInteger("FUSE", this.fuse);
		if (this.hasComponent())
			tag.setString("COMPONENET", component.getSaveName());
		if(this.entity!=null)
			tag.setBoolean("AMMO", true);
		else
			tag.setBoolean("AMMO", false);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.yaw = tag.getDouble("YAW");
		this.pitch = tag.getDouble("PITCH");
		this.gunPowder = tag.getDouble("GUNPOWDER");
		this.fuse = tag.getInteger("FUSE");
		this.component = (CannonBarrelComponent) GearRegistry.instance.getFrameComponentFromString(tag.getString("COMPONENET"));
		if(tag.getBoolean("AMMO"))
			this.entity = new ProjectileCannonBall(worldObj);
	}

	public void addFuse(int i) {
		this.fuse += i;
	}

	public boolean addGunPowder(double d) {
		if (hasComponent()) {
			if (!this.hasMaxGunPowder()) {
				this.gunPowder += d;
				return true;
			}
		}
		return false;
	}

	private boolean hasMaxGunPowder() {
		if (hasComponent())
			return this.gunPowder >= component.getMaxGunPowder();
		return false;
	}

	private boolean hasFuse() {
		return this.fuse < this.maxFuse;
	}

	private boolean hasGunPowder() {
		return this.gunPowder > 0;
	}

	public boolean setPogectile(ProjectileTFCS projectile) {
		if (hasComponent()) {
			if (this.entity == null) {
				this.entity = projectile;
				return true;
			}
		}
		return false;
	}

	public double getMaxGunPowder() {
		if (hasComponent())
			return component.getMaxGunPowder();
		return 0;
	}

	public double getGunPowder() {
		return gunPowder;
	}

	public void light() {
		if (hasComponent() && hasGunPowder()) {
			this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "game.tnt.primed", (float) fuse - 1,
					(1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 1.0F);
			this.fuse--;
		}
	}

	public void removeComponent() {
		this.component = null;
	}

}
