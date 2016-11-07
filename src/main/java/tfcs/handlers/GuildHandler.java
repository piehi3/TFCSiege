package tfcs.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import tfcs.guild.Guild;

public class GuildHandler {

	public List<Guild> guilds;

	public GuildHandler() {
		guilds = new ArrayList<Guild>();
	}

	public boolean isInAGuild(EntityPlayer player, int xCoord,
			int yCoord, int zCoord) {
		for (int i = 0; i < guilds.size(); i++)
			if (guilds.get(i) != null)
				if (!guilds.get(i).isPlayerInGuild(player))
					return guilds.get(i).isEventInGuild(xCoord, yCoord,
							zCoord);
		return false;
	}
}
