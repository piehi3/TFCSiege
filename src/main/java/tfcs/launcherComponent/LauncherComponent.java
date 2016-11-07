package tfcs.launcherComponent;

import tfcs.gears.ToolComponent;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.tileentities.TileEntityLauncherBase;

public class LauncherComponent extends ToolComponent{

	@Override
	public boolean onFrameComponentPlace(TileEntityGearFrame tileentity) {
		return tileentity instanceof TileEntityLauncherBase;
	}
	
}
