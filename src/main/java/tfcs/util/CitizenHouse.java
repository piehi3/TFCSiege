package tfcs.util;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import tfcs.entity.EntitySettler;

public class CitizenHouse {
	int freeSpace;
	String player;

	public CitizenHouse(int i, String player) {
		freeSpace = i;
		this.player = player;
	}

	public void setNewSettler(World world, int x, int y, int z) {
			if (freeSpace>0) {
				EntitySettler es = new EntitySettler(world);
				es.setOwner(player);
				es.setPosition(x, y, z);
				if(!world.isRemote)
					world.spawnEntityInWorld(es);
			}
	}

	public EntityLivingBase getOwner(World world) {
		try {
			UUID uuid = UUID.fromString(this.player);
			return uuid == null ? null : world.func_152378_a(uuid);
		} catch (IllegalArgumentException illegalargumentexception) {
			return null;
		}
	}
	public int getFreeSpace() {
		return freeSpace;
	}
	
	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}
	
}
