package tfcs.gears;

import tfcs.tileentities.TileEntityGearFrame;
import net.minecraft.entity.player.EntityPlayer;

public class GearComponent extends FrameComponent{
	private int toothSize = 4;
	private double gearSubDivid = 8;
	private double depth = 0.2;

	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		int a = (int) ((this.getSize() - 1) / 2);
		if (a > 0) {
			for (int i = -a; i < a; i++) {
				for (int k = -a; k < a; k++) {
					if (!isFrameViable(tileentity.xCoord, tileentity.yCoord + k, tileentity.zCoord + i, 1, tileentity))
						return false;
					if (!isFrameViable(tileentity.xCoord + i, tileentity.yCoord + k, tileentity.zCoord, 2, tileentity))
						return false;
					if (!isFrameViable(tileentity.xCoord + i, tileentity.yCoord, tileentity.zCoord + k, 3, tileentity))
						return false;
				}
			}
			setGostGears(tileentity, a);
			return true;
		} else {
			return true;
		}
	}

	@Override
	public void update(TileEntityGearFrame tileentity) {
		if(!tileentity.getWorldObj().isRemote)
			//System.out.println(this.size);
		super.update(tileentity);
	}
	
	public void setGostGears(TileEntityGearFrame tileentity, int a) {
		if (tileentity.getAxe(1) == 1) {
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord - a))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord + a))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord - a, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord + a, tileentity.zCoord))).setGostGear(tileentity);
		}
		if (tileentity.getAxe(2) == 1) {
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord - a, tileentity.yCoord, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord + a, tileentity.yCoord, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord - a, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord + a, tileentity.zCoord))).setGostGear(tileentity);

		}
		if (tileentity.getAxe(3) == 1) {
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord - a, tileentity.yCoord, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord + a, tileentity.yCoord, tileentity.zCoord))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord - a))).setGostGear(tileentity);
			((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord + a))).setGostGear(tileentity);
		}
	}

	private boolean isFrameViable(int x, int y, int z, int axe, TileEntityGearFrame tileentity) {
		if (!(tileentity.xCoord == x && tileentity.yCoord == y && tileentity.zCoord == z)) {
			if (tileentity.getAxe(axe) == 1) {
				if (tileentity.getWorldObj().getTileEntity(x, y, z) == null) {
					return false;
				} else if (!(tileentity.getWorldObj().getTileEntity(x, y, z) instanceof TileEntityGearFrame)) {
					return false;
				} else if (!((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(x, y, z))).getIsEmpty()) {
					return false;
				} else {
					return ((TileEntityGearFrame) (tileentity.getWorldObj().getTileEntity(x, y, z))).sameAxes(tileentity);
				}
			}
		}
		return true;
	}

	public int getToothSize() {
		return toothSize;
	}

	public void setToothSize(int toothSize) {
		this.toothSize = toothSize;
	}

	public double getGearSubDivid() {
		return gearSubDivid;
	}

	public void setGearSubDivid(double gearSubDivid) {
		this.gearSubDivid = gearSubDivid;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public boolean getHasTeeth() {
		return toothSize > 0;
	}


	public double getRenderSize() {
		return ((double)this.getSize()) / 2;
	}
	
	@Override
	public void drop(EntityPlayer player, TileEntityGearFrame tileentity) {
		tileentity.removeGearComponent();
		super.drop(player, tileentity);
	}
}
