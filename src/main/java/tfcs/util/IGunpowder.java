package tfcs.util;

import net.minecraft.entity.Entity;

public interface IGunpowder {
	
	public float getGunpowder();
	
	public int getGunpowderStorage();
	
	public int getMaxGunpowderStorage();
	
	public void setGunpowder(float f);
	
	public void setGunpowderStorage(int f);
	
	public void setMaxGunpowderStorage(int f);

	public int getFuse();

	public void setFuse(int i);

	public int getMaxFuse();

	public void setShooter(Entity shooter);
	
	public boolean isClosed();
	
	public void toggleOpen();
	
	public void updateBlock();
}
