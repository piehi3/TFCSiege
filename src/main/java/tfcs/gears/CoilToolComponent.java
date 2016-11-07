package tfcs.gears;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import tfcs.core.RotationPacket;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.util.RenderHelper;

public class CoilToolComponent extends ToolComponent {

	double coilPosition = 0;
	double coilResistance = 5;
	double maxCoilPosition = 120;
	boolean isOutputing = false;

	public CoilToolComponent() {
		this.setMaxRotation(1);
		this.setMinTorque(1);
	}

	@Override
	public void onRotated(RotationPacket packet, TileEntityGearFrame tileentity) {
		if (!isOutputing) {
			if (coilPosition <= maxCoilPosition) {
				coilPosition += (Math.abs(packet.getSpeed()) / 10);
				updateCoilResistance(tileentity);
			}
		}
	}

	@Override
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		this.isOutputing = true;
	}

	@Override
	public void update(TileEntityGearFrame tileentity) {
		super.update(tileentity);
		if (this.isOutputing) {
			if (coilPosition > 0) {
				tileentity.addRotation(getRotationOutput());
				coilPosition--;
				updateCoilResistance(tileentity);
			} else {
				this.isOutputing = false;
			}
		}
	}

	private void updateCoilResistance(TileEntityGearFrame tileentity) {
		this.removeAugmentation(tileentity);
		this.setMinTorque(coilResistance * coilPosition);
		this.applyAugmentation(tileentity);
	}

	public RotationPacket getRotationOutput() {
		return new RotationPacket(coilResistance * 20, coilResistance * coilPosition * 1.5);
	}

	@Override
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		RenderHelper.renderCoil(tessellator, 0, 8, 0.2 - (coilPosition / 1000), 0.075, 2, 0.05);
	}

	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		return tileentity.getHasAxle();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		coilPosition = tag.getDouble("COIL_POSITION");
		coilResistance = tag.getDouble("COIL_RESISTANCE");
		isOutputing = tag.getBoolean("OUTPUT");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setDouble("COIL_POSITION", coilPosition);
		tag.setDouble("COIL_RESISTANCE", coilResistance);
		tag.setBoolean("OUTPUT", isOutputing);
	}

	public void setCoilResistance(double coilResistance) {
		this.coilResistance = coilResistance;
	}

}
