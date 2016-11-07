package tfcs.tileentities;

import com.bioxx.tfc.Core.TFC_Time;

import net.minecraft.nbt.NBTTagCompound;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.reference.ReferenceMultiBlock;

public class TileEntityPort extends TileEntityTFCS {
	public boolean hasBoat = false;
	public double placeTimer;
	EntityTravelingSalesman ets;

	public TileEntityPort() {
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (TFC_Time.getDayOfMonth() >= 1 && TFC_Time.getDayOfMonth() <= ((int) TFC_Time.daysInMonth / 2)) {
				if (hasBoat) {
					this.hasBoat = !this.hasBoat;
					switch (ReferenceMultiBlock.PORT_STRUCTURE.getMultiBlockStructureWithFourRotations(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, false, -5, -1, -1)) {
					case 0:
						ReferenceMultiBlock.BOAT_STRUCTURE.clearBlocks(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord + 1);
						break;
					case 1:
						ReferenceMultiBlock.BOAT_STRUCTURE.clearBlocksWith90DegreeRotation(this.getWorldObj(), this.xCoord + 1, this.yCoord, this.zCoord - 5);
						break;
					case 2:
						ReferenceMultiBlock.BOAT_STRUCTURE.clearBlocksWith180DegreeRotation(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord - 5);
						break;
					case 3:
						ReferenceMultiBlock.BOAT_STRUCTURE.clearBlocksWith270DegreeRotation(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord - 5);
						break;
					default:
						break;
					}
					if (ets != null && !ets.isDead)
						ets.setDead();
				}
			} else if (TFC_Time.getDayOfMonth() <= TFC_Time.daysInMonth) {
				if (!hasBoat) {
					this.hasBoat = !this.hasBoat;
					switch (ReferenceMultiBlock.PORT_STRUCTURE.getMultiBlockStructureWithFourRotations(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, false, -5, -1, -1)) {
					case 0:
						ReferenceMultiBlock.BOAT_STRUCTURE.buildStructure(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord + 1);
						createEntitySalesmane(-3, 2, 0);
						break;
					case 1:
						ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith90DegreeRotation(this.getWorldObj(), this.xCoord + 1, this.yCoord, this.zCoord - 5);
						createEntitySalesmane(3, 2, 0);
						break;
					case 2:
						ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith180DegreeRotation(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord - 5);
						createEntitySalesmane(0, 2, -3);
						break;
					case 3:
						ReferenceMultiBlock.BOAT_STRUCTURE.buildStructureWith270DegreeRotation(this.getWorldObj(), this.xCoord - 5, this.yCoord, this.zCoord - 5);
						createEntitySalesmane(0, 2, 3);
						break;
					default:
						break;
					}
				}
			}
		}
	}

	private void createEntitySalesmane(int x, int y, int z) {
		this.ets = new EntityTravelingSalesman(this.worldObj);
		ets.setPosition(x + this.xCoord, y + this.yCoord, z + this.zCoord);
		if (!this.worldObj.isRemote)
			this.worldObj.spawnEntityInWorld(ets);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setBoolean("HAS_BOAT", this.hasBoat);
		tag.setInteger("PLACE_TIME", (int) this.placeTimer);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.hasBoat = tag.getBoolean("HAS_BOAT");
		this.placeTimer = tag.getInteger("PLACE_TIME");
	}

}
