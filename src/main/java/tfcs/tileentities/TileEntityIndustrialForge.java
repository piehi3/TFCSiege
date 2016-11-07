package tfcs.tileentities;

import com.bioxx.tfc.api.TFCBlocks;

import tfcs.core.TFCSBlocks;
import tfcs.util.IndustrialFuel;

public class TileEntityIndustrialForge extends TileEntityTFCS {

	private int size = 0;

	public void addFuelToPile(IndustrialFuel industrialFuel) {
		if (size == 0)
			size = getMultiBlockSize();
		float averageFuelVolume = getAverageFuelVolume();
		for (int i = -(size - 1); i < size; i++)
			for (int k = -(size - 1); k < size; k++) {
				int fuelVolume = getFuelVolumeFromBlock(xCoord + i, yCoord + 1, zCoord + k);
				if (fuelVolume != -1)
					if (fuelVolume <= averageFuelVolume){
						this.addFuelToSlot(xCoord + i, yCoord + 1, zCoord + k, industrialFuel, fuelVolume);
						return;
					}
			}

	}

	private void addFuelToSlot(int x, int y, int z, IndustrialFuel industrialFuel, int fuelVolume) {
		if (fuelVolume == 0) {
			this.worldObj.setBlock(x, y, z, TFCSBlocks.multiFuelOFF, fuelVolume, 2);
			((TileEntityMultiFuel)this.worldObj.getTileEntity(x, y, z)).updateFuelStats(industrialFuel, fuelVolume);
		} else {
			this.worldObj.setBlockMetadataWithNotify(x, y, z, fuelVolume, 2);
			((TileEntityMultiFuel)this.worldObj.getTileEntity(x, y, z)).updateFuelStats(industrialFuel, fuelVolume);
		}
	}

	private int getFuelVolumeFromBlock(int x, int y, int z) {
		if (this.worldObj.getBlock(x, y, z).equals(TFCSBlocks.multiFuelOFF) || this.worldObj.getBlock(x, y, z).equals(TFCSBlocks.multiFuelON)) {
			return 1 + this.worldObj.getBlockMetadata(x, y, z);
		} else if (this.worldObj.isAirBlock(x, y, z)) {
			return 0;
		} else {
			return -1;
		}
	}

	private float getAverageFuelVolume() {
		float numberOfFuelSlots = (size + size - 1) * (size + size - 1);
		float volumeOfFuel = 0;
		for (int i = -(size - 1); i < size; i++)
			for (int k = -(size - 1); k < size; k++)
				if (this.worldObj.getBlock(xCoord + i, yCoord + 1, zCoord + k).equals(TFCSBlocks.multiFuelOFF)
						|| this.worldObj.getBlock(xCoord + i, yCoord + 1, zCoord + k).equals(TFCSBlocks.multiFuelON))
					volumeOfFuel += 1 + this.worldObj.getBlockMetadata(xCoord + i, yCoord + 1, zCoord + k);

		return volumeOfFuel / numberOfFuelSlots;
	}

	private int getMultiBlockSize() {
		for (int i = 1; i < 5; i++)
			if (this.worldObj.getBlock(this.xCoord + i, this.yCoord + 1, this.zCoord).equals(TFCBlocks.fireBrick))
				return i;
		return 0;
	}

}
