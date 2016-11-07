package tfcs.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import tfcs.TFCSiege;
import tfcs.guild.Guild;

public class EventHandler {

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent e) {
		if(e.getPlayer()!=null)
			e.setCanceled(TFCSiege.instance.guildHandler.isInAGuild(e.getPlayer(), e.x, e.y, e.z));
	}

}
