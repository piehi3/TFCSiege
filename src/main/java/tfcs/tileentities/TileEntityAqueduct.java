package tfcs.tileentities;

import com.bioxx.tfc.api.TFCBlocks;

import net.minecraft.nbt.NBTTagCompound;
import tfcs.util.WaterPacket;

public class TileEntityAqueduct extends TileEntityTFCS {

	WaterPacket packet = new WaterPacket(0, WaterPacket.getMaxDrop());
	int maxCounter = 1000;
	int counter = maxCounter;
	int maxWaterCounter = 100;
	int waterCounter = maxWaterCounter;

	@Override
	public void updateEntity() {
		if(packet.getWater()>0){
		if (counter <= 0) {
			counter = maxCounter;
			WaterPacket.moveWater(getWorldObj(), xCoord, yCoord, zCoord, this, new WaterPacket((packet.getWater() / 3) + 1, packet.getDrop()));
		} else {
			counter -= packet.getDrop() + (packet.getWater()/2);
		}
		}
		if (waterCounter <= 0) {
			if (WaterPacket.hasWater(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord)){
				addWater(new WaterPacket(4, WaterPacket.getMaxWater()+1));
			}
			waterCounter = maxWaterCounter;
		} else {
			waterCounter--;
		}
	}

	public boolean addWater(WaterPacket packet) {
		this.packet.addWater(packet.getWater());
		this.packet.setDrop(packet.getDrop() - 1);
		if(this.packet.getWater()>WaterPacket.getMaxWater())
			overFlowWater();
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		return true;
	}

	public void overFlowWater() {
		this.packet.setWater(WaterPacket.getMaxWater());
		//if(this.getWorldObj().isAirBlock(xCoord, yCoord+1, zCoord))
		//	this.worldObj.setBlock(xCoord, yCoord+1, zCoord, TFCBlocks.freshWater);
	}
	
	public void removeWater(WaterPacket packet) {
		this.packet.addWater(-packet.getWater());
	}

	public WaterPacket getWaterPacket() {
		return packet;
	}

	public double getRenderWater() {
		if (packet != null)
			return ((double) packet.getWater()) / ((double) packet.getMaxWater());
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.packet = new WaterPacket(tag.getInteger("WATER"), tag.getInteger("DROP"));
		this.counter = tag.getInteger("COUNTER");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("WATER", packet.getWater());
		tag.setInteger("DROP", packet.getDrop());
		tag.setInteger("COUNTER", this.counter);
	}

}
