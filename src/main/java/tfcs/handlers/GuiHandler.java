package tfcs.handlers;

import tfcs.containers.ContainerGuild;
import tfcs.containers.ContainerSettler;
import tfcs.containers.ContainerTravelingSalesman;
import tfcs.entity.EntitySettler;
import tfcs.entity.EntityTravelingSalesman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	public static final int GuiIdCustom = 0;
	public static final int GuildIdCustom = 2;

	@SuppressWarnings("unused")
	@Override
	public Object getServerGuiElement(int Id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity;

		/*try {
			tileEntity = world.getTileEntity(x, y, z);
		} catch (Exception e) {
			return null;
		}*/

		switch (Id) {
		case GuiIdCustom:
			if (player.worldObj.getEntityByID(x) instanceof EntitySettler)
				return new ContainerSettler(player, (EntitySettler) player.worldObj.getEntityByID(x));
			else
				return null;
		case 1:
			if (player.worldObj.getEntityByID(x) instanceof EntityTravelingSalesman)
				return new ContainerTravelingSalesman(player, (EntityTravelingSalesman) player.worldObj.getEntityByID(x));
			else
				return null;
		case GuiHandler.GuildIdCustom:
			return new ContainerGuild(player);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int Id, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}
