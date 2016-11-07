package tfcs.gears;

import tfcs.core.RotationPacket;
import tfcs.tileentities.TileEntityGearFrame;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ToolComponent extends FrameComponent{

	boolean isActive = true;
	public ToolComponent() {
		this.setMaxRotation(1);
		this.setMinTorque(1);
	}
	
	public void onActivated(TileEntityGearFrame tileentity, EntityPlayer player) {
		
	}
	
	public void render(TileEntityGearFrame tileentity, Tessellator tessellator, double rotation) {
		
	}

	public void readFromNBT(NBTTagCompound tag) {
		
	}

	public void writeToNBT(NBTTagCompound tag) {
		
	}
	
	@Override
	public void drop(EntityPlayer player, TileEntityGearFrame tileentity) {
		tileentity.removeToolComponent();
		super.drop(player, tileentity);
	}

	public void onRotated(RotationPacket packet,TileEntityGearFrame tileentity) {
		
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
