package tfcs.gears;

import net.minecraft.entity.player.EntityPlayer;
import tfcs.tileentities.TileEntityGearFrame;

public class AxleComponent extends FrameComponent {
	@Override
	public void drop(EntityPlayer player, TileEntityGearFrame tileentity) {
		tileentity.removeAxleComponent();
		super.drop(player, tileentity);
	}
}
