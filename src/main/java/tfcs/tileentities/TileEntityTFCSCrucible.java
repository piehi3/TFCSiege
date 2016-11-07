package tfcs.tileentities;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.TFCSBlocks;
import tfcs.reference.Reference;
import tfcs.util.HeatTransferManager;
import tfcs.util.IHeated;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityTFCSCrucible extends TileEntity implements IHeated{

	private boolean isValide = false;
	private float temperature = 0;

	@Override
	public void updateEntity() {
		if(this.worldObj!=null){
			HeatTransferManager.conductHeat(worldObj, xCoord, yCoord, zCoord);
		}
	}
	
	public boolean isValide() {
		return isValide;
	}

	public void setValide() {
		this.isValide = true;
	}

	public void invalide() {
		this.isValide = false;
	}

	public boolean checkForStructure(World world, int x, int y, int z) {
		int size = getStrutureSize(world, x, y, z);
		for (int i = -(size - 1); i < size; i++)
			for (int k = -(size - 1); k < size; k++)
				if (!world.getBlock(x + i, y, z + k).equals(TFCSBlocks.crucible))
					return false;
		for (int i = -(size - 1); i < size; i++) {
			if (!world.getBlock(x + i, y, z - (size)).equals(TFCBlocks.fireBrick))
				return false;
			if (!world.getBlock(x + i, y, z + (size)).equals(TFCBlocks.fireBrick))
				return false;
			if (!world.getBlock(x - (size), y, z + i).equals(TFCBlocks.fireBrick))
				return false;
			if (!world.getBlock(x + (size), y, z + i).equals(TFCBlocks.fireBrick))
				return false;
		}
		int height = getStructureHeight(world, x, y, z, size);
		for (int i = -(size - 1); i < size; i++)
			for (int k = 1; k < height; k++) {
				if (!world.getBlock(x - size, y + k, z + i).equals(TFCBlocks.fireBrick))
					return false;
				if (!world.getBlock(x + size, y + k, z + i).equals(TFCBlocks.fireBrick))
					return false;
				if (!world.getBlock(x + i, y + k, z - size).equals(TFCBlocks.fireBrick))
					return false;
				if (!world.getBlock(x + i, y + k, z + size).equals(TFCBlocks.fireBrick))
					return false;
			}
		return true;
	}

	private int getStructureHeight(World world, int x, int y, int z, int size) {
		for (int k = 1; k < Reference.MAX_FORGE_SIZE; k++)
			if (!world.getBlock(x + size, y + k, z).equals(TFCBlocks.fireBrick))
				return k;
		return 0;
	}

	private int getStrutureSize(World world, int x, int y, int z) {
		for (int i = 0; i < Reference.MAX_FORGE_SIZE; i++)
			if (!world.getBlock(x + i, y, z).equals(TFCSBlocks.crucible))
				return i;
		return 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.temperature = tag.getFloat("TEMPERATURE");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat("TEMPERATURE", this.temperature);
	}
	
	@Override
	public float getHeat() {
		return temperature + (TFC_Climate.getBioTemperatureHeight(worldObj, xCoord, yCoord, zCoord)+273);
	}

	@Override
	public void addHeat(float temperature) {
		this.temperature += temperature;
	}

	@Override
	public float getThermalConductivity() {
		return 125.0F;
	}

	@Override
	public float getHeatCapacity() {
		return 140580.0F * 25.1F;
	}

	@Override
	public float getNonAnbiantTemperature() {
		return this.temperature;
	}

	
	
}
