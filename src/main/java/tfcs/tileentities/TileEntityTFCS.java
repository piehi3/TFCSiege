package tfcs.tileentities;

import tfcs.handlers.network.MessageTileEntityTFCS;
import tfcs.handlers.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTFCS extends TileEntity{
	@Override
	public Packet getDescriptionPacket() {
		return PacketHandler.network.getPacketFrom(new MessageTileEntityTFCS(this));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
	}
}
