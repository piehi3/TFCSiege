package tfcs.tileentities;

import tfcs.core.GearRegistry;
import tfcs.core.RotationPacket;
import tfcs.core.TFCSBlocks;
import tfcs.gears.AxleComponent;
import tfcs.gears.FrameComponent;
import tfcs.gears.GearComponent;
import tfcs.gears.ToolComponent;
import tfcs.interfaces.IRotation;
import tfcs.util.RotationHelper;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityGearFrame extends TileEntityTFCS implements IRotation {

	public double maxRotation = 1;
	public double minTorque = 1;
	private boolean hasRotated = false;
	double rotation = 0;
	double rotationSpeed = 0;
	double torque = 0;
	int[] axe = new int[] { 0, 1, 0 };
	private int axeLocation = 0;

	private int[] linkedGear;

	private GearComponent gearComponent;
	private AxleComponent axleComponent;
	private ToolComponent toolComponent;

	@Override
	public void updateEntity() {

		this.hasRotated = false;

		if (!this.doseHaveGostGear()) {
			if (gearComponent != null)
				gearComponent.update(this);
			if (axleComponent != null)
				axleComponent.update(this);
			if (toolComponent != null && toolComponent.isActive())
				toolComponent.update(this);
		}
	}

	public boolean sameAxes(IRotation r) {
		boolean b = false;
		for (int i = 0; i < axe.length; i++) {
			if (axe[i] == 1)
				if (r.getAxe()[i] == 1)
					b = true;
		}
		return b;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public double getTorque() {
		return torque;
	}

	public void setTorque(double torque) {
		this.torque = torque;
	}

	public int getAxe(int i) {
		return axe[i - 1];
	}

	public void setAxe(int i) {
		int[] i2 = new int[] { 0, 0, 0 };
		i2[i - 1] = 1;
		this.axe = i2;
	}

	@Override
	public void addRotation(RotationPacket packet) {
		if (!doseHaveGostGear()) {
			unloadRotationPacket(packet);
			RotationPacket rotationPacket = new RotationPacket(packet.getSpeed(), packet.getTorque() - minTorque);
			RotationHelper.rotateAdj(this, rotationPacket);
		} else {
			if (this.worldObj.getTileEntity(this.linkedGear[0], this.linkedGear[1], this.linkedGear[2]) == null) {
				this.removeGostGear();
			} else if (!(this.worldObj.getTileEntity(this.linkedGear[0], this.linkedGear[1], this.linkedGear[2]) instanceof TileEntityGearFrame)) {
				this.removeGostGear();
			}
			TileEntityGearFrame tileentity = ((TileEntityGearFrame) (this.worldObj.getTileEntity(linkedGear[0], linkedGear[1], linkedGear[2])));
			if (tileentity != null) {
				if (!tileentity.getHasRotated()) {
					packet.scaleRotation(tileentity.getGearComponent().getSize(), 1);
					tileentity.addRotation(packet);
				}
			}
		}
	}

	@Override
	public void setHasRotated(boolean b) {
		this.hasRotated = b;
	}

	@Override
	public boolean breakGear() {
		return true;
	}

	@Override
	public void unloadRotationPacket(RotationPacket packet) {
		this.hasRotated = true;
		this.rotation += packet.getSpeed();
		this.rotationSpeed = packet.getSpeed();
		this.torque = packet.getTorque();
		if (rotation >= 360)
			rotation -= 360;
		if (rotation <= -360)
			rotation += 360;
		if (toolComponent != null)
			toolComponent.onRotated(packet, this);
	}

	public boolean getHasGear() {
		return gearComponent != null&&!isOffset();
	}

	public boolean getRenderHasGear() {
		return gearComponent != null;
	}
	
	public boolean getHAsGearOrGostGear() {
		return getHasGear() || this.doseHaveGostGear();
	}

	public boolean getHasAxle() {
		return axleComponent != null;
	}

	public boolean getHasTool() {
		return toolComponent != null;
	}

	@Override
	public int[] getAxe() {
		return axe;
	}

	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public GearComponent getGearComponent() {
		return gearComponent;
	}

	public void removeGearComponent() {
		if (this.getHasGear()) {
			this.getGearComponent().removeAugmentation(this);
			this.gearComponent = null;
		}
	}

	public AxleComponent getAxleComponent() {
		return axleComponent;
	}

	public void setFrameComponent(FrameComponent frameComponent, boolean override) {
		if (!this.doseHaveGostGear()) {
			if (override || frameComponent.onFrameComponentPlace(this)) {
				if (frameComponent instanceof AxleComponent) {
					if (this.axleComponent != null)
						this.axleComponent.removeAugmentation(this);
					frameComponent.applyAugmentation(this);
					this.axleComponent = (AxleComponent) frameComponent;
				} else if (frameComponent instanceof GearComponent) {
					if (this.gearComponent != null)
						this.gearComponent.removeAugmentation(this);
					frameComponent.applyAugmentation(this);
					this.gearComponent = (GearComponent) frameComponent;
				}
				if (this.getWorldObj() != null && !this.getWorldObj().isRemote)
					this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	public void removeAxleComponent() {
		if (this.getHasAxle()) {
			this.getAxleComponent().removeAugmentation(this);
			this.axleComponent = null;
		}
	}

	public ToolComponent getToolComponent() {
		return toolComponent;
	}

	private void forceSetToolComponent(FrameComponent frameComponent, NBTTagCompound tag) {
		if (frameComponent instanceof ToolComponent) {
			ToolComponent toolComponent = (ToolComponent) frameComponent;
			this.toolComponent = toolComponent;
			this.toolComponent.readFromNBT(tag);
			this.toolComponent.applyAugmentation(this);
			this.toolComponent.markForUpdate();
		}

	}

	public void setToolComponent(ToolComponent toolComponent) {
		if (!this.doseHaveGostGear()) {
			if (toolComponent.onFrameComponentPlace(this)) {
				if (this.toolComponent != null)
					this.toolComponent.removeAugmentation(this);
				toolComponent.applyAugmentation(this);
				this.toolComponent = toolComponent;
				if (!this.getWorldObj().isRemote)
					this.getWorldObj().markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	public void removeToolComponent() {
		if (this.getHasTool()) {
			this.getToolComponent().removeAugmentation(this);
			this.toolComponent = null;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		GearRegistry gr = GearRegistry.instance;
		if (tag.hasKey("GOST_COORD_0")) {
			int[] lg = new int[] { 0, 0, 0 };
			for (int i = 0; i < 3; i++)
				lg[i] = tag.getInteger("GOST_COORD_" + i);
			this.linkedGear = lg;
		}
		if (tag.hasKey("AXE_LOCATION"))
			this.axeLocation = tag.getInteger("AXE_LOCATION");
		if (tag.hasKey("AXE_0"))
			for (int i = 0; i < axe.length; i++)
				axe[i] = tag.getByte("AXE_" + i);
		if (tag.hasKey("GEAR_COMPONENET")) {
			this.setFrameComponent((GearComponent) gr.getFrameComponentFromString(tag.getString("GEAR_COMPONENET")), true);
			this.gearComponent.readFromNBT(tag);
		}
		if (tag.hasKey("AXLE_COMPONENET")) {
			this.setFrameComponent((AxleComponent) gr.getFrameComponentFromString(tag.getString("AXLE_COMPONENET")), true);
		}
		if (tag.hasKey("TOOL_COMPONENET")) {
			this.forceSetToolComponent(gr.getFrameComponentFromString(tag.getString("TOOL_COMPONENET")), tag);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (linkedGear != null) {
			for (int i = 0; i < this.linkedGear.length; i++)
				tag.setInteger("GOST_COORD_" + i, this.linkedGear[i]);
		}
		tag.setInteger("AXE_LOCATION", this.axeLocation);
		for (int i = 0; i < axe.length; i++) {
			tag.setByte("AXE_" + i, (byte) axe[i]);
		}
		if (this.getRenderHasGear()) {
			this.gearComponent.writeToNBT(tag);
			tag.setString("GEAR_COMPONENET", gearComponent.getSaveName());
		}
		if (this.getHasAxle()) {
			tag.setString("AXLE_COMPONENET", axleComponent.getSaveName());
		}
		if (this.getHasTool()) {
			this.toolComponent.writeToNBT(tag);
			tag.setString("TOOL_COMPONENET", toolComponent.getSaveName());
		}
	}

	public double getAxeLocation() {
		return (double) axeLocation;
	}

	public void setAxeLocation(int axeLocation) {
		this.axeLocation = axeLocation;
	}

	public boolean getHasRotated() {
		return getHasGear() || getHasAxle() ? hasRotated : false;
	}

	public boolean getIsEmpty() {
		if (this.getHasAxle() || this.getHasGear() || this.getHasTool()) {
			return false;
		} else {
			return true;
		}
	}

	public void setGostGear(TileEntityGearFrame tileentity) {
		this.maxRotation = 360;
		this.minTorque = 0;
		this.linkedGear = new int[] { tileentity.xCoord, tileentity.yCoord, tileentity.zCoord };
	}

	public void removeGostGear() {
		this.maxRotation -= 360;
		this.linkedGear = new int[] { 0, -1, 0 };
	}

	public boolean doseHaveGostGear() {
		return linkedGear != null ? linkedGear[1] >= 0 : false;
	}

	public int[] getLinkedGear() {
		return linkedGear;
	}

	public void setLinkedGear(int[] linkedGear) {
		this.linkedGear = linkedGear;
	}

	public int getGearSize() {
		if (this.getHasGear())
			return (int) this.getGearComponent().getSize();
		return 1;
	}

	public boolean sameAxeLocation(int a) {
		if (this.getAxeLocation() == a) {
			if (this.axe[0] == 1)
				return isBlockFrame((int) (1 * this.getAxeLocation()), 0, 0);
			if (this.axe[1] == 1)
				return isBlockFrame(0, 0, (int) (-1 * this.getAxeLocation()));
			if (this.axe[2] == 1)
				return isBlockFrame(0, (int) (-1 * this.getAxeLocation()), 0);
		}
		return false;
	}

	private boolean isBlockFrame(int x, int y, int z) {
		return this.worldObj.getBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z).getUnlocalizedName().equals(TFCSBlocks.gearFrame.getUnlocalizedName());
	}

	public boolean isOffset() {
		return this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

}
