package tfcs.handlers.client;

import tfcs.entity.EntitySettler;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.gui.GuildGui;
import tfcs.gui.SettlerGui;
import tfcs.gui.TravelingSalesmanGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler extends tfcs.handlers.GuiHandler
{
	@SuppressWarnings("unused")
	@Override
	public Object getClientGuiElement(int Id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity;
		
		/*try
		{
			tileEntity = world.getTileEntity(x, y, z);
		}
		catch(Exception e)
		{
			return null;
		}*/
		switch (Id) {
		case GuiIdCustom:
			if (player.worldObj.getEntityByID(x) instanceof EntitySettler)
				return new SettlerGui(player,(EntitySettler) player.worldObj.getEntityByID(x));
			else
				return null;
		case 1:
			if (player.worldObj.getEntityByID(x) instanceof EntityTravelingSalesman)
				return new TravelingSalesmanGui(player,(EntityTravelingSalesman) player.worldObj.getEntityByID(x));
			else
				return null;
		case GuiHandler.GuildIdCustom:
			return new GuildGui(player);
		default:
			return null;
		}
	}
}
