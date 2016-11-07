package tfcs.handlers.client;

import tfcs.TFCSiege;
import tfcs.proxy.ClientProxy;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

public class KeyHandler {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void playerTick(PlayerTickEvent event)
	{
	    KeyBinding[] keyBindings = ClientProxy.keyBindings;
	   
	    if (keyBindings[0].isPressed()) 
	    {
	    	event.player.openGui(TFCSiege.instance, GuiHandler.GuildIdCustom, event.player.worldObj, (int)event.player.posX, (int) event.player.posY, (int) event.player.posZ);
	    	//PacketHandler.network.sendToAllAround(new MessageSyncEntityToClient(this), new TargetPoint(this.worldObj.provider.dimensionId, this.posX, this.posY, this.posZ, 20));
	    }
	}
	
}
