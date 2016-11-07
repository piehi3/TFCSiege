package tfcs.guild;

public class SubGuild {

	int xCoord = 0;
	int yCoord = 0;
	int zCoord = 0;
	int size = 0;
	public SubGuild(int xCoord,int yCoord,int zCoord,int size) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.size = size;
	}
	
	public boolean isInSubGuild(int xCoord,int yCoord,int zCoord) {
		return size*size >=(xCoord-this.xCoord)*(xCoord-this.xCoord) + (yCoord-this.yCoord)*(yCoord-this.yCoord) + (zCoord-this.zCoord)*(zCoord-this.zCoord);
	}
	
}
