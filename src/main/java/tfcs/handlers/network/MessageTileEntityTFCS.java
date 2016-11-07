package tfcs.handlers.network;

import tfcs.tileentities.TileEntityTFCS;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTileEntityTFCS implements IMessage, IMessageHandler<MessageTileEntityTFCS, IMessage> {

	public NBTTagCompound tileentityNBT;

	public MessageTileEntityTFCS() {
	}

	public MessageTileEntityTFCS(TileEntityTFCS tileentity) {
		tileentityNBT = new NBTTagCompound();
		tileentityNBT.setIntArray("COORDS", new int[] { tileentity.xCoord, tileentity.yCoord, tileentity.zCoord });
		tileentity.writeToNBT(tileentityNBT);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		tileentityNBT = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, tileentityNBT);
	}

	@Override
	public IMessage onMessage(MessageTileEntityTFCS message, MessageContext ctx) {
		if (message.tileentityNBT != null) {
			int[] coords = message.tileentityNBT.getIntArray("COORDS");
			TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(coords[0], coords[1], coords[2]);
			if (tileEntity.getWorldObj().isRemote && tileEntity instanceof TileEntityTFCS)
				tileEntity.readFromNBT(message.tileentityNBT);
		}
		return null;
	}
}
