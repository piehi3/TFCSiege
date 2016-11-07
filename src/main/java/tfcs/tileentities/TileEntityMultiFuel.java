package tfcs.tileentities;

import com.bioxx.tfc.Core.TFC_Climate;

import net.minecraft.nbt.NBTTagCompound;
import tfcs.core.TFCSBlocks;
import tfcs.util.HeatTransferManager;
import tfcs.util.IHeated;
import tfcs.util.IndustrialFuel;

public class TileEntityMultiFuel extends TileEntityTFCS implements IHeated{

	private float timeBurning = 0;
	private float timePerFuel = 0;
	private float heatPerFuel = 0;
	private float temperature = 0;
	
	public void updateFuelStats(IndustrialFuel industrialFuel, int fuelVolume) {
		heatPerFuel = getScaledFuelStat(heatPerFuel, industrialFuel.getBurnTemperature(), this.getFuelVolume());
		timePerFuel = getScaledFuelStat(timePerFuel, industrialFuel.getBurnTime(), this.getFuelVolume());
	}
	
	@Override
	public void updateEntity() {
		if(this.worldObj != null){
			if(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord).equals(TFCSBlocks.multiFuelON)){
			if(timeBurning<timePerFuel)
				timeBurning++;
			else
				removeFuelToSlot();
			if(temperature<heatPerFuel)
				temperature+=4;
			}
			HeatTransferManager.conductHeat(worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	public float getScaledFuelStat(float fuelStat, float newfuelStat,float fuelVolume) {
		return ((fuelStat * (this.getFuelVolume()-1))+newfuelStat)/(this.getFuelVolume());
	}
	
	public void removeFuelToSlot() {
		if (this.getFuelVolume()-1 == 0) {
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
		} else {
			this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.getFuelVolume()-2, 2);
			this.timeBurning = 0;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat("TIME_BURNING", this.timeBurning);
		tag.setFloat("TIME_PER_FUEL", this.timePerFuel);
		tag.setFloat("TIME_PER_HEAT", this.heatPerFuel);
		tag.setFloat("TEMPERATURE", this.temperature);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.timeBurning = tag.getInteger("TIME_BURNING");
		this.timePerFuel = tag.getInteger("TIME_PER_FUEL");
		this.heatPerFuel = tag.getInteger("TIME_PER_HEAT");
		this.temperature = tag.getInteger("TEMPERATURE");
	}

	public float getTimeBurning() {
		return timeBurning;
	}

	public void setTimeBurning(int timeBurning) {
		this.timeBurning = timeBurning;
	}

	public float getTimePerFuel() {
		return timePerFuel;
	}

	public void setTimePerFuel(int timePerFuel) {
		this.timePerFuel = timePerFuel;
	}

	public float getHeatPerFuel() {
		return heatPerFuel;
	}

	public void setHeatPerFuel(int heatPerFuel) {
		this.heatPerFuel = heatPerFuel;
	}
	
	public int getFuelVolume() {
		return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) + 1;
	}

	@Override
	public void addHeat(float temperature) {
		this.temperature += temperature;
	}

	@Override
	public float getHeat() {
		return temperature + (TFC_Climate.getBioTemperatureHeight(worldObj, xCoord, yCoord, zCoord)+273);
	}
	
	@Override
	public float getThermalConductivity() {
		return 8.8F;
	}

	@Override
	public float getHeatCapacity() {
		return 388.0F * 8.5F;
	}

	@Override
	public float getNonAnbiantTemperature() {
		return this.temperature;
	}
	
}
