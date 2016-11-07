package tfcs.handlers.network;

import tfcs.entity.EntitySettler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncEntityToServer implements IMessage, IMessageHandler<MessageSyncEntityToServer, IMessage> {

	private int entityId;
	private NBTTagCompound entitySyncDataCompound;

	public MessageSyncEntityToServer() {
	}

	public MessageSyncEntityToServer(int parEntityId, NBTTagCompound parTagCompound) {
		entityId = parEntityId;
		entitySyncDataCompound = parTagCompound;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityId = ByteBufUtils.readVarInt(buf, 4);
		entitySyncDataCompound = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarInt(buf, entityId, 4);
		ByteBufUtils.writeTag(buf, entitySyncDataCompound);
	}

	@Override
	public IMessage onMessage(MessageSyncEntityToServer message, MessageContext ctx) {
		if (ctx.side.isServer()) {
			EntityPlayer thePlayer = ctx.getServerHandler().playerEntity;
			if (thePlayer.worldObj.getEntityByID(message.entityId) instanceof EntitySettler) {
				EntitySettler theEntity = (EntitySettler) thePlayer.worldObj.getEntityByID(message.entityId);
				theEntity.readEntityFromNBT(message.entitySyncDataCompound);
			}
		}
		return null;
	}
}
