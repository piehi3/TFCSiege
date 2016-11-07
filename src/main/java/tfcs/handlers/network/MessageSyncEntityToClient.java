package tfcs.handlers.network;

import tfcs.entity.EntitySettler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncEntityToClient  implements IMessage, IMessageHandler<MessageSyncEntityToClient, IMessage>{
	
	 public NBTTagCompound entityNBT;
	
	public MessageSyncEntityToClient() { }
	
	public MessageSyncEntityToClient(EntitySettler entity) {
		entityNBT = new NBTTagCompound();
		entityNBT.setInteger("ID", entity.getEntityId());
		entity.writeToNBT(entityNBT);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityNBT = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, entityNBT);
	}
	
	@Override
	public IMessage onMessage(MessageSyncEntityToClient message, MessageContext ctx) {
		 if (message.entityNBT != null){
			 int id = message.entityNBT.getInteger("ID");
	            Entity entity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(id);
	            if (entity instanceof EntitySettler){
	                entity.readFromNBT(message.entityNBT);
	            }
	        }
		return null;
	}

}
