package tfcs.handlers;

import tfcs.core.Recipes;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ServerTickHandler 
{
    @SubscribeEvent 
    public void onServerWorldTick(WorldTickEvent e) 
    { 
    	System.out.println("///////////////////////////////////////////////////////////////////////////");
        if (e.phase == Phase.START) 
        { 
        	if (e.world.provider.dimensionId == 0)
        		Recipes.initialiseAnvil(); 
        } 
		else if(e.phase == Phase.END)
		{

		}
    } 
}
