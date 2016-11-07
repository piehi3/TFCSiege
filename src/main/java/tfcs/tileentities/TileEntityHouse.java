package tfcs.tileentities;

import com.bioxx.tfc.Core.TFC_Time;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import tfcs.core.TFCSBlocks;
import tfcs.util.CitizenHouse;

public class TileEntityHouse extends TileEntityTFCS {

	boolean isHome;
	boolean hasSpawned = false;;
	int maxSize = 10;
	int personRequirment = 25;
	int size;
	CitizenHouse house;

	@Override
	public void updateEntity() {
		if (isHome) {
			if (TFC_Time.getDayOfMonth() == 1) {
				if (!hasSpawned) {
					spawnSettler();
					hasSpawned = true;
				}
			} else {
				hasSpawned = false;
			}
		}
		super.updateEntity();
	}

	private void spawnSettler() {
		if (hasStructure())
			house.setNewSettler(this.worldObj, this.xCoord + (size / 2), this.yCoord + 1, this.zCoord + (size / 2));
	}

	public void setAsHome(EntityPlayer player) {
		if (hasStructure()) {
			this.isHome = true;
			house = new CitizenHouse((size * size) / personRequirment, player.getUniqueID().toString());
		}
	}

	private int getSize() {
		int i = 0;
		for (int k = 0; k <= maxSize; k++)
			if (this.worldObj.getBlock(xCoord + k, yCoord, zCoord).equals(TFCSBlocks.foundation))
				if (!this.worldObj.canBlockSeeTheSky(xCoord + k, yCoord, zCoord))
					i++;
		if (hasFloor(i))
			return i;
		return 0;
	}

	private boolean hasFloor(int i) {
		for (int j = 0; j < i; j++)
			for (int k = 0; k < i; k++)
				if (this.worldObj.isAirBlock(xCoord + k, yCoord, zCoord + j))
					return false;
		return true;
	}

	private boolean hasWall(int x, int y, int z) {
		for (int i = 0; i < 4; i++)
			if (this.worldObj.isAirBlock(x, y + i, z))
				return false;
		return true;
	}

	public boolean hasStructure() {
		int i = getSize();
		if (i > 0) {
			for (int j = 0; j < i; j++)
				if (!this.hasWall(this.xCoord + j, this.yCoord, this.zCoord))
					return false;
			for (int j = 0; j < i; j++)
				if (!this.hasWall(this.xCoord, this.yCoord, this.zCoord + j))
					return false;
			for (int j = 0; j < i; j++)
				if (!this.hasWall(this.xCoord + j, this.yCoord, this.zCoord + i - 1))
					return false;
			for (int j = 0; j < i; j++)
				if (!this.hasWall(this.xCoord + i - 1, this.yCoord, this.zCoord + j))
					return false;
			this.size = i;
			return true;
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.isHome = tag.getBoolean("IS_HOME");
		this.hasSpawned = tag.getBoolean("HAS_SPANWED");
		this.size = tag.getInteger("SIZE");
		if (tag.hasKey("FREE_SPACE"))
			this.house = new CitizenHouse(tag.getInteger("FREE_SPACE"), tag.getString("OWNER"));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("IS_HOME", this.isHome);
		tag.setBoolean("HAS_SPANWED", this.hasSpawned);
		tag.setInteger("SIZE", this.size);
		if (this.house != null) {
			tag.setInteger("FREE_SPACE", this.house.getFreeSpace());
			tag.setString("OWNER", this.house.getPlayer());
		}
	}

	public boolean isHome() {
		return isHome;
	}

}
