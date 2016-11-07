package tfcs.tileentities;

import tfcs.util.IGunpowder;
import tfcs.util.TFCSExplosion;
import tfcs.util.Vector;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBomb extends TileEntityTFCS implements IGunpowder{

	private int maxFuse = 41;
	private int fuse = maxFuse;
	private float explotionModifier;
	private Entity entity;
	private float gunpowder = 0;
	private int maxGunpowderStorage = 32;
	private int gunpowderStorage = 0;
	private boolean isClosed = true; 
	int type = 1;
	
	public void setExplotionModifier(float explotionModifier) {
		this.explotionModifier = explotionModifier;
	}
	
	@Override
	public void updateEntity() {
		if(fuse<maxFuse){
			Vector v = new Vector(0,0.5,0,false);
			this.worldObj.spawnParticle("smoke", this.xCoord + 0.5, this.yCoord + 1, this.zCoord + 0.5, -v.getMotionX(), v.getMotionY(), -v.getMotionZ());
			if(fuse==0){
				explode();
				this.fuse = maxFuse;
			}else{
				fuse--;
			}
		}
	}
	
	public void explode(){
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		TFCSExplosion.explode(worldObj, entity, xCoord, yCoord, zCoord, this.gunpowder * type,explotionModifier, true, true, true);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.fuse = tag.getInteger("FUSE");
		this.setGunpowder(tag.getFloat("GUNPOWDER"));
		this.setGunpowderStorage(tag.getInteger("GUNPOWDER_STORAGE"));
		this.setMaxGunpowderStorage(tag.getInteger("MAX_GUNPOWDER_STORAGE"));
		this.isClosed = tag.getBoolean("IS_CLOSED");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("FUSE", this.fuse);
		tag.setFloat("GUNPOWDER", this.getGunpowder());
		tag.setInteger("GUNPOWDER_STORAGE", this.getGunpowderStorage());
		tag.setInteger("MAX_GUNPOWDER_STORAGE", this.getMaxGunpowderStorage());
		tag.setBoolean("IS_CLOSED", this.isClosed);
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	@Override
	public float getGunpowder() {
		return this.gunpowder;
	}

	@Override
	public int getGunpowderStorage() {
		return this.gunpowderStorage;
	}

	@Override
	public int getMaxGunpowderStorage() {
		return this.maxGunpowderStorage;
	}

	@Override
	public void setGunpowder(float f) {
		this.gunpowder = f;
	}

	@Override
	public void setGunpowderStorage(int i) {
		this.gunpowderStorage = i;
	}

	@Override
	public void setMaxGunpowderStorage(int i) {
		this.maxGunpowderStorage = i;
	}

	@Override
	public int getFuse() {
		return this.fuse;
	}

	@Override
	public void setFuse(int i) {
		this.fuse = i;
	}

	@Override
	public int getMaxFuse() {
		return this.maxFuse;
	}

	@Override
	public void setShooter(Entity shooter) {
		this.entity = shooter;
	}

	@Override
	public boolean isClosed() {
		return isClosed;
	}

	@Override
	public void toggleOpen() {
		this.isClosed = !this.isClosed;
	}

	@Override
	public void updateBlock() {
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
}
