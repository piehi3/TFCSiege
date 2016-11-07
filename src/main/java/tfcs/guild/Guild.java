package tfcs.guild;

import java.util.ArrayList;
import java.util.List;

import tfcs.TFCSiege;
import net.minecraft.entity.player.EntityPlayer;

public class Guild {
	
	private List<EntityPlayer> players;
	private List<SubGuild> subGuilds;
	private String name = "Defult Name";
	
	public Guild(EntityPlayer player,String name){
		TFCSiege.instance.guildHandler.guilds.add(this);
		this.players = new ArrayList<EntityPlayer>();
		this.players.add(player);
		this.subGuilds = new ArrayList<SubGuild>();
		this.name = name;
	}
	
	public Guild(ArrayList<EntityPlayer> players) {
		TFCSiege.instance.guildHandler.guilds.add(this);
		this.players = players;
		this.subGuilds = new ArrayList<SubGuild>();
	}
	
	private void addSubGuild(SubGuild guild){
		this.subGuilds.add(guild);
	}
	
	public void addSubGuild(int xCoord,int yCoord,int zCoord,int size){
		this.addSubGuild(new SubGuild(xCoord,yCoord,zCoord,size));
	}
	
	public void removeSubGuild(SubGuild guild){
		this.subGuilds.remove(guild);
	}
	
	public void addPlayer(EntityPlayer player) {
		this.players.add(player);
	}
	
	public void removePlayer(EntityPlayer player){
		this.players.remove(player);
	}

	public boolean isPlayerInGuild(EntityPlayer player) {
		return players.contains(player);
	}
	
	public boolean isEventInGuild(int xCoord,int yCoord,int zCoord) {
		for (int i = 0; i < subGuilds.size(); i++)
			if(subGuilds.get(i)!=null)
				return subGuilds.get(i).isInSubGuild(xCoord, yCoord, zCoord);
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}